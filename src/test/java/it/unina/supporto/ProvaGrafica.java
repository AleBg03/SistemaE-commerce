package it.unina.supporto;

import org.junit.jupiter.api.Assumptions;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProvaGrafica implements AutoCloseable {
    public record Dialogo(String titolo, String messaggio) {
    }

    private static final int FUORI_SCHERMO = -32000;

    private final List<Dialogo> dialoghi = new CopyOnWriteArrayList<>();
    private final Set<Window> dialoghiGestiti = Collections.newSetFromMap(new IdentityHashMap<>());
    private final Thread guardiano;
    private volatile boolean attivo = true;

    public static void richiedeSchermo() {
        Assumptions.assumeFalse(GraphicsEnvironment.isHeadless(), "test grafico: serve un ambiente con schermo (non headless)");
    }

    public ProvaGrafica() {
        guardiano = new Thread(this::sorveglia, "guardiano-finestre");
        guardiano.setDaemon(true);
        guardiano.start();
    }

    public List<Dialogo> dialoghi() {
        return List.copyOf(dialoghi);
    }

    public JFrame ospita(String aTitolo, JComponent aPannello) {
        JFrame[] finestra = new JFrame[1];
        eseguiSuEdt(() -> {
            finestra[0] = new JFrame(aTitolo);
            finestra[0].setContentPane(aPannello);
            finestra[0].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            finestra[0].pack();
            finestra[0].setLocation(FUORI_SCHERMO, FUORI_SCHERMO);
            finestra[0].setVisible(true);
        });
        return finestra[0];
    }

    public Optional<JFrame> finestraVisibile(String aTitolo) {
        for (Window w : Window.getWindows()) {
            if (w instanceof JFrame f && aTitolo.equals(f.getTitle()) && f.isShowing()) {
                return Optional.of(f);
            }
        }
        return Optional.empty();
    }

    public void premi(Object aForm, String aNomePulsante) {
        JButton pulsante = campo(aForm, aNomePulsante, JButton.class);
        eseguiSuEdt(pulsante::doClick);
    }

    public void scrivi(Object aForm, String aNomeCampo, String aTesto) {
        JTextComponent componente = campo(aForm, aNomeCampo, JTextComponent.class);
        eseguiSuEdt(() -> componente.setText(aTesto));
    }

    public String testo(Object aForm, String aNomeEtichetta) {
        return campo(aForm, aNomeEtichetta, JLabel.class).getText();
    }

    public static <T> T campo(Object aOggetto, String aNome, Class<T> aTipo) {
        try {
            Field campo = aOggetto.getClass().getDeclaredField(aNome);
            campo.setAccessible(true);
            return aTipo.cast(campo.get(aOggetto));
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("campo " + aNome + " non trovato in " + aOggetto.getClass().getSimpleName(), e);
        }
    }

    @Override
    public void close() {
        attivo = false;
        try {
            guardiano.join(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        eseguiSuEdt(() -> {
            for (Window w : Window.getWindows()) {
                w.dispose();
            }
        });
    }

    private void sorveglia() {
        while (attivo) {
            for (Window w : Window.getWindows()) {
                if (w.isShowing() && w.getX() > FUORI_SCHERMO / 2) {
                    w.setLocation(FUORI_SCHERMO, FUORI_SCHERMO);
                }
                if (w instanceof JDialog d && d.isShowing() && dialoghiGestiti.add(d)) {
                    chiudiDialogo(d);
                }
            }
            dialoghiGestiti.removeIf(w -> !w.isShowing());
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void chiudiDialogo(JDialog aDialogo) {
        JOptionPane pannello = trovaPannelloOpzioni(aDialogo.getContentPane());
        String messaggio = pannello == null ? "" : String.valueOf(pannello.getMessage());
        dialoghi.add(new Dialogo(aDialogo.getTitle(), messaggio));
        if (pannello != null) {
            SwingUtilities.invokeLater(() -> pannello.setValue(JOptionPane.OK_OPTION));
        } else {
            SwingUtilities.invokeLater(aDialogo::dispose);
        }
    }

    private static JOptionPane trovaPannelloOpzioni(Container aContenitore) {
        for (Component c : aContenitore.getComponents()) {
            if (c instanceof JOptionPane p) {
                return p;
            }
            if (c instanceof Container figlio) {
                JOptionPane trovato = trovaPannelloOpzioni(figlio);
                if (trovato != null) {
                    return trovato;
                }
            }
        }
        return null;
    }

    private static void eseguiSuEdt(Runnable aAzione) {
        try {
            if (SwingUtilities.isEventDispatchThread()) {
                aAzione.run();
            } else {
                SwingUtilities.invokeAndWait(aAzione);
            }
        } catch (InvocationTargetException e) {
            Throwable causa = e.getCause();
            if (causa instanceof RuntimeException re) {
                throw re;
            }
            if (causa instanceof Error er) {
                throw er;
            }
            throw new IllegalStateException(causa);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }
}

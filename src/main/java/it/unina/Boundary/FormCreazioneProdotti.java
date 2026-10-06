package it.unina.Boundary;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import it.unina.Control.DatiProdotto;
import it.unina.Exceptions.CategoriaNonTrovataException;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class FormCreazioneProdotti {
    private static final int LUNGHEZZA_MASSIMA_NOME = 15;
    private static final int LUNGHEZZA_MASSIMA_DESCRIZIONE = 200;

    private JPanel jpPrincipale;
    private JTextField jtfNome;
    private JTextArea jtaDescrizione;
    private JTextField jtfPrezzo;
    private JTextField jtfQuantita;
    private JTextField jtfCategoria;
    private JTextArea jtaMessaggio;
    private JButton jbCreaProdotto;
    private JButton jbAnnulla;
    private final BAmministratore amministratore;

    public FormCreazioneProdotti() {
        this.amministratore = new BAmministratore();
        jbCreaProdotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickCrea();
            }
        });
        jbAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickAnnulla();
            }
        });
    }

    private void onClickCrea() {
        if (!jbCreaProdotto.isEnabled()) {
            return;
        }
        if (!verificaValiditaDati()) {
            return;
        }
        DatiProdotto dati = new DatiProdotto(jtfNome.getText().trim(), jtaDescrizione.getText().trim(),
                leggiPrezzo(), Integer.parseInt(jtfQuantita.getText().trim()), jtfCategoria.getText().trim());
        try {
            if (amministratore.creaProdotto(dati)) {
                jtaMessaggio.setText("Prodotto creato e aggiunto al catalogo.");
                jbCreaProdotto.setEnabled(false);
                jbAnnulla.setText("Chiudi");
                jtfNome.setEditable(false);
                jtaDescrizione.setEditable(false);
                jtfPrezzo.setEditable(false);
                jtfQuantita.setEditable(false);
                jtfCategoria.setEditable(false);
            } else {
                jtaMessaggio.setText("Il prodotto non e' stato creato. Verifica i dati e riprova.");
            }
        } catch (CategoriaNonTrovataException ex) {
            jtaMessaggio.setText(ex.getMessage());
        } catch (UnsupportedOperationException ex) {
            jtaMessaggio.setText("La creazione del prodotto non e' ancora disponibile.");
        } catch (RuntimeException ex) {
            jtaMessaggio.setText("Errore durante la creazione del prodotto. Nessuna conferma di salvataggio ricevuta.");
        }
    }

    private boolean verificaValiditaDati() {
        if (jtfNome.getText().isBlank()) {
            return segnalaErrore("Inserisci il nome del prodotto.", jtfNome);
        }
        if (jtfNome.getText().trim().length() > LUNGHEZZA_MASSIMA_NOME) {
            return segnalaErrore("Il nome deve contenere al massimo 15 caratteri.", jtfNome);
        }
        if (jtaDescrizione.getText().isBlank()) {
            return segnalaErrore("Inserisci la descrizione del prodotto.", jtaDescrizione);
        }
        if (jtaDescrizione.getText().trim().length() > LUNGHEZZA_MASSIMA_DESCRIZIONE) {
            return segnalaErrore("La descrizione deve contenere al massimo 200 caratteri.", jtaDescrizione);
        }
        if (jtfPrezzo.getText().isBlank()) {
            return segnalaErrore("Inserisci il prezzo del prodotto.", jtfPrezzo);
        }
        if (jtfQuantita.getText().isBlank()) {
            return segnalaErrore("Inserisci la quantita' disponibile.", jtfQuantita);
        }
        if (jtfCategoria.getText().isBlank()) {
            return segnalaErrore("Inserisci il nome di una categoria gia' registrata.", jtfCategoria);
        }
        try {
            float prezzo = leggiPrezzo();
            if (!Float.isFinite(prezzo) || prezzo <= 0) {
                return segnalaErrore("Il prezzo deve essere un numero finito maggiore di zero.", jtfPrezzo);
            }
        } catch (NumberFormatException ex) {
            return segnalaErrore("Il prezzo deve essere numerico (esempio: 19,99).", jtfPrezzo);
        }
        try {
            int quantita = Integer.parseInt(jtfQuantita.getText().trim());
            if (quantita < 0) {
                return segnalaErrore("La quantita' deve essere un intero non negativo.", jtfQuantita);
            }
        } catch (NumberFormatException ex) {
            return segnalaErrore("La quantita' deve essere un numero intero non negativo.", jtfQuantita);
        }
        return true;
    }

    private float leggiPrezzo() {
        String valore = jtfPrezzo.getText().trim().replace(',', '.');
        if (!valore.matches("[+-]?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)")) {
            throw new NumberFormatException();
        }
        return Float.parseFloat(valore);
    }

    private boolean segnalaErrore(String messaggio, JComponent campo) {
        jtaMessaggio.setText(messaggio);
        campo.requestFocusInWindow();
        return false;
    }

    private void onClickAnnulla() {
        Window finestra = SwingUtilities.getWindowAncestor(jpPrincipale);
        if (finestra != null) {
            finestra.dispose();
        }
    }

    public JPanel getPannelloPrincipale() {
        return jpPrincipale;
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        jpPrincipale = new JPanel();
        jpPrincipale.setLayout(new GridLayoutManager(9, 2, new Insets(16, 16, 16, 16), 12, 10));
        jpPrincipale.setPreferredSize(new Dimension(620, 480));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, Font.BOLD, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Creazione prodotto");
        jpPrincipale.add(label1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Tutti i campi sono obbligatori.");
        jpPrincipale.add(label2, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Nome");
        jpPrincipale.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jtfNome = new JTextField();
        jtfNome.setColumns(24);
        jtfNome.setToolTipText("Nome obbligatorio, massimo 15 caratteri.");
        jpPrincipale.add(jtfNome, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, 28), null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Descrizione");
        jpPrincipale.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        jpPrincipale.add(scrollPane1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(300, 96), null, null, 0, false));
        jtaDescrizione = new JTextArea();
        jtaDescrizione.setColumns(24);
        jtaDescrizione.setLineWrap(true);
        jtaDescrizione.setRows(4);
        jtaDescrizione.setToolTipText("Descrizione obbligatoria, massimo 200 caratteri.");
        jtaDescrizione.setWrapStyleWord(true);
        scrollPane1.setViewportView(jtaDescrizione);
        final JLabel label5 = new JLabel();
        label5.setText("Prezzo (euro)");
        jpPrincipale.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jtfPrezzo = new JTextField();
        jtfPrezzo.setColumns(24);
        jtfPrezzo.setToolTipText("Prezzo maggiore di zero, ad esempio 19,99 oppure 19.99.");
        jpPrincipale.add(jtfPrezzo, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, 28), null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Quantita'");
        jpPrincipale.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jtfQuantita = new JTextField();
        jtfQuantita.setColumns(24);
        jtfQuantita.setToolTipText("Numero intero non negativo.");
        jpPrincipale.add(jtfQuantita, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, 28), null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Categoria (nome)");
        jpPrincipale.add(label7, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jtfCategoria = new JTextField();
        jtfCategoria.setColumns(24);
        jtfCategoria.setToolTipText("Nome di una categoria gia' registrata; la sua esistenza verra' verificata dal dominio.");
        jpPrincipale.add(jtfCategoria, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, 28), null, null, 0, false));
        jtaMessaggio = new JTextArea();
        jtaMessaggio.setColumns(24);
        jtaMessaggio.setEditable(false);
        jtaMessaggio.setFocusable(false);
        Font jtaMessaggioFont = UIManager.getFont("Label.font");
        if (jtaMessaggioFont != null) jtaMessaggio.setFont(jtaMessaggioFont);
        jtaMessaggio.setLineWrap(true);
        jtaMessaggio.setOpaque(false);
        jtaMessaggio.setRows(2);
        jtaMessaggio.setWrapStyleWord(true);
        jpPrincipale.add(jtaMessaggio, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 48), null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 12, 0, true, false));
        jpPrincipale.add(panel1, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jbCreaProdotto = new JButton();
        jbCreaProdotto.setText("Crea prodotto");
        panel1.add(jbCreaProdotto, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 36), null, null, 0, false));
        jbAnnulla = new JButton();
        jbAnnulla.setText("Annulla");
        panel1.add(jbAnnulla, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 36), null, null, 0, false));
    }

    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    public JComponent $$$getRootComponent$$$() {
        return jpPrincipale;
    }
}

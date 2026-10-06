package it.unina;

import it.unina.Boundary.MainForm;
import it.unina.Database.JpaUtil;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            JpaUtil.getInstance().getEntityManager().close();
        } catch (RuntimeException e) {
            Throwable causa = e;
            while (causa.getCause() != null) {
                causa = causa.getCause();
            }
            JOptionPane.showMessageDialog(null, "Impossibile collegarsi al database MySQL.\nControllare che MySQL sia avviato e i dati di connessione in\nsrc/main/resources/META-INF/persistence.xml (url, utente, password).\n\nDettaglio: " + causa.getMessage(),
                    "Database non raggiungibile", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JpaUtil.getInstance().chiudi()));
        SwingUtilities.invokeLater(() -> {
            MainForm mainForm = new MainForm();
            JFrame frame = new JFrame();
            frame.setTitle("E-commerce");
            frame.setContentPane(mainForm.$$$getRootComponent$$$());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
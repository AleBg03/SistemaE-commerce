package it.unina.Boundary;

import it.unina.Exceptions.EmailGiaInUsoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormRegistrazione {

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JButton chiudiButton;
    private JButton salvaButton;
    private String erroreCampi = "";
    private static final String CARATTERI_SPECIALI = "!@#$%^&*()_+-=[]{};:'\",.<>/?\\|";
    private static final String ERRORE_CAMPI_GENERICO = "ERRORE! Campi non validi: compila nome, cognome, e-mail e password e inserisci un'e-mail corretta.";

    public FormRegistrazione() {
        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickRegistrati();
            }
        });
    }

    public void onClickRegistrati() {
        boolean campiValidi = verificaCampiObbligatori();
        if (campiValidi) {
            String nome = textField1.getText().trim();
            String cognome = textField2.getText().trim();
            String email = textField3.getText().trim();
            String password = String.valueOf(passwordField1.getPassword());
            BUtente butente = new BUtente();
            try {
                boolean esito = butente.registrazione(nome, cognome, email, password);
                if (esito) {
                    JOptionPane.showMessageDialog(panel1,
                            "Registrazione effettuata con successo! Creato nuovo account cliente.",
                            "Registrazione", JOptionPane.INFORMATION_MESSAGE);
                    Window finestra = SwingUtilities.getWindowAncestor(panel1);
                    if (finestra != null) {
                        finestra.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(panel1,
                            "ERRORE! Registrazione non riuscita. Riprova.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } catch (EmailGiaInUsoException ex) {
                JOptionPane.showMessageDialog(panel1,
                        "ERRORE! E-mail già presente nel sistema. Inserisci un'e-mail diversa.",
                        "E-mail già in uso", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(panel1,
                    erroreCampi,
                    "Campi non validi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean verificaCampiObbligatori() {
        erroreCampi = "";
        String nome = textField1.getText().trim();
        String cognome = textField2.getText().trim();
        String email = textField3.getText().trim();
        String password = String.valueOf(passwordField1.getPassword());
        if (nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || password.isEmpty()) {
            erroreCampi = ERRORE_CAMPI_GENERICO;
            return false;
        }
        if (!email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            erroreCampi = ERRORE_CAMPI_GENERICO;
            return false;
        }
        erroreCampi = verificaPassword(password);
        return erroreCampi.isEmpty();
    }

    public String verificaPassword(String password) {
        StringBuilder errori = new StringBuilder();
        if (password.length() < 8) {
            errori.append("Password inferiore agli 8 caratteri! Deve essere una stringa compresa tra gli 8 e i 15 caratteri.\n");
        }
        if (password.length() > 15) {
            errori.append("Password superiore ai 15 caratteri! Deve essere una stringa compresa tra gli 8 e i 15 caratteri.\n");
        }
        boolean haSpeciale = false;
        for (char c : password.toCharArray()) {
            if (CARATTERI_SPECIALI.indexOf(c) >= 0) {
                haSpeciale = true;
            }
        }
        if (!haSpeciale) {
            errori.append("La password deve contenere almeno un carattere speciale.\n");
        }
        return errori.toString().trim();
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setPreferredSize(new Dimension(450, 300));
        final JLabel label1 = new JLabel();
        label1.setText("Modulo di registrazione");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Nome");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Cognome");
        panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("email");
        panel1.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("password");
        panel1.add(label5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        panel1.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField2 = new JTextField();
        panel1.add(textField2, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField3 = new JTextField();
        panel1.add(textField3, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordField1 = new JPasswordField();
        panel1.add(passwordField1, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(5, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        chiudiButton = new JButton();
        chiudiButton.setText("chiudi");
        panel1.add(chiudiButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        salvaButton = new JButton();
        salvaButton.setText("salva");
        panel1.add(salvaButton, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}

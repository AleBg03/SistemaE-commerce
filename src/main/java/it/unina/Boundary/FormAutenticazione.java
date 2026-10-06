package it.unina.Boundary;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import it.unina.Exceptions.CredenzialiErrateException;
import it.unina.Exceptions.UtenteNonTrovatoException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class FormAutenticazione {
    private JPanel jpPrincipale;
    private JTextField tfEmail;
    private JPanel jpTitolo;
    private JPanel jpEmail;
    private JPanel jpPassword;
    private JButton accediButton;
    private JPasswordField passwordField1;
    private JLabel jleffettivo;
    private JLabel jlesitoop;

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        jpPrincipale = new JPanel();
        jpPrincipale.setLayout(new GridLayoutManager(6, 7, new Insets(0, 0, 0, 0), -1, -1));
        jpPrincipale.setPreferredSize(new Dimension(450, 350));
        jpTitolo = new JPanel();
        jpTitolo.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        jpPrincipale.add(jpTitolo, new GridConstraints(0, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jpTitolo.setBorder(BorderFactory.createTitledBorder(null, "Form AUTENTICAZIONE", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        jpEmail = new JPanel();
        jpEmail.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        jpPrincipale.add(jpEmail, new GridConstraints(1, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jpEmail.setBorder(BorderFactory.createTitledBorder(null, "inserire l'email:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        jpPassword = new JPanel();
        jpPassword.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        jpPrincipale.add(jpPassword, new GridConstraints(3, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jpPassword.setBorder(BorderFactory.createTitledBorder(null, "inserire la password:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tfEmail = new JTextField();
        jpPrincipale.add(tfEmail, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        jpPrincipale.add(spacer1, new GridConstraints(2, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        jpPrincipale.add(spacer2, new GridConstraints(4, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        passwordField1 = new JPasswordField();
        jpPrincipale.add(passwordField1, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        accediButton = new JButton();
        accediButton.setText("Accedi");
        jpPrincipale.add(accediButton, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jleffettivo = new JLabel();
        jleffettivo.setText("attesa esito");
        jpPrincipale.add(jleffettivo, new GridConstraints(5, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jlesitoop = new JLabel();
        jlesitoop.setText("esito operazione");
        jpPrincipale.add(jlesitoop, new GridConstraints(5, 0, 1, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return jpPrincipale;
    }

    public FormAutenticazione() {
        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickAccedi();
            }
        });
    }

    public void onClickAccedi() {
        String email = tfEmail.getText().trim();
        char[] caratteriPassword = passwordField1.getPassword();
        String password = String.valueOf(caratteriPassword);

        try {
            if (!verificaFormatoDati(email, password)) {
                return;
            }
            BUtente bUtente = new BUtente();
            String nomeRuolo = bUtente.autenticazione(email, password);
            if ("CLIENTE".equals(nomeRuolo)) {
                DashboardCliente dashboard = new DashboardCliente(email);
                mostraDashboard("Dashboard Cliente", dashboard.$$$getRootComponent$$$());
            } else if ("AMMINISTRATORE".equals(nomeRuolo)) {
                DashboardAmministratore dashboard = new DashboardAmministratore();
                mostraDashboard("Dashboard Amministratore", dashboard.getPannelloPrincipale());
            }
            jleffettivo.setText("Accesso eseguito.");
            jleffettivo.setForeground(Color.GREEN);
            Window finestra = SwingUtilities.getWindowAncestor(jpPrincipale);
            if (finestra != null) {
                finestra.dispose();
            }
        } catch (UtenteNonTrovatoException | CredenzialiErrateException ex) {
            jleffettivo.setText("Accesso negato.");
            jleffettivo.setForeground(Color.RED);
            jlesitoop.setText(ex.getMessage());
        } finally {
            Arrays.fill(caratteriPassword, '\0');
        }
    }

    private void mostraDashboard(String aTitolo, JComponent aContenuto) {
        JFrame frame = new JFrame();
        frame.setTitle(aTitolo);
        frame.setContentPane(aContenuto);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public boolean verificaFormatoDati(String email, String password) {
        if (email == null || email.isBlank() || password == null || password.length() == 0) {
            JOptionPane.showMessageDialog(
                    jleffettivo,
                    "Inserisci e-mail e password.",
                    "Dati mancanti",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (email.indexOf('@') < 0) {
            JOptionPane.showMessageDialog(
                    jleffettivo,
                    "L'e-mail deve contenere il carattere @.",
                    "E-mail non valida",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (password.length() < 8 || password.length() > 15) {
            JOptionPane.showMessageDialog(
                    jleffettivo,
                    "La password deve contenere da 8 a 15 caratteri.",
                    "Password non valida",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (!contieneCarattereSpeciale(password)) {
            JOptionPane.showMessageDialog(
                    jleffettivo,
                    "La password deve contenere almeno un carattere speciale.",
                    "Password non valida",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        return true;
    }

    private boolean contieneCarattereSpeciale(String password) {
        String caratteriSpeciali = "!@#$%^&*()_+-=[]{};:'\",.<>/?\\|";

        for (char carattere : password.toCharArray()) {
            if (caratteriSpeciali.indexOf(carattere) >= 0) {
                return true;
            }
        }

        return false;
    }

}

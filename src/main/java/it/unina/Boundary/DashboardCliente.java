package it.unina.Boundary;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class DashboardCliente {
    private JPanel panel1;
    private JLabel lblBenvenuto;
    private JButton btnProfilo;
    private JButton btnCatalogo;
    private JButton btnCarrello;
    private JButton btnStoricoOrdini;
    private final String emailCliente;

    public DashboardCliente(String aEmail) {
        this.emailCliente = aEmail;
        lblBenvenuto.setText("Benvenuto, " + aEmail);
        btnCatalogo.addActionListener(e -> onClickCatalogo());
        btnStoricoOrdini.addActionListener(e -> onClickStoricoOrdini());
    }

    public void onClickProfilo() {
        throw new UnsupportedOperationException();
    }

    public void onClickStoricoOrdini() {
        FormStoricoOrdini form = new FormStoricoOrdini(emailCliente);
        mostraInFinestra("Storico ordini", form.$$$getRootComponent$$$());
    }

    public void onClickCatalogo() {
        FormCatalogo form = new FormCatalogo(emailCliente);
        mostraInFinestra("Catalogo prodotti", form.$$$getRootComponent$$$());
    }

    public void onClickCarrello() {
        throw new UnsupportedOperationException();
    }

    private void mostraInFinestra(String aTitolo, JComponent aContenuto) {
        JFrame frame = new JFrame();
        frame.setTitle(aTitolo);
        frame.setContentPane(aContenuto);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(SwingUtilities.getWindowAncestor(panel1));
        frame.setVisible(true);
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(10, 10, 10, 10), 10, 10));
        panel1.setPreferredSize(new Dimension(460, 260));
        lblBenvenuto = new JLabel();
        lblBenvenuto.setText("Benvenuto");
        panel1.add(lblBenvenuto, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnProfilo = new JButton();
        btnProfilo.setText("Il mio profilo");
        panel1.add(btnProfilo, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 70), null, 0, false));
        btnCatalogo = new JButton();
        btnCatalogo.setText("Catalogo prodotti");
        panel1.add(btnCatalogo, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 70), null, 0, false));
        btnCarrello = new JButton();
        btnCarrello.setText("Carrello");
        panel1.add(btnCarrello, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 70), null, 0, false));
        btnStoricoOrdini = new JButton();
        btnStoricoOrdini.setText("Storico ordini");
        panel1.add(btnStoricoOrdini, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 70), null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}

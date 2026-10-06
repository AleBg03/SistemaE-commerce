package it.unina.Boundary;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class FormCarrello {
    private JPanel panel1;
    private JTable tblCarrello;
    private JLabel lblTotale;
    private JLabel lblIndirizzo;
    private JLabel lblMessaggio;
    private JButton btnRiepilogo;
    private JButton btnNuovoIndirizzo;
    private JButton btnConfermaOrdine;

    public void onClickRiepilogo() {
        throw new UnsupportedOperationException();
    }

    public void onClickNuovoIndirizzo() {
        throw new UnsupportedOperationException();
    }

    public void onClickConfermaOrdine() {
        throw new UnsupportedOperationException();
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(5, 3, new Insets(10, 10, 10, 10), 6, 6));
        panel1.setPreferredSize(new Dimension(660, 440));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(620, 240), null, 0, false));
        tblCarrello = new JTable();
        scrollPane1.setViewportView(tblCarrello);
        lblTotale = new JLabel();
        lblTotale.setText("Totale: 0,00 €");
        panel1.add(lblTotale, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblIndirizzo = new JLabel();
        lblIndirizzo.setText("Indirizzo di spedizione: -");
        panel1.add(lblIndirizzo, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMessaggio = new JLabel();
        lblMessaggio.setText(" ");
        panel1.add(lblMessaggio, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnRiepilogo = new JButton();
        btnRiepilogo.setText("Aggiorna riepilogo");
        panel1.add(btnRiepilogo, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnNuovoIndirizzo = new JButton();
        btnNuovoIndirizzo.setText("Nuovo indirizzo...");
        panel1.add(btnNuovoIndirizzo, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnConfermaOrdine = new JButton();
        btnConfermaOrdine.setText("Conferma ordine");
        panel1.add(btnConfermaOrdine, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}

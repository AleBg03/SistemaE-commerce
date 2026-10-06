package it.unina.Boundary;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Locale;

public class FormCatalogo {
    private static final int R_ID = 0;
    private static final int R_NOME = 1;
    private static final int R_DESCRIZIONE = 2;
    private static final int R_CATEGORIA = 3;
    private static final int R_PREZZO = 4;
    private static final int R_QUANTITA = 5;
    private static final int R_DISPONIBILE = 6;
    private static final String[] COLONNE = {"ID", "Prodotto", "Descrizione", "Categoria", "Prezzo", "Disponibili", "Stato"};

    private JPanel panel1;
    private JTextField tfRicerca;
    private JButton btnCerca;
    private JButton btnOfferte;
    private JTable tblProdotti;
    private JLabel lblMessaggio;
    private JTextField tfQuantita;
    private JButton btnAggiungiAlCarrello;
    private final BCliente bCliente;
    private final DefaultTableModel modello;
    public FormCatalogo(String aEmailCliente) {
        bCliente = new BCliente(aEmailCliente);
        modello = new DefaultTableModel(COLONNE, 0) {
            @Override
            public boolean isCellEditable(int riga, int colonna) {
                return false;
            }
        };
        tblProdotti.setModel(modello);
        tblProdotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        int[] larghezze = {40, 120, 210, 110, 110, 80, 110};
        for (int i = 0; i < larghezze.length; i++) {
            tblProdotti.getColumnModel().getColumn(i).setPreferredWidth(larghezze[i]);
        }
        mostraElencoProdotti(bCliente.consultaCatalogo());
    }

    public void onClickCerca() {
        throw new UnsupportedOperationException();
    }

    public void onClickOfferte() {
        throw new UnsupportedOperationException();
    }

    public void onClickAggiungiAlCarrello() {
        throw new UnsupportedOperationException();
    }

    private void mostraElencoProdotti(Object[][] aRighe) {
        modello.setRowCount(0);
        for (Object[] r : aRighe) {
            String prezzo = String.format(Locale.ITALY, "%.2f €", (Float) r[R_PREZZO]);
            modello.addRow(new Object[]{r[R_ID], r[R_NOME], r[R_DESCRIZIONE], r[R_CATEGORIA], prezzo, r[R_QUANTITA],
                    (Boolean) r[R_DISPONIBILE] ? "Disponibile" : "Non disponibile"});
        }
        lblMessaggio.setText(aRighe.length > 0 ? " " : "Il catalogo è vuoto.");
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 4, new Insets(10, 10, 10, 10), 6, 6));
        panel1.setPreferredSize(new Dimension(780, 430));
        final JLabel label1 = new JLabel();
        label1.setText("Cerca:");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfRicerca = new JTextField();
        panel1.add(tfRicerca, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, -1), null, 0, false));
        btnCerca = new JButton();
        btnCerca.setText("Cerca");
        panel1.add(btnCerca, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnOfferte = new JButton();
        btnOfferte.setText("Offerte");
        panel1.add(btnOfferte, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(740, 270), null, 0, false));
        tblProdotti = new JTable();
        scrollPane1.setViewportView(tblProdotti);
        lblMessaggio = new JLabel();
        lblMessaggio.setText(" ");
        panel1.add(lblMessaggio, new GridConstraints(2, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Quantità:");
        panel1.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfQuantita = new JTextField();
        tfQuantita.setText("1");
        panel1.add(tfQuantita, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, -1), null, 0, false));
        btnAggiungiAlCarrello = new JButton();
        btnAggiungiAlCarrello.setText("Aggiungi al carrello");
        panel1.add(btnAggiungiAlCarrello, new GridConstraints(3, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}

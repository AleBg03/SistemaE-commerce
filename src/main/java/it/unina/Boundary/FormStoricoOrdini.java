package it.unina.Boundary;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormStoricoOrdini {
    private static final int R_ID = 0;
    private static final int R_DATA = 1;
    private static final int R_STATO = 2;
    private static final int R_TOTALE = 3;
    private static final String[] COLONNE = {"N. ordine", "Data", "Stato", "Totale"};

    private JPanel panel1;
    private JTable tblOrdini;
    private JLabel lblMessaggio;
    private JButton btnDettaglio;
    private JButton btnAnnullaOrdine;
    private final BCliente bCliente;
    private final DefaultTableModel modello;

    public FormStoricoOrdini(String aEmailCliente) {
        bCliente = new BCliente(aEmailCliente);
        modello = new DefaultTableModel(COLONNE, 0) {
            @Override
            public boolean isCellEditable(int riga, int colonna) {
                return false;
            }
        };
        tblOrdini.setModel(modello);
        tblOrdini.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Object[][] righe = bCliente.consultaStoricoOrdini();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Object[] r : righe) {
            modello.addRow(new Object[]{r[R_ID], formato.format((Date) r[R_DATA]), r[R_STATO], String.format(Locale.ITALY, "%.2f €", (Float) r[R_TOTALE])});
        }
        lblMessaggio.setText(righe.length > 0 ? " " : "Non hai ancora effettuato alcun ordine.");
    }

    public void onClickDettaglio() {
        throw new UnsupportedOperationException();
    }

    public void onClickAnnullaOrdine() {
        throw new UnsupportedOperationException();
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 3, new Insets(10, 10, 10, 10), 6, 6));
        panel1.setPreferredSize(new Dimension(660, 400));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(620, 270), null, 0, false));
        tblOrdini = new JTable();
        scrollPane1.setViewportView(tblOrdini);
        lblMessaggio = new JLabel();
        lblMessaggio.setText(" ");
        panel1.add(lblMessaggio, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnDettaglio = new JButton();
        btnDettaglio.setText("Dettaglio ordine");
        panel1.add(btnDettaglio, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnAnnullaOrdine = new JButton();
        btnAnnullaOrdine.setText("Annulla ordine");
        panel1.add(btnAnnullaOrdine, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}

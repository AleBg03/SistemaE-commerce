package it.unina.Boundary;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormOrdiniRicevuti {
    private static final int R_ID = 0;
    private static final int R_CLIENTE = 1;
    private static final int R_DATA = 2;
    private static final int R_STATO = 3;
    private static final int R_TOTALE = 4;
    private static final String[] COLONNE = {"N. ordine", "Cliente", "Data", "Stato", "Totale"};

    private JPanel panel1;
    private JTable tblOrdini;
    private JLabel lblMessaggio;
    private final BAmministratore bAmministratore;
    private final DefaultTableModel modello;

    public FormOrdiniRicevuti() {
        bAmministratore = new BAmministratore();
        modello = new DefaultTableModel(COLONNE, 0) {
            @Override
            public boolean isCellEditable(int riga, int colonna) {
                return false;
            }
        };
        tblOrdini.setModel(modello);
        Object[][] righe = bAmministratore.consultaOrdiniRicevuti();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Object[] r : righe) {
            modello.addRow(new Object[]{r[R_ID], r[R_CLIENTE], formato.format((Date) r[R_DATA]), r[R_STATO], String.format(Locale.ITALY, "%.2f €", (Float) r[R_TOTALE])});
        }
        lblMessaggio.setText(righe.length > 0 ? " " : "Nessun ordine ricevuto.");
    }

    public void onClickAggiornaStato() {
        throw new UnsupportedOperationException();
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), 6, 6));
        panel1.setPreferredSize(new Dimension(700, 380));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(660, 300), null, 0, false));
        tblOrdini = new JTable();
        scrollPane1.setViewportView(tblOrdini);
        lblMessaggio = new JLabel();
        lblMessaggio.setText(" ");
        panel1.add(lblMessaggio, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}

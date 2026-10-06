package it.unina.Boundary;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardAmministratore {
    private JButton CATALOGOButton;
    private JButton MONITORAGGIOVENDITEButton;
    private JButton ORDINIRICEVUTIButton;
    private JPanel jptitolo;

    public DashboardAmministratore() {
        MONITORAGGIOVENDITEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ORDINIRICEVUTIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickOrdiniRicevuti();
            }
        });
        CATALOGOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickGestioneCatalogo();
            }
        });
    }

    public void onClickGestioneCatalogo() {
        FormGestioneProdotto form = new FormGestioneProdotto();
        JFrame finestraCatalogo = new JFrame("Gestione catalogo");
        finestraCatalogo.setContentPane(form.getPannelloPrincipale());

        finestraCatalogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        finestraCatalogo.pack();
        finestraCatalogo.setLocationRelativeTo(SwingUtilities.getWindowAncestor(CATALOGOButton));
        finestraCatalogo.setVisible(true);
    }

    public void onClickOrdiniRicevuti() {
        FormOrdiniRicevuti form = new FormOrdiniRicevuti();
        JFrame finestraOrdini = new JFrame("Ordini ricevuti");
        finestraOrdini.setContentPane(form.$$$getRootComponent$$$());

        finestraOrdini.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        finestraOrdini.pack();
        finestraOrdini.setLocationRelativeTo(SwingUtilities.getWindowAncestor(ORDINIRICEVUTIButton));
        finestraOrdini.setVisible(true);
    }

    public void onClickMonitoraggio() {
        throw new UnsupportedOperationException();
    }

    public JComponent getPannelloPrincipale() {
        return (JComponent) jptitolo.getParent();
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 3, new Insets(12, 12, 12, 12), 12, 12, true, false));
        panel1.setPreferredSize(new Dimension(720, 400));
        jptitolo = new JPanel();
        jptitolo.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(jptitolo, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jptitolo.setBorder(BorderFactory.createTitledBorder(null, "DASHBOARD AMMINISTRATORE", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(panel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MONITORAGGIOVENDITEButton = new JButton();
        MONITORAGGIOVENDITEButton.setText("MONITORAGGIO VENDITE");
        panel2.add(MONITORAGGIOVENDITEButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(210, 48), new Dimension(210, 48), new Dimension(-1, 48), 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(panel3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ORDINIRICEVUTIButton = new JButton();
        ORDINIRICEVUTIButton.setText("ORDINI RICEVUTI");
        panel3.add(ORDINIRICEVUTIButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(210, 48), new Dimension(210, 48), new Dimension(-1, 48), 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(panel4, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CATALOGOButton = new JButton();
        CATALOGOButton.setText("CATALOGO E PRODOTTI");
        panel4.add(CATALOGOButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(210, 48), new Dimension(210, 48), new Dimension(-1, 48), 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }
}

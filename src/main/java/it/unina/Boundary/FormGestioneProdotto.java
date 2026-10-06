package it.unina.Boundary;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormGestioneProdotto {
    private JPanel jpPrincipale;
    private JLabel jptitolo;
    private JButton jbCreazioneProdotto;
    private JButton jbModificaProd;
    private JButton jbOfferta;
    private JButton jbDisponibilita;
    private JButton jbRimuovi;

    public FormGestioneProdotto() {
        if (jpPrincipale == null) {
            jpPrincipale = new JPanel();
            jpPrincipale.setPreferredSize(new Dimension(500, 400));
        }
        jbCreazioneProdotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickCrea();
            }
        });
    }
    private void onClickCrea() {
        FormCreazioneProdotti form = new FormCreazioneProdotti();
        JFrame finestraCreazione = new JFrame("Creazione prodotto");
        finestraCreazione.setContentPane(form.getPannelloPrincipale());
        finestraCreazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        finestraCreazione.pack();
        finestraCreazione.setLocationRelativeTo(SwingUtilities.getWindowAncestor(jpPrincipale));
        finestraCreazione.setVisible(true);
    }

    public JPanel getPannelloPrincipale() {
        return jpPrincipale;
    }

    public void onClickModifica() {
        throw new UnsupportedOperationException();
    }

    public void onClickDisponibilità() {
        throw new UnsupportedOperationException();
    }

    public void onClickRimuovi() {
        throw new UnsupportedOperationException();
    }

    public void onClickOfferta() {
        throw new UnsupportedOperationException();
    }

    public boolean verificaCampi() {
        throw new UnsupportedOperationException();
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        jpPrincipale = new JPanel();
        jpPrincipale.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        jpPrincipale.setPreferredSize(new Dimension(500, 400));
        jptitolo = new JLabel();
        jptitolo.setText("Gestione Prodotti");
        jpPrincipale.add(jptitolo, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jbOfferta = new JButton();
        jbOfferta.setText("aggiungi offerta");
        jpPrincipale.add(jbOfferta, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 100), new Dimension(200, 100), null, 0, false));
        jbCreazioneProdotto = new JButton();
        jbCreazioneProdotto.setText("aggiungi nuovo un prodotto");
        jpPrincipale.add(jbCreazioneProdotto, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 100), new Dimension(200, 100), null, 0, false));
        jbModificaProd = new JButton();
        jbModificaProd.setText("modifica un prodotto");
        jpPrincipale.add(jbModificaProd, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 100), new Dimension(200, 100), null, 0, false));
        jbDisponibilita = new JButton();
        jbDisponibilita.setText("verifica disponibilità");
        jpPrincipale.add(jbDisponibilita, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 100), new Dimension(200, 100), null, 0, false));
        jbRimuovi = new JButton();
        jbRimuovi.setText("rimuovi un prodotto");
        jpPrincipale.add(jbRimuovi, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 100), new Dimension(200, 100), null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return jpPrincipale;
    }

}

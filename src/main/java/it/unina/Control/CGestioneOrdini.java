package it.unina.Control;

import it.unina.Entity.E_commerce;
import it.unina.Entity.Ordine;

import java.util.List;

public class CGestioneOrdini {
    private CServizioMessaggistica gestoreNotifiche;

    public CGestioneOrdini() {
        this(new CServizioMessaggistica());
    }

    public CGestioneOrdini(CServizioMessaggistica aGestoreNotifiche) {
        this.gestoreNotifiche = aGestoreNotifiche;
    }

    public Object[][] gestisciConsultazioneOrdiniRicevuti() {
        List<Ordine> ordini = E_commerce.getInstance().getOrdiniRicevuti();
        Object[][] righe = new Object[ordini.size()][];
        for (int i = 0; i < ordini.size(); i++) {
            Ordine o = ordini.get(i);
            righe[i] = new Object[]{o.getId(), o.getCliente().getEmail(), o.getDataCreazione(), o.getStato().name(), o.getTotComplessivo()};
        }
        return righe;
    }

    public Object[][] gestisciConsultazioneStoricoOrdini(String aEmail) {
        List<Ordine> ordini = E_commerce.getInstance().consultaStoricoOrdini(aEmail);
        Object[][] righe = new Object[ordini.size()][];
        for (int i = 0; i < ordini.size(); i++) {
            Ordine o = ordini.get(i);
            righe[i] = new Object[]{o.getId(), o.getDataCreazione(), o.getStato().name(), o.getTotComplessivo()};
        }
        return righe;
    }

    public boolean gestisciAggiuntaAlCarrello(String aEmail, Long aIdProdotto, int aQuantita) {
        throw new UnsupportedOperationException();
    }

    public Object[][] gestisciRiepilogoOrdine(String aEmail) {
        throw new UnsupportedOperationException();
    }

    public long gestisciConfermaOrdine(String aEmail) {
        throw new UnsupportedOperationException();
    }

    public long gestisciConfermaOrdine(String aEmail, DatiIndirizzo aNuovoIndirizzo) {
        throw new UnsupportedOperationException();
    }

    public Object[][] gestisciConsultazioneDettaglioOrdine(String aEmail, Long aIdOrdine) {
        throw new UnsupportedOperationException();
    }

    public boolean gestisciAnnullamentoOrdine(String aEmail, Long aIdOrdine) {
        throw new UnsupportedOperationException();
    }

    public String gestisciAggiornamentoStatoOrdine(Long aIdOrdine) {
        throw new UnsupportedOperationException();
    }

    public void InviaNotifica(Ordine aOrdine) {
        throw new UnsupportedOperationException();
    }
}

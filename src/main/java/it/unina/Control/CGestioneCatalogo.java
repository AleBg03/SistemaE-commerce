package it.unina.Control;

import it.unina.Entity.E_commerce;
import it.unina.Entity.Prodotto;

import java.util.List;

public class CGestioneCatalogo {

    public Object[][] gestisciConsultazioneCatalogo() {
        List<Prodotto> prodotti = E_commerce.getInstance().getCatalogo();
        Object[][] righe = new Object[prodotti.size()][];
        for (int i = 0; i < prodotti.size(); i++) {
            Prodotto p = prodotti.get(i);
            righe[i] = new Object[]{p.getId(), p.getNome(), p.getDescrizione(), p.getCategoria().getNome(), p.getPrezzo(), p.getQtaDisponibile(), p.getDisponibile()};
        }
        return righe;
    }

    public boolean gestisciCreazioneProdotto(DatiProdotto aDati) {
        E_commerce.getInstance().creaProdotto(aDati.nome(), aDati.descrizione(), aDati.prezzo(), aDati.quantita(), aDati.nomeCategoria());
        return true;
    }

    public Object[][] gestisciRicercaProdotto(String aTermine) {
        throw new UnsupportedOperationException();
    }

    public Object[][] gestisciConsultazioneOfferte() {
        throw new UnsupportedOperationException();
    }

    public Object[][] gestisciElencoCategorie() {
        throw new UnsupportedOperationException();
    }

    public boolean gestisciModificaProdotto(Long aIdProdotto, DatiProdotto aDati) {
        throw new UnsupportedOperationException();
    }

    public boolean gestisciDisponibilita(Long aIdProdotto, boolean aDisponibile) {
        throw new UnsupportedOperationException();
    }

    public boolean gestisciRimozioneProdotto(Long aIdProdotto) {
        throw new UnsupportedOperationException();
    }

    public boolean gestisciAggiuntaOfferta(Long aIdProdotto) {
        throw new UnsupportedOperationException();
    }
}

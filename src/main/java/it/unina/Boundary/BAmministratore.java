package it.unina.Boundary;

import it.unina.Control.CGestioneCatalogo;
import it.unina.Control.CGestioneOrdini;
import it.unina.Control.DatiProdotto;

import java.util.Date;

public class BAmministratore {

    public boolean creaProdotto(DatiProdotto aDati) {
        return new CGestioneCatalogo().gestisciCreazioneProdotto(aDati);
    }

    public Object[][] consultaOrdiniRicevuti() {
        return new CGestioneOrdini().gestisciConsultazioneOrdiniRicevuti();
    }

    public void onClickMonitoraggio() {
        throw new UnsupportedOperationException();
    }

    public Object[][] consultaCatalogo() {
        throw new UnsupportedOperationException();
    }

    public Object[][] elencoCategorie() {
        throw new UnsupportedOperationException();
    }

    public boolean modificaProdotto(Long aIdProdotto, DatiProdotto aDati) {
        throw new UnsupportedOperationException();
    }

    public boolean impostaDisponibilita(Long aIdProdotto, boolean aDisponibile) {
        throw new UnsupportedOperationException();
    }

    public boolean rimuoviProdotto(Long aIdProdotto) {
        throw new UnsupportedOperationException();
    }

    public boolean aggiungiOfferta(Long aIdProdotto) {
        throw new UnsupportedOperationException();
    }

    public String aggiornaStatoOrdine(Long aIdOrdine) {
        throw new UnsupportedOperationException();
    }

    public long contaOrdini(Date aDataInizio, Date aDataFine) {
        throw new UnsupportedOperationException();
    }

    public Object[][] prodottiPiùVenduti(Date aDataInizio, Date aDataFine) {
        throw new UnsupportedOperationException();
    }

    public Object[][] quantitàResidue() {
        throw new UnsupportedOperationException();
    }
}

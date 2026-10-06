package it.unina.Boundary;

import it.unina.Control.CGestioneCatalogo;
import it.unina.Control.CGestioneOrdini;
import it.unina.Control.DatiIndirizzo;

public class BCliente {
    private String emailCliente;

    public BCliente(String aEmailCliente) {
        this.emailCliente = aEmailCliente;
    }

    public Object[][] consultaCatalogo() {
        return new CGestioneCatalogo().gestisciConsultazioneCatalogo();
    }

    public Object[][] consultaStoricoOrdini() {
        return new CGestioneOrdini().gestisciConsultazioneStoricoOrdini(emailCliente);
    }

    public String[] visualizzaProfilo() {
        throw new UnsupportedOperationException();
    }

    public boolean modificaProfilo(String aNome, String aCognome, String aImmagineProfilo) {
        throw new UnsupportedOperationException();
    }

    public boolean modificaIndirizzo(DatiIndirizzo aDatiIndirizzo) {
        throw new UnsupportedOperationException();
    }

    public Object[][] ricercaProdotto(String aTermine) {
        throw new UnsupportedOperationException();
    }

    public Object[][] consultaOfferte() {
        throw new UnsupportedOperationException();
    }

    public boolean aggiungiAlCarrello(Long aIdProdotto, int aQuantità) {
        throw new UnsupportedOperationException();
    }

    public Object[][] richiediRiepilogoOrdine() {
        throw new UnsupportedOperationException();
    }

    public void confermaOrdine() {
        throw new UnsupportedOperationException();
    }

    public void confermaOrdine(DatiIndirizzo aNuovoIndirizzo) {
        throw new UnsupportedOperationException();
    }

    public boolean annullaOrdine(Long aIdOrdine) {
        throw new UnsupportedOperationException();
    }

    public Object[][] consultaDettaglioOrdine(Long aIdOrdine) {
        throw new UnsupportedOperationException();
    }
}

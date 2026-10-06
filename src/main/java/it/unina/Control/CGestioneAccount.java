package it.unina.Control;

import it.unina.Entity.E_commerce;
import it.unina.Entity.Utente_Registrato;

public class CGestioneAccount {
    public String gestisciAutenticazione(String aEmail, String aPassword) {
        Utente_Registrato utente = E_commerce.getInstance().autentica(aEmail, aPassword);
        int ruolo = utente.getRuolo();
        if (ruolo == Utente_Registrato.RUOLO_AMMINISTRATORE) {
            return "AMMINISTRATORE";
        }
        return "CLIENTE";
    }

    public boolean gestisciRegistrazione(String aNome, String aCognome, String aEmail, String aPassword) {
        E_commerce.getInstance().registraCliente(aNome, aCognome, aEmail, aPassword);
        return true;
    }

    public String[] gestisciVisualizzazioneProfilo(String aEmail) {
        throw new UnsupportedOperationException();
    }

    public boolean gestisciModificaProfilo(String aEmail, String aNome, String aCognome, String aImmagineProfilo) {
        throw new UnsupportedOperationException();
    }

    public boolean gestisciModificaIndirizzo(String aEmail, DatiIndirizzo aIndirizzo) {
        throw new UnsupportedOperationException();
    }
}

package it.unina.Boundary;

import it.unina.Control.CGestioneAccount;

public class BUtente {

    public boolean registrazione(String aNome, String aCognome, String aEmail, String aPassword) {
        return new CGestioneAccount().gestisciRegistrazione(aNome, aCognome, aEmail, aPassword);
    }

    public String autenticazione(String aEmail, String aPassword) {
        return new CGestioneAccount().gestisciAutenticazione(aEmail, aPassword);
    }
}

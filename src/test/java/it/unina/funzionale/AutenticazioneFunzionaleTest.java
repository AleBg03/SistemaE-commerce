package it.unina.funzionale;

import it.unina.Entity.Amministratore;
import it.unina.Entity.Cliente;
import it.unina.supporto.PersistenzaInMemoria;
import it.unina.supporto.ProvaGrafica;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(30)
@DisplayName("Autenticazione - test funzionali (piano di test 3.2, Category Partition)")
class AutenticazioneFunzionaleTest {
    private static final String TITOLO_AUTENTICAZIONE = "Autenticazione";
    private static final String TITOLO_DASHBOARD_CLIENTE = "Dashboard Cliente";
    private static final String TITOLO_DASHBOARD_AMMINISTRATORE = "Dashboard Amministratore";

    private PersistenzaInMemoria db;
    private ProvaGrafica grafica;
    private FormAutenticazione form;
    private JFrame finestra;

    @BeforeEach
    void preparaScenario() {
        ProvaGrafica.richiedeSchermo();
        db = PersistenzaInMemoria.installa();
        db.aggiungi(new Cliente("Mario", "Rossi", "mario.rossi@email.com", "Password123!"));
        db.aggiungi(new Amministratore("admin@email.com", "AdminPass123!", 1));
        grafica = new ProvaGrafica();
        form = new FormAutenticazione();
        finestra = grafica.ospita(TITOLO_AUTENTICAZIONE, form.$$$getRootComponent$$$());
    }

    @AfterEach
    void ripulisci() {
        if (grafica != null) {
            grafica.close();
        }
        PersistenzaInMemoria.ripristina();
    }

    private void accedi(String aEmail, String aPassword) {
        grafica.scrivi(form, "tfEmail", aEmail);
        grafica.scrivi(form, "passwordField1", aPassword);
        grafica.premi(form, "accediButton");
    }

    private void verificaAccessoRiuscito(String aTitoloDashboard) {
        assertTrue(grafica.finestraVisibile(aTitoloDashboard).isPresent(), "deve aprirsi la finestra \"" + aTitoloDashboard + "\"");
        assertTrue(grafica.dialoghi().isEmpty(), "nessun messaggio d'errore");
        assertEquals("Accesso eseguito.", grafica.testo(form, "jleffettivo"));
        assertFalse(finestra.isShowing(), "a accesso riuscito la form di autenticazione si chiude");
    }

    private void verificaAutenticazioneNonEffettuata() {
        assertFalse(grafica.finestraVisibile(TITOLO_DASHBOARD_CLIENTE).isPresent(), "non deve aprirsi la dashboard Cliente");
        assertFalse(grafica.finestraVisibile(TITOLO_DASHBOARD_AMMINISTRATORE).isPresent(), "non deve aprirsi la dashboard Amministratore");
        assertTrue(finestra.isShowing(), "la form di autenticazione resta aperta");
    }

    private void verificaDatiNonValidi(String... aFrammentiAttesi) {
        assertEquals(1, grafica.dialoghi().size(), "deve comparire un solo messaggio all'Utente");
        String messaggio = grafica.dialoghi().get(0).messaggio().toLowerCase();
        for (String frammento : aFrammentiAttesi) {
            assertTrue(messaggio.contains(frammento.toLowerCase()), "il messaggio \"" + messaggio + "\" dovrebbe contenere \"" + frammento + "\"");
        }
        verificaAutenticazioneNonEffettuata();
    }

    @Test
    @DisplayName("TC1 - Credenziali corrette, ruolo Cliente: accesso effettuato, dashboard Cliente")
    void tc1_clienteCredenzialiCorrette() {
        accedi("mario.rossi@email.com", "Password123!");

        verificaAccessoRiuscito(TITOLO_DASHBOARD_CLIENTE);
        assertFalse(grafica.finestraVisibile(TITOLO_DASHBOARD_AMMINISTRATORE).isPresent());
    }

    @Test
    @DisplayName("TC2 - Credenziali corrette, ruolo Amministratore: accesso effettuato, dashboard Amministratore")
    void tc2_amministratoreCredenzialiCorrette() {
        accedi("admin@email.com", "AdminPass123!");

        verificaAccessoRiuscito(TITOLO_DASHBOARD_AMMINISTRATORE);
        assertFalse(grafica.finestraVisibile(TITOLO_DASHBOARD_CLIENTE).isPresent());
    }

    @Test
    @DisplayName("TC3 - E-mail senza simbolo @: dati non validi, autenticazione non effettuata")
    void tc3_emailSenzaChiocciola() {
        accedi("mario.rossi.email.com", "Password123!");

        verificaDatiNonValidi("e-mail", "@");
    }

    @Test
    @DisplayName("TC4 - E-mail vuota: dati non validi, autenticazione non effettuata")
    void tc4_emailVuota() {
        accedi("", "Password123!");

        verificaDatiNonValidi("e-mail");
    }

    @Test
    @DisplayName("TC5 - Password di lunghezza inferiore a 8 caratteri: dati non validi, autenticazione non effettuata")
    void tc5_passwordTroppoCorta() {
        accedi("mario.rossi@email.com", "Pas123!");

        verificaDatiNonValidi("password", "8", "15", "caratteri");
    }

    @Test
    @DisplayName("TC6 - Password di lunghezza superiore a 15 caratteri: dati non validi, autenticazione non effettuata")
    void tc6_passwordTroppoLunga() {
        accedi("mario.rossi@email.com", "Password12345678!");

        verificaDatiNonValidi("password", "8", "15", "caratteri");
    }

    @Test
    @DisplayName("TC7 - Password senza carattere speciale: dati non validi, autenticazione non effettuata")
    void tc7_passwordSenzaCarattereSpeciale() {
        accedi("mario.rossi@email.com", "Password123");

        verificaDatiNonValidi("password", "carattere speciale");
    }

    @Test
    @DisplayName("TC8 - Password vuota: dati non validi, autenticazione non effettuata")
    void tc8_passwordVuota() {
        accedi("mario.rossi@email.com", "");

        verificaDatiNonValidi("password");
    }

    @Test
    @DisplayName("TC9 - E-mail valida ma non associata ad alcun account: credenziali errate, autenticazione non effettuata")
    void tc9_emailInesistente() {
        accedi("utente.inesistente@email.com", "Password123!");

        assertTrue(grafica.dialoghi().isEmpty(), "l'esito compare nella form, non in una finestra di dialogo");
        assertEquals("Credenziali errate! E-mail o password non corrette.", grafica.testo(form, "jlesitoop"));
        assertEquals("Accesso negato.", grafica.testo(form, "jleffettivo"));
        verificaAutenticazioneNonEffettuata();
    }
}

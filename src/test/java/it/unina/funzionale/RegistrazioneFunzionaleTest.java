package it.unina.funzionale;

import it.unina.Boundary.BUtente;
import it.unina.Boundary.FormRegistrazione;
import it.unina.Entity.Cliente;
import it.unina.Entity.Utente_Registrato;
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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(30)
@DisplayName("Registrazione - test funzionali (piano di test 3.1, Category Partition)")
class RegistrazioneFunzionaleTest {
    private static final String EMAIL_GIA_REGISTRATA = "mariorossi@mail.com";

    private PersistenzaInMemoria db;
    private ProvaGrafica grafica;
    private FormRegistrazione form;
    private JFrame finestra;

    @BeforeEach
    void preparaScenario() {
        ProvaGrafica.richiedeSchermo();
        db = PersistenzaInMemoria.installa();
        db.aggiungi(new Cliente("Mario", "Rossi", EMAIL_GIA_REGISTRATA, "Password123!"));
        grafica = new ProvaGrafica();
        form = new FormRegistrazione();
        finestra = grafica.ospita("Registrazione", form.$$$getRootComponent$$$());
    }

    @AfterEach
    void ripulisci() {
        if (grafica != null) {
            grafica.close();
        }
        PersistenzaInMemoria.ripristina();
    }

    private void registra(String aNome, String aCognome, String aEmail, String aPassword) {
        grafica.scrivi(form, "textField1", aNome);
        grafica.scrivi(form, "textField2", aCognome);
        grafica.scrivi(form, "textField3", aEmail);
        grafica.scrivi(form, "passwordField1", aPassword);
        grafica.premi(form, "salvaButton");
    }

    private String unicoMessaggio() {
        assertEquals(1, grafica.dialoghi().size(), "deve comparire un solo messaggio all'Utente");
        return grafica.dialoghi().get(0).messaggio();
    }

    private void verificaRifiuto(String... aFrammentiAttesi) {
        String messaggio = unicoMessaggio().toLowerCase();
        for (String frammento : aFrammentiAttesi) {
            assertTrue(messaggio.contains(frammento.toLowerCase()), "il messaggio \"" + messaggio + "\" dovrebbe contenere \"" + frammento + "\"");
        }
        assertEquals(1, db.utenti().size(), "nessun account deve essere creato");
        assertTrue(finestra.isShowing(), "la form di registrazione resta aperta per correggere i dati");
    }

    @Test
    @DisplayName("TC1 - Tutti input validi: registrazione effettuata, creato nuovo account Cliente")
    void tc1_tuttiInputValidi() {
        registra("Mario", "Rossi", "mariorossi@gmail.com", "qwert7890?");

        assertTrue(unicoMessaggio().contains("Registrazione effettuata con successo! Creato nuovo account cliente."));
        assertEquals(2, db.utenti().size());
        Utente_Registrato creato = db.utente("mariorossi@gmail.com");
        assertNotNull(creato, "l'account deve esistere nel database");
        Cliente cliente = assertInstanceOf(Cliente.class, creato);
        assertEquals(Utente_Registrato.RUOLO_CLIENTE, cliente.getRuolo());
        assertEquals("Mario", cliente.getNome());
        assertEquals("Rossi", cliente.getCognome());
        assertFalse(finestra.isShowing(), "a registrazione conclusa la form si chiude");
        assertEquals("CLIENTE", new BUtente().autenticazione("mariorossi@gmail.com", "qwert7890?"), "il cliente puo' autenticarsi");
    }

    @Test
    @DisplayName("TC2 - E-mail gia' registrata: errore, nessun account creato")
    void tc2_emailGiaRegistrata() {
        registra("Mario", "Rossi", EMAIL_GIA_REGISTRATA, "abcdefghil!");

        verificaRifiuto("E-mail già presente nel sistema");
        assertEquals("Password123!", db.utente(EMAIL_GIA_REGISTRATA).getPassword(), "l'account esistente non deve essere toccato");
    }

    @Test
    @DisplayName("TC3 - E-mail sintatticamente non valida: errore, nessun account creato")
    void tc3_emailNonValida() {
        registra("Mario", "Rossi", "ABCDEFGHI123456", "qwerbcd.ef1");

        verificaRifiuto("e-mail");
        assertEquals("Campi non validi", grafica.dialoghi().get(0).titolo());
    }

    @Test
    @DisplayName("TC4 - Password di lunghezza superiore a 15 caratteri: errore, nessun account creato")
    void tc4_passwordTroppoLunga() {
        registra("Mario", "Rossi", "mariorossi@gmail.com", "abcdefghilmnopqrstuvz");

        verificaRifiuto("superiore", "15", "tra gli 8 e i 15 caratteri");
    }

    @Test
    @DisplayName("TC5 - Password di lunghezza inferiore a 8 caratteri: errore, nessun account creato")
    void tc5_passwordTroppoCorta() {
        registra("Mario", "Rossi", "mariorossi@gmail.com", "qwert");

        verificaRifiuto("inferiore", "8", "tra gli 8 e i 15 caratteri");
    }

    @Test
    @DisplayName("TC6 - Password senza carattere speciale: errore, nessun account creato")
    void tc6_passwordSenzaCarattereSpeciale() {
        registra("Mario", "Rossi", "mariorossi@gmail.com", "qwerttyonfhfmoh");

        verificaRifiuto("La password deve contenere almeno un carattere speciale");
    }

    @Test
    @DisplayName("TC7 - Password non presente (campo vuoto): errore, nessun account creato")
    void tc7_passwordVuota() {
        registra("Mario", "Rossi", "mariorossi@gmail.com", " ");

        verificaRifiuto("inferiore", "8", "tra gli 8 e i 15 caratteri");
    }
}

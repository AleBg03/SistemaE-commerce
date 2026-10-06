package it.unina.controllo;

import it.unina.Boundary.BUtente;
import it.unina.Entity.Cliente;
import it.unina.Entity.E_commerce;
import it.unina.Entity.Utente_Registrato;
import it.unina.Exceptions.EmailGiaInUsoException;
import it.unina.supporto.PersistenzaInMemoria;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Registrazione - test funzionali sul Control e sull'Entity (senza interfaccia grafica)")
class RegistrazioneControlloTest {
    private static final String EMAIL_GIA_REGISTRATA = "mariorossi@mail.com";

    private PersistenzaInMemoria db;
    private final BUtente bUtente = new BUtente();

    @BeforeEach
    void preparaScenario() {
        db = PersistenzaInMemoria.installa();
        db.aggiungi(new Cliente("Mario", "Rossi", EMAIL_GIA_REGISTRATA, "Password123!"));
    }

    @AfterEach
    void ripulisci() {
        PersistenzaInMemoria.ripristina();
    }

    private String errorePassword(String aPassword) {
        return E_commerce.getInstance().verificaRobustezzaPassword(aPassword);
    }

    @Test
    @DisplayName("TC1 - Tutti input validi: account Cliente creato e autenticabile")
    void tc1_tuttiInputValidi() {
        assertTrue(bUtente.registrazione("Mario", "Rossi", "mariorossi@gmail.com", "qwert7890?"));

        assertEquals(2, db.utenti().size());
        Cliente cliente = assertInstanceOf(Cliente.class, db.utente("mariorossi@gmail.com"));
        assertEquals(Utente_Registrato.RUOLO_CLIENTE, cliente.getRuolo());
        assertEquals("CLIENTE", bUtente.autenticazione("mariorossi@gmail.com", "qwert7890?"));
    }

    @Test
    @DisplayName("TC1 - La password valida (10 caratteri con carattere speciale) supera la verifica di robustezza")
    void tc1_passwordValida() {
        assertEquals("", errorePassword("qwert7890?"));
    }

    @Test
    @DisplayName("TC2 - E-mail gia' registrata: EmailGiaInUsoException, nessun account creato")
    void tc2_emailGiaRegistrata() {
        EmailGiaInUsoException errore = assertThrows(EmailGiaInUsoException.class,
                () -> bUtente.registrazione("Mario", "Rossi", EMAIL_GIA_REGISTRATA, "abcdefghil!"));

        assertTrue(errore.getMessage().contains("E-mail già presente nel sistema"));
        assertEquals(1, db.utenti().size());
        assertEquals("Password123!", db.utente(EMAIL_GIA_REGISTRATA).getPassword());
    }

    @Test
    @DisplayName("TC2 - Anche la stessa e-mail scritta con altre maiuscole e spazi e' gia' registrata")
    void tc2_emailGiaRegistrataConMaiuscole() {
        assertFalse(E_commerce.getInstance().verificaUnicitàEmail("  MarioRossi@Mail.com "));
        assertThrows(EmailGiaInUsoException.class,
                () -> bUtente.registrazione("Mario", "Rossi", "  MarioRossi@Mail.com ", "abcdefghil!"));
        assertEquals(1, db.utenti().size());
    }

    @Test
    @DisplayName("TC4 - Password superiore a 15 caratteri: non supera la verifica di robustezza")
    void tc4_passwordTroppoLunga() {
        String errore = errorePassword("abcdefghilmnopqrstuvz");

        assertTrue(errore.contains("superiore"));
        assertTrue(errore.contains("tra gli 8 e i 15 caratteri"));
        assertFalse(errore.contains("inferiore"));
    }

    @Test
    @DisplayName("TC5 - Password inferiore a 8 caratteri: non supera la verifica di robustezza")
    void tc5_passwordTroppoCorta() {
        String errore = errorePassword("qwert");

        assertTrue(errore.contains("inferiore"));
        assertTrue(errore.contains("tra gli 8 e i 15 caratteri"));
        assertFalse(errore.contains("superiore"));
    }

    @Test
    @DisplayName("TC6 - Password senza carattere speciale: non supera la verifica di robustezza")
    void tc6_passwordSenzaCarattereSpeciale() {
        String errore = errorePassword("qwerttyonfhfmoh");

        assertEquals("La password deve contenere almeno un carattere speciale.", errore);
    }

    @Test
    @DisplayName("TC7 - Password vuota (solo uno spazio): non supera la verifica di robustezza")
    void tc7_passwordVuota() {
        assertTrue(errorePassword(" ").contains("inferiore"));
        assertTrue(errorePassword("").contains("inferiore"));
        assertTrue(errorePassword(null).contains("inferiore"));
    }
}

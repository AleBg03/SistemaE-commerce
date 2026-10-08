package it.unina.controllo;

import it.unina.Boundary.BUtente;
import it.unina.Exceptions.CredenzialiErrateException;
import it.unina.Exceptions.UtenteNonTrovatoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Autenticazione - test funzionali sul Control e sull'Entity (senza interfaccia grafica)")
class AutenticazioneControlloTest {
    private static final String ERRORE_CREDENZIALI = "Credenziali errate! E-mail o password non corrette.";

    private final BUtente bUtente = new BUtente();


    @Test
    @DisplayName("TC1 - Credenziali corrette, ruolo Cliente: l'Utente risulta autenticato come CLIENTE")
    void tc1_cliente() {
        assertEquals("CLIENTE", bUtente.autenticazione("mario.rossi@email.com", "Password123!"));
    }

    @Test
    @DisplayName("TC2 - Credenziali corrette, ruolo Amministratore: l'Utente risulta autenticato come AMMINISTRATORE")
    void tc2_amministratore() {
        assertEquals("AMMINISTRATORE", bUtente.autenticazione("admin@email.com", "AdminPass123!"));
    }

    @Test
    @DisplayName("TC9 - E-mail valida ma non associata ad alcun account: UtenteNonTrovatoException con il messaggio del piano di test")
    void tc9_emailInesistente() {
        UtenteNonTrovatoException errore = assertThrows(UtenteNonTrovatoException.class,
                () -> bUtente.autenticazione("utente.inesistente@email.com", "Password123!"));

        assertEquals(ERRORE_CREDENZIALI, errore.getMessage());
    }

    @Test
    @DisplayName("TC9 (variante) - Password sbagliata per un'e-mail esistente: stesso messaggio, non rivela quali account esistono")
    void tc9_passwordSbagliata() {
        CredenzialiErrateException errore = assertThrows(CredenzialiErrateException.class,
                () -> bUtente.autenticazione("mario.rossi@email.com", "Password999!"));

        assertEquals(ERRORE_CREDENZIALI, errore.getMessage());
    }

    @Test
    @DisplayName("L'e-mail si riconosce senza badare a maiuscole e spazi, la password invece e' case-sensitive")
    void emailNormalizzataPasswordCaseSensitive() {
        assertEquals("CLIENTE", bUtente.autenticazione("  Mario.Rossi@Email.com ", "Password123!"));
        assertThrows(CredenzialiErrateException.class, () -> bUtente.autenticazione("mario.rossi@email.com", "password123!"));
    }
}

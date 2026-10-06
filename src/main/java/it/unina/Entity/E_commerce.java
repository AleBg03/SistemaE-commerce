package it.unina.Entity;

import it.unina.Database.GestorePersistenza;
import it.unina.Exceptions.CategoriaNonTrovataException;
import it.unina.Exceptions.CredenzialiErrateException;
import it.unina.Exceptions.EmailGiaInUsoException;
import it.unina.Exceptions.UtenteNonTrovatoException;

import java.util.List;
import java.util.Map;

public class E_commerce {
    private static final String JPQL_UTENTE_PER_EMAIL = "SELECT u FROM Utente_Registrato u WHERE u.email = :email";
    private static final String JPQL_CATALOGO = "SELECT p FROM Prodotto p ORDER BY p.id";
    private static final String JPQL_CATEGORIA_PER_NOME = "SELECT c FROM Categoria c WHERE c.nome = :nome";
    private static final String JPQL_ORDINI_RICEVUTI = "SELECT o FROM Ordine o ORDER BY o.id DESC";
    private static final String JPQL_STORICO_ORDINI = "SELECT o FROM Ordine o WHERE o.cliente.email = :email ORDER BY o.id DESC";
    private static final String CARATTERI_SPECIALI = "!@#$%^&*()_+-=[]{};:'\",.<>/?\\|";
    private static final String ERRORE_CREDENZIALI = "Credenziali errate! E-mail o password non corrette.";

    private static E_commerce instance;
    private final GestorePersistenza gestorePersistenza;

    private E_commerce(GestorePersistenza gestorePersistenza) {
        this.gestorePersistenza = gestorePersistenza;
    }

    public static synchronized E_commerce getInstance() {
        if (instance == null) {
            instance = new E_commerce(new GestorePersistenza());
        }
        return instance;
    }

    public void registraCliente(String aNome, String aCognome, String aEmail, String aPassword) {
        if (aNome == null || aNome.isBlank() || aCognome == null || aCognome.isBlank() || aEmail == null || aEmail.isBlank()) {
            throw new IllegalArgumentException("Nome, cognome ed e-mail sono obbligatori.");
        }
        boolean emailUnica = verificaUnicitàEmail(aEmail);
        if (!emailUnica) {
            throw new EmailGiaInUsoException("ERRORE! E-mail già presente nel sistema.");
        }
        Cliente cliente = new Cliente(aNome.trim(), aCognome.trim(), normalizza(aEmail), aPassword);
        gestorePersistenza.salva(cliente);
    }

    public boolean verificaUnicitàEmail(String aEmail) {
        List<Utente_Registrato> utenti = gestorePersistenza.eseguiQuery(JPQL_UTENTE_PER_EMAIL, Utente_Registrato.class, Map.of("email", normalizza(aEmail)));
        return utenti.isEmpty();
    }

    public String verificaRobustezzaPassword(String aPassword) {
        String password = aPassword == null ? "" : aPassword;
        StringBuilder criteri = new StringBuilder();
        if (password.length() < 8) {
            criteri.append("Password inferiore agli 8 caratteri! Deve essere una stringa compresa tra gli 8 e i 15 caratteri.\n");
        }
        if (password.length() > 15) {
            criteri.append("Password superiore ai 15 caratteri! Deve essere una stringa compresa tra gli 8 e i 15 caratteri.\n");
        }
        boolean haSpeciale = false;
        for (char c : password.toCharArray()) {
            if (CARATTERI_SPECIALI.indexOf(c) >= 0) {
                haSpeciale = true;
            }
        }
        if (!haSpeciale) {
            criteri.append("La password deve contenere almeno un carattere speciale.\n");
        }
        return criteri.toString().trim();
    }

    public Utente_Registrato autentica(String aEmail, String aPassword) {
        List<Utente_Registrato> utenti = gestorePersistenza.eseguiQuery(JPQL_UTENTE_PER_EMAIL, Utente_Registrato.class, Map.of("email", normalizza(aEmail)));
        if (utenti.isEmpty()) {
            throw new UtenteNonTrovatoException(ERRORE_CREDENZIALI);
        }
        Utente_Registrato utente = utenti.get(0);
        if (!utente.verificaPassword(aPassword)) {
            throw new CredenzialiErrateException(ERRORE_CREDENZIALI);
        }
        return utente;
    }

    public List<Prodotto> getCatalogo() {
        return gestorePersistenza.eseguiQuery(JPQL_CATALOGO, Prodotto.class, Map.of());
    }

    public Categoria cercaCategoria(String aNomeCategoria) {
        List<Categoria> categorie = gestorePersistenza.eseguiQuery(JPQL_CATEGORIA_PER_NOME, Categoria.class, Map.of("nome", aNomeCategoria == null ? "" : aNomeCategoria.trim()));
        return categorie.isEmpty() ? null : categorie.get(0);
    }

    public void creaProdotto(String aNome, String aDescrizione, Float aPrezzo, int aQuantità, String aNomeCategoria) {
        Categoria categoria = cercaCategoria(aNomeCategoria);
        if (categoria == null) {
            throw new CategoriaNonTrovataException("ERRORE! La categoria \"" + aNomeCategoria + "\" non è tra quelle registrate.");
        }
        Prodotto prodotto = new Prodotto(aNome.trim(), aDescrizione.trim(), aPrezzo, aQuantità, categoria);
        gestorePersistenza.salva(prodotto);
    }

    public List<Ordine> getOrdiniRicevuti() {
        return gestorePersistenza.eseguiQuery(JPQL_ORDINI_RICEVUTI, Ordine.class, Map.of());
    }

    public List<Ordine> consultaStoricoOrdini(String aEmailCliente) {
        return gestorePersistenza.eseguiQuery(JPQL_STORICO_ORDINI, Ordine.class, Map.of("email", normalizza(aEmailCliente)));
    }

    private String normalizza(String aEmail) {
        return aEmail == null ? "" : aEmail.trim().toLowerCase();
    }

    public void rimuoviProdotto(String aNome) {
        throw new UnsupportedOperationException();
    }

    public Prodotto ricercaProdotto(String aParametro) {
        throw new UnsupportedOperationException();
    }

    public void ripristinaQuantità(String aNome, int aQuantità) {
        throw new UnsupportedOperationException();
    }

    public boolean controlloDisponibilitàQuantità(String aNome, int aQuantità) {
        throw new UnsupportedOperationException();
    }

    public void visualizzaOfferte() {
        throw new UnsupportedOperationException();
    }

    public void verificaValiditàDati(String aEmail, String aPassword) {
        throw new UnsupportedOperationException();
    }

    public void verificaIndirizzo(String aVia, String aCivico, int aCap, String aCittà) {
        throw new UnsupportedOperationException();
    }

    public void verificaCorrispondenzaCredenziali(String aEmail, String aPassword) {
        throw new UnsupportedOperationException();
    }

    public void verificaStato() {
        throw new UnsupportedOperationException();
    }

    public void verificaTransizioneStato() {
        throw new UnsupportedOperationException();
    }

    public void verificaQuantità(int aQtaDesiderata) {
        throw new UnsupportedOperationException();
    }

    public void verificaValiditàDatiProdotto(String aNome, String aDescrizione, Float aPrezzo, int aQtaDisponibile, Boolean aScontato, Boolean aDisponibile) {
        throw new UnsupportedOperationException();
    }

    public void verificaAnnullamento() {
        throw new UnsupportedOperationException();
    }
}

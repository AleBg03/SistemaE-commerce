package it.unina.supporto;

import it.unina.Database.GestorePersistenza;
import it.unina.Entity.E_commerce;
import it.unina.Entity.Utente_Registrato;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersistenzaInMemoria extends GestorePersistenza {
    private final List<Utente_Registrato> utenti = new ArrayList<>();

    public static PersistenzaInMemoria installa() {
        PersistenzaInMemoria db = new PersistenzaInMemoria();
        try {
            Constructor<E_commerce> costruttore = E_commerce.class.getDeclaredConstructor(GestorePersistenza.class);
            costruttore.setAccessible(true);
            impostaIstanza(costruttore.newInstance(db));
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("impossibile sostituire il GestorePersistenza di E_commerce", e);
        }
        return db;
    }

    public static void ripristina() {
        try {
            impostaIstanza(null);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void impostaIstanza(E_commerce valore) throws ReflectiveOperationException {
        Field istanza = E_commerce.class.getDeclaredField("instance");
        istanza.setAccessible(true);
        istanza.set(null, valore);
    }

    public void aggiungi(Utente_Registrato aUtente) {
        salva(aUtente);
    }

    public List<Utente_Registrato> utenti() {
        return List.copyOf(utenti);
    }

    public Utente_Registrato utente(String aEmail) {
        return utenti.stream().filter(u -> u.getEmail().equalsIgnoreCase(aEmail)).findFirst().orElse(null);
    }

    @Override
    public void salva(Object aEntità) {
        if (!(aEntità instanceof Utente_Registrato nuovo)) {
            throw new UnsupportedOperationException("PersistenzaInMemoria gestisce solo gli utenti");
        }
        if (utente(nuovo.getEmail()) != null) {
            throw new IllegalStateException("chiave primaria duplicata: " + nuovo.getEmail());
        }
        utenti.add(nuovo);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> eseguiQuery(String aJpql, Class<T> aClasse, Map<String, Object> aParametri) {
        if (!aJpql.contains("FROM Utente_Registrato") || !aParametri.containsKey("email")) {
            throw new UnsupportedOperationException("query non prevista dai test di registrazione/autenticazione: " + aJpql);
        }
        String email = (String) aParametri.get("email");
        List<T> risultato = new ArrayList<>();
        for (Utente_Registrato u : utenti) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                risultato.add((T) u);
            }
        }
        return risultato;
    }
}

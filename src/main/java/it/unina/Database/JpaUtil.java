package it.unina.Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final String UNITA_DI_PERSISTENZA = "ecommercePU";
    private static JpaUtil instance;
    private EntityManagerFactory emf;

    private JpaUtil() {
        this.emf = Persistence.createEntityManagerFactory(UNITA_DI_PERSISTENZA);
    }

    public static synchronized JpaUtil getInstance() {
        if (instance == null) {
            instance = new JpaUtil();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void chiudi() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        instance = null;
    }
}

package it.unina.Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GestorePersistenza {

    public void salva(Object aEntità) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(aEntità);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public <T> List<T> eseguiQuery(String aJpql, Class<T> aClasse, Map<String, Object> aParametri) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        try {
            TypedQuery<T> query = em.createQuery(aJpql, aClasse);
            for (Map.Entry<String, Object> parametro : aParametri.entrySet()) {
                query.setParameter(parametro.getKey(), parametro.getValue());
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public <T> T aggiorna(T aEntità) {
        throw new UnsupportedOperationException();
    }

    public void elimina(Object aEntità) {
        throw new UnsupportedOperationException();
    }

    public <T> T trovaPerId(Class<T> aClasse, Object aId) {
        throw new UnsupportedOperationException();
    }

    public void eseguiInTransazione(Consumer<EntityManager> aOperazioni) {
        throw new UnsupportedOperationException();
    }
}

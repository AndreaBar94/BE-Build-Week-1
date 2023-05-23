package dao;

import javax.persistence.EntityManager;
import entities.AuthorizedDealer;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class DealersDAO {
    private EntityManagerFactory emf;

    public DealersDAO() {
        emf = Persistence.createEntityManagerFactory("transportdb");
    }

    public void createAuthorizedDealer(AuthorizedDealer authorizedDealer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(authorizedDealer);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public AuthorizedDealer getAuthorizedDealerById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(AuthorizedDealer.class, id);
        } finally {
            em.close();
        }
    }

    public List<AuthorizedDealer> getAllAuthorizedDealers() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("AuthorizedDealer.findAll", AuthorizedDealer.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void updateAuthorizedDealer(AuthorizedDealer authorizedDealer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(authorizedDealer);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteAuthorizedDealer(AuthorizedDealer authorizedDealer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(em.contains(authorizedDealer) ? authorizedDealer : em.merge(authorizedDealer));
            et.commit();
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}

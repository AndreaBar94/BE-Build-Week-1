package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.AuthorizedDealer;
import entities.VendingMachine;

public class DealersDAO {
    private static Logger logger = (Logger) LoggerFactory.getLogger(DealersDAO.class);

    private final EntityManager em;

    public DealersDAO(EntityManager em) {
        this.em = em;
    }

    public List<AuthorizedDealer> getAllAuthorizedDealers() {
        try {
            TypedQuery<AuthorizedDealer> query = em.createNamedQuery("AuthorizedDealer.findAll", AuthorizedDealer.class);
            List<AuthorizedDealer> authorizedDealers = query.getResultList();
            for (AuthorizedDealer dealer : authorizedDealers) {
                logger.info(dealer.toString());
            }
            return authorizedDealers;
        } catch (Exception e) {
            logger.error("Error: " + e);
            throw e;
        }
    }

    public void saveAuthorizedDealer(AuthorizedDealer authorizedDealer) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(authorizedDealer);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            logger.error("Error: " + e);
        }
    }
    
    public AuthorizedDealer getAuthorizedDealerById(Integer id) {
        return em.find(AuthorizedDealer.class, id);
    }

    public void updateAuthorizedDealer(AuthorizedDealer authorizedDealer) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(authorizedDealer);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            logger.error("Error: " + e);
        }
    }

    public void deleteAuthorizedDealer(AuthorizedDealer authorizedDealer) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(em.contains(authorizedDealer) ? authorizedDealer : em.merge(authorizedDealer));
            et.commit();
        } catch (Exception e) {
            et.rollback();
            logger.error("Error: " + e);
        }
    }

    public List<VendingMachine> getAllVendingMachines() {
        try {
            TypedQuery<VendingMachine> query = em.createNamedQuery("VendingMachine.findAll", VendingMachine.class);
            List<VendingMachine> vendingMachines = query.getResultList();
            for (VendingMachine machine : vendingMachines) {
                logger.info(machine.toString());
            }
            return vendingMachines;
        } catch (Exception e) {
            logger.error("Error: " + e);
            throw e;
        }
    }

    public void close() {
        em.close();
    }
}
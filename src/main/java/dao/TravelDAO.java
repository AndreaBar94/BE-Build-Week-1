package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Travel;

public class TravelDAO {
    private static final Logger logger = LoggerFactory.getLogger(TravelDAO.class);
    private final EntityManager em;

    public TravelDAO(EntityManager em) {
        this.em = em;
    }

    public void saveTravel(Travel travel) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(travel);
            transaction.commit();
            logger.info("Viaggio salvato correttamente: " + travel);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Errore durante il salvataggio del viaggio.", e);
            throw e;
        }
    }

    public Travel getTravelById(Long id) {
        Travel found = em.find(Travel.class, id);
        return found;
    }

    public void deleteTravel(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Travel travel = em.find(Travel.class, id);
            if (travel != null) {
                em.remove(travel);
                transaction.commit();
                logger.info("Viaggio eliminato correttamente: " + travel);
            } else {
                logger.warn("Impossibile trovare il viaggio con ID: " + id);
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Errore durante l'eliminazione del viaggio.", e);
            throw e;
        }
    }

    public List<Travel> getAllTravels() {
        try {
            TypedQuery<Travel> query = em.createQuery("SELECT t FROM Travel t", Travel.class);
            List<Travel> travels = query.getResultList();
            for (Travel travel : travels) {
                logger.info(travel.toString());
            }
            return travels;
        } catch (Exception e) {
            logger.error("Errore durante il recupero dei viaggi.", e);
            throw e;
        }
    }
}
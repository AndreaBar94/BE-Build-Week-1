package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Route;

public class RouteDAO {
	private static final Logger logger = LoggerFactory.getLogger(VehicleDAO.class);
	private final EntityManager em;

	public RouteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void saveRoute(Route route) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(route);
			transaction.commit();
			logger.info("Route salvato correttamente: " + route);
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Errore durante il salvataggio della Route.", e);
			throw e;
		}
	}
}
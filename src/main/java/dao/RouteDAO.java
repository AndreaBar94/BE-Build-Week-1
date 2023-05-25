package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Route;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RouteDAO {
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
			log.info("Route salvato correttamente: " + route);
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			log.error("Errore durante il salvataggio della Route.", e);
			throw e;
		}
	}
}
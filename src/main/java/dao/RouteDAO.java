package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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

	public List<Route> getAllRoutes() {
		try {
			TypedQuery<Route> query = em.createQuery("SELECT u FROM Route u", Route.class);
			List<Route> routeList = query.getResultList();
			StringBuilder routes = new StringBuilder("Lista Tratte:\n");
			for (Route route : routeList) {
				routes.append(route.toString()).append("\n");
			}
			System.out.println(routes.toString());
			return routeList;
		} catch (Exception e) {
			log.error("error: " + e);
			throw e;
		}

	};

	public Route getRouteById(Long id) {
		return em.find(Route.class, id);
	}

	public void deleteRoute(Long id) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Route route = em.find(Route.class, id);
			if (route != null) {
				em.remove(route);
				transaction.commit();
				log.info("Viaggio eliminato correttamente: " + route);
			} else {
				log.warn("Impossibile trovare il viaggio con ID: " + id);
			}
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			log.error("Errore durante l'eliminazione del viaggio.", e);
			throw e;
		}
	}

}
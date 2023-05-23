package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Vehicle;

public class VehicleDAO {
	private static final Logger logger = LoggerFactory.getLogger(VehicleDAO.class);
	private final EntityManager em;

	public VehicleDAO(EntityManager em) {
		this.em = em;
	}

	public void saveVehicle(Vehicle vehicle) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(vehicle);
			transaction.commit();
			logger.info("Veicolo salvato correttamente: " + vehicle);
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Errore durante il salvataggio del veicolo.", e);
			throw e;
		}
	}

	public Vehicle getVehicleById(UUID id) {
		Vehicle found = em.find(Vehicle.class, id);
		return found;
	}

	public List<Vehicle> getAllVehicles() {
		try {
			TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v", Vehicle.class);
			return query.getResultList();
		} catch (Exception e) {
			logger.error("Errore durante il recupero dei veicoli.", e);
			throw e;
		}
	}

	public void deleteVehicle(UUID id) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Vehicle vehicle = em.find(Vehicle.class, id);
			if (vehicle != null) {
				em.remove(vehicle);
				transaction.commit();
				logger.info("Veicolo eliminato correttamente: " + vehicle);
			} else {
				logger.warn("Impossibile trovare il veicolo con ID: " + id);
			}
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Errore durante l'eliminazione del veicolo.", e);
			throw e;
		}
	}
}

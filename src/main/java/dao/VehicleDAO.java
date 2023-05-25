package dao;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.AuthorizedDealer;
import entities.Bus;
import entities.Public_Transport_Pass;
import entities.Ticket;
import entities.Tram;
import entities.Travel_Document;
import entities.Vehicle;
import entities.Vehicle.State;

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
	
	public void validateTicket(String travelDocID, String vID) {
	    EntityTransaction transaction = null;

	    try {
	        transaction = em.getTransaction();
	        transaction.begin();

	        Travel_DocumentDAO tDAO = new Travel_DocumentDAO(em);
	        Travel_Document td = tDAO.findByUUID(travelDocID);

	        if (td instanceof Public_Transport_Pass) {
	            if (((Public_Transport_Pass) td).isValid()) {
	                logger.info("Pass valido!");
	            } else {
	                logger.info("Aaaaaaaah facciamo una bella multina qui!");
	            }
	        } else if (td instanceof Ticket) {
	            Ticket ticket = (Ticket) td;

	            if (!ticket.isEndorsed()) {
	                Vehicle v = this.getVehicleById(UUID.fromString(vID));

	                ticket.setEndorsed(true);
	                ticket.setDataVid(LocalDate.now());
	                logger.info("Biglietto vidimato!");

	                v.setTicketsValidated(v.getTicketsValidated() + 1);
	            } else {
	                logger.info("Aaaaaaaah facciamo una bella multina qui!");
	            }
	        } else {
	            logger.info("Niente biglietto o abbonamento? Aaaaaaaah facciamo una bella multina qui!");
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        logger.error("An error occurred during ticket validation: " + e.getMessage());
	    }
	}

	
	public Vehicle getVehicleById(UUID id) {
		Vehicle found = em.find(Vehicle.class, id);
		return found;
	}

	public List<Vehicle> getAllVehicles() {
		try {
			TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v", Vehicle.class);
			List<Vehicle> vehicles = query.getResultList();
			for (Vehicle vehicle : vehicles) {
				logger.info(vehicle.toString());
			}
			return vehicles;
		} catch (Exception e) {
			logger.error("Errore durante il recupero dei veicoli.", e);
			throw e;
		}
	}

	public List<Bus> getAllBuses() {
		try {
			TypedQuery<Bus> query = em.createQuery("SELECT b FROM Bus b", Bus.class);
			List<Bus> buses = query.getResultList();
			for (Bus bus : buses) {
				logger.info(bus.toString());
			}
			return buses;
		} catch (Exception e) {
			logger.error("Errore durante il recupero dei buses.", e);
			throw e;
		}
	}

	public List<Tram> getAllTrams() {
		try {
			TypedQuery<Tram> query = em.createQuery("SELECT t FROM Tram t", Tram.class);
			List<Tram> trams = query.getResultList();
			for (Tram tram : trams) {
				logger.info(tram.toString());
			}
			return trams;
		} catch (Exception e) {
			logger.error("Errore durante il recupero dei trams.", e);
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

	public void endService(UUID id) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			Vehicle vehicle = em.find(Vehicle.class, id);
			if (vehicle != null) {
				if (vehicle.getState() == State.IN_SERVICE) {
					vehicle.setState(State.UNDER_MAINTENANCE);
					vehicle.setMaintenanceStartDate(LocalDate.now());
					LocalDate endDate = LocalDate.now();
					Duration serviceDuration = Duration.between(vehicle.getServiceStartDate().atStartOfDay(),
							endDate.atStartOfDay());
					vehicle.setTotalServiceDuration(vehicle.getTotalServiceDuration().plus(serviceDuration));
					vehicle.setServiceCount(vehicle.getServiceCount() + 1);
				} else {
					logger.info("Vehicle with ID " + id + " is not in service.");
				}
			} else {
				logger.info("No vehicle found with ID " + id);
			}

			transaction.commit();
			logger.info("Service ended successfully for vehicle with ID: " + id);
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Error occurred while ending service for vehicle with ID: " + id, e);
			throw e;
		}
	}

	public void endMaintenance(UUID id) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			Vehicle vehicle = em.find(Vehicle.class, id);
			if (vehicle != null) {
				if (vehicle.getState() == State.UNDER_MAINTENANCE) {
					vehicle.setState(State.IN_SERVICE);
					vehicle.setServiceStartDate(LocalDate.now());
					LocalDate endDate = LocalDate.now();
					Duration maintenanceDuration = Duration.between(vehicle.getMaintenanceStartDate().atStartOfDay(),
							endDate.atStartOfDay());
					vehicle.setTotalMaintenanceDuration(
							vehicle.getTotalMaintenanceDuration().plus(maintenanceDuration));
					vehicle.setMaintenanceCount(vehicle.getMaintenanceCount() + 1);
				} else {
					logger.info("Vehicle with ID " + id + " is not under maintenance.");
				}
			} else {
				logger.info("No vehicle found with ID " + id);
			}

			transaction.commit();
			logger.info("Maintenance ended successfully for vehicle with ID: " + id);
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Error occurred while ending maintenance for vehicle with ID: " + id, e);
			throw e;
		}
	}
	
	public long docPerVehicleAndDate(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
		try {
			List<Ticket> tickets = getDocumentsByVehicle(vehicle);//fin qui tutto ok
            int count =(int) tickets.stream()
                    .filter(ticket -> isWithinDateRange(ticket.getDataVid(), startDate, endDate))
                    .count();
            logger.info("Numero di documenti vidimati per il mezzo selezionato nell'arco di tempo richiesto: " + count);
            return count;
        } catch (Exception e) {
            logger.error("Si è verificato un errore durante il conteggio dei biglietti.", e);
            return 0;
        }
	}
	
//	public int docPerVehicleAndDate(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
//	    try {
//	        List<Ticket> tickets = em.createQuery(
//	                "SELECT t FROM Ticket t WHERE t.vehicle = :vehicle " +
//	                "AND t.endorsed = true " +
//	                "AND t.dataVid >= :startDate " +
//	                "AND t.dataVid <= :endDate", Ticket.class)
//	                .setParameter("vehicle", vehicle)
//	                .setParameter("startDate", startDate)
//	                .setParameter("endDate", endDate)
//	                .getResultList();
//
//	        int count = tickets.size();
//	        logger.info("Numero di documenti vidimati per il mezzo selezionato nell'arco di tempo richiesto: " + count);
//	        return count;
//	    } catch (Exception e) {
//	        logger.error("Si è verificato un errore durante il conteggio dei biglietti.", e);
//	        return 0;
//	    }
//	}
	
	private List<Ticket> getDocumentsByVehicle(Vehicle vehicle) {
        try {
        	List<Ticket> found = vehicle.getTicketsList();
			return found;
		} catch (Exception e) {
			 logger.error("Si è verificato un errore durante il conteggio dei documenti.", e);
	            return null;
		}
  
    }
    
    private boolean isWithinDateRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

}

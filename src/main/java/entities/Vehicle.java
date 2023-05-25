package entities;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import dao.Travel_DocumentDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@DiscriminatorColumn(name = "vehicle_type")
@Slf4j
public abstract class Vehicle {
	public enum State {
		IN_SERVICE, UNDER_MAINTENANCE
	}

	@Id
	@GeneratedValue
	private UUID id;

	@Enumerated(EnumType.STRING)
	private State state;

	private int ticketsValidated;

	private LocalDate serviceStartDate;
	private LocalDate maintenanceStartDate;

	private int maintenanceCount;
	private Duration totalMaintenanceDuration;

	private int serviceCount;
	private Duration totalServiceDuration;
	
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
	private Set<Ticket> ticketsList = new HashSet<>();
	
	
	public Vehicle() {
		this.state = State.IN_SERVICE;
		this.ticketsValidated = 0;
		this.serviceStartDate = LocalDate.now();
		this.maintenanceStartDate = null;
		this.maintenanceCount = 0;
		this.totalMaintenanceDuration = Duration.ZERO;
		this.serviceCount = 0;
		this.totalServiceDuration = Duration.ZERO;
	}

//	public void validateTicket(String travelDocID) {
//		
//		Travel_Document td = Travel_DocumentDAO.findByUUID(travelDocID);
//		
//		if(td instanceof Public_Transport_Pass) {
//			if(((Public_Transport_Pass) td).isValid() == true) {
//				log.info("Pass valido!");
//			}else {
//				log.info("Aaaaaaaah facciamo una bella multina qui!");
//			}
//		}else if(td instanceof Ticket){
//			
//			for (Ticket ticket : ticketsList) {
//			if (ticket.getId().equals(travelDocID) && ticket.isEndorsed() == false) {
//				ticket.setEndorsed(true);
//				this.ticketsValidated++;
//				log.info("Biglietto vidimato!");//non compare in console? ma funziona
//				this.setTicketsValidated(+1);
//				return;
//			}else {
//				log.info("Aaaaaaaah facciamo una bella multina qui!");
//			}
//		}
//		}else {
//			log.info("Niente biglietto o abbonamento? Aaaaaaaah facciamo una bella multina qui!");
//		}		
//	}
	
//	public int getTotalValidatedTickets() {
//		return ticketsList.size();
//	}
//
//	public UUID getId() {
//		return id;
//	}
//
//	public void setId(UUID id) {
//		this.id = id;
//	}
//
//	public State getState() {
//		return state;
//	}
//
//	public void setState(State state) {
//		this.state = state;
//	}
//
//	public int getTicketsValidated() {
//		return ticketsValidated;
//	}
//
//	public void setTicketsValidated(int ticketsValidated) {
//		this.ticketsValidated = ticketsValidated;
//	}
//
//	public LocalDate getServiceStartDate() {
//		return serviceStartDate;
//	}
//
//	public void setServiceStartDate(LocalDate serviceStartDate) {
//		this.serviceStartDate = serviceStartDate;
//	}
//
//	public LocalDate getMaintenanceStartDate() {
//		return maintenanceStartDate;
//	}
//
//	public void setMaintenanceStartDate(LocalDate maintenanceStartDate) {
//		this.maintenanceStartDate = maintenanceStartDate;
//	}
//
//	public int getMaintenanceCount() {
//		return maintenanceCount;
//	}
//
//	public void setMaintenanceCount(int maintenanceCount) {
//		this.maintenanceCount = maintenanceCount;
//	}
//
//	public Duration getTotalMaintenanceDuration() {
//		return totalMaintenanceDuration;
//	}
//
//	public void setTotalMaintenanceDuration(Duration totalMaintenanceDuration) {
//		this.totalMaintenanceDuration = totalMaintenanceDuration;
//	}
//
//	public int getServiceCount() {
//		return serviceCount;
//	}
//
//	public void setServiceCount(int serviceCount) {
//		this.serviceCount = serviceCount;
//	}
//
//	public Duration getTotalServiceDuration() {
//		return totalServiceDuration;
//	}
//
//	public void setTotalServiceDuration(Duration totalServiceDuration) {
//		this.totalServiceDuration = totalServiceDuration;
//	}
//
	public abstract int getCapacity();

	@Override
	public String toString() {
		return "Vehicle{" + "id=" + id + ", state=" + state + ", ticketsValidated=" + ticketsValidated
				+ ", serviceStartDate=" + serviceStartDate + ", maintenanceStartDate=" + maintenanceStartDate
				+ ", maintenanceCount=" + maintenanceCount + ", totalMaintenanceDuration=" + totalMaintenanceDuration
				+ ", serviceCount=" + serviceCount + ", totalServiceDuration=" + totalServiceDuration + '}';
	}
}

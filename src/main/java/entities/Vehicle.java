package entities;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type")
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

	public void validateTicket() {
		this.ticketsValidated++;
		// trigger ticket validation using the ticket UUID
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getTicketsValidated() {
		return ticketsValidated;
	}

	public void setTicketsValidated(int ticketsValidated) {
		this.ticketsValidated = ticketsValidated;
	}

	public LocalDate getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(LocalDate serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public LocalDate getMaintenanceStartDate() {
		return maintenanceStartDate;
	}

	public void setMaintenanceStartDate(LocalDate maintenanceStartDate) {
		this.maintenanceStartDate = maintenanceStartDate;
	}

	public int getMaintenanceCount() {
		return maintenanceCount;
	}

	public void setMaintenanceCount(int maintenanceCount) {
		this.maintenanceCount = maintenanceCount;
	}

	public Duration getTotalMaintenanceDuration() {
		return totalMaintenanceDuration;
	}

	public void setTotalMaintenanceDuration(Duration totalMaintenanceDuration) {
		this.totalMaintenanceDuration = totalMaintenanceDuration;
	}

	public int getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}

	public Duration getTotalServiceDuration() {
		return totalServiceDuration;
	}

	public void setTotalServiceDuration(Duration totalServiceDuration) {
		this.totalServiceDuration = totalServiceDuration;
	}

	public abstract int getCapacity();

	@Override
	public String toString() {
		return "Vehicle{" + "id=" + id + ", state=" + state + ", ticketsValidated=" + ticketsValidated
				+ ", serviceStartDate=" + serviceStartDate + ", maintenanceStartDate=" + maintenanceStartDate
				+ ", maintenanceCount=" + maintenanceCount + ", totalMaintenanceDuration=" + totalMaintenanceDuration
				+ ", serviceCount=" + serviceCount + ", totalServiceDuration=" + totalServiceDuration + '}';
	}
}

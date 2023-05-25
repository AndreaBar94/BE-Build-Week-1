package entities;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	private List<Ticket> ticketsList = new ArrayList<>();
	
	
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

	public abstract int getCapacity();

	@Override
	public String toString() {
		return "Vehicle{" + "id=" + id + ", state=" + state + ", ticketsValidated=" + ticketsValidated
				+ ", serviceStartDate=" + serviceStartDate + ", maintenanceStartDate=" + maintenanceStartDate
				+ ", maintenanceCount=" + maintenanceCount + ", totalMaintenanceDuration=" + totalMaintenanceDuration
				+ ", serviceCount=" + serviceCount + ", totalServiceDuration=" + totalServiceDuration + '}';
	}
}

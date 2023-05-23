package entities;

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

	public Vehicle() {
		this.state = State.IN_SERVICE;
		this.ticketsValidated = 0;
	}

	public void validateTicket() {
		this.ticketsValidated++;
		// triggera validazione biglietto, passando uuid biglietto
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

	public abstract int getCapacity();

	@Override
	public String toString() {
		return "Vehicle{" + "id=" + id + ", state=" + state + ", ticketsValidated=" + ticketsValidated + '}';
	}

}

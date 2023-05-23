package entities;

import javax.persistence.Entity;

@Entity
public class Tram extends Vehicle {
	private int capacity;

	public Tram() {
		super();
	}

	public Tram(int capacity) {
		super();
		this.capacity = capacity;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}

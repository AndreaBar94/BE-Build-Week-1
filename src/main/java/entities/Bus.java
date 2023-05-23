package entities;

import javax.persistence.Entity;

@Entity
public class Bus extends Vehicle {
	private int capacity;

	public Bus() {
		super();
	}

	public Bus(int capacity) {
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

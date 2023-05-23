package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "VendingMachine.findAll", query = "SELECT a FROM VendingMachine a")
public class VendingMachine extends AuthorizedDealer implements Serializable {
	private boolean outOfOrder;

	public boolean isOutOfOrder() {
		return outOfOrder;
	}

	public void setOutOfOrder(boolean outOfOrder) {
		this.outOfOrder= outOfOrder;
	}

	public VendingMachine() {
		super();
	}

	@Override
	public String toString() {
		return "VendingMachine [outOfOrder=" + outOfOrder + "]";
	}
	public void ticketBought() {
	    try {
	        if (isOutOfOrder()) {
	            System.out.println("The vending machine is out of order.");
	        } else {
	            System.out.println("Ticket is being printed, please wait.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
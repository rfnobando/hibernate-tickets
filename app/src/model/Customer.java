package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
	private List<Ticket> managedTickets;

	public Customer(){}
	
	public Customer(String name, String surname, String email, String password, ProfilePicture profilePhoto) {
		super(name, surname, email, password, profilePhoto);
		this.managedTickets = new ArrayList<Ticket>();
	}

	public List<Ticket> getManagedTickets() {
		return managedTickets;
	}

	public void setManagedTickets(List<Ticket> managedTickets) {
		this.managedTickets = managedTickets;
	}

	@Override
	public String toString() {
		return "Customer [managedTickets=" + managedTickets + "]"+ "]";
	}
	
	
}

package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Employee extends User {
	private Set<Ticket> managedTickets;

	public Employee(){}
	
	public Employee(String name, String surname, String email, String password, ProfilePicture profilePhoto) {
		super(name, surname, email, password, profilePhoto);
		this.managedTickets = new HashSet<Ticket>();
	}

	public Set<Ticket> getManagedTickets() {
		return managedTickets;
	}

	public void setManagedTickets(Set<Ticket> managedTickets) {
		this.managedTickets = managedTickets;
	}

	@Override
	public String toString() {
		return "Employee [managedTickets=" + managedTickets + "]"+ "]";
	}
	
	
}

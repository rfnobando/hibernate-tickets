package model;

import java.util.HashSet;
import java.util.Set;

public class Employee extends User {
	private Set<Ticket> managedTickets;

	public Employee(){}
	
	public Employee(String name, String surname, String email, String password, ProfilePicture profilePicture) {
		super(name, surname, email, password, profilePicture);
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
        return "Employee [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + "]";
	}
	
	
}

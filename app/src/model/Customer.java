package model;

import java.util.HashSet;
import java.util.Set;

public class Customer extends User {
	private Set<Ticket> createdTickets;

	
	public Customer(){}
	
	public Customer(String name, String surname, String email, String password, ProfilePicture profilePicture) {
		super(name, surname, email, password, profilePicture);
		this.createdTickets = new HashSet<Ticket>();
	}

	public Set<Ticket> getCreatedTickets() {
		return createdTickets;
	}

	public void setCreatedTickets(Set<Ticket> createdTickets) {
		this.createdTickets = createdTickets;
	}
	
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + "]";
    }
}

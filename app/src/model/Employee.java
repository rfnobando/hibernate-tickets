package model;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
	protected List<Ticket> ticketsManaged;

	public Employee(){}
	
	public Employee(String name, String surname, String email, String password, ProfilePhoto profilePhoto) {
		super(name, surname, email, password, profilePhoto);
		this.ticketsManaged = new ArrayList<Ticket>();
	}

	public List<Ticket> getTicketsManaged() {
		return ticketsManaged;
	}

	public void setTicketsManaged(List<Ticket> ticketsManaged) {
		this.ticketsManaged = ticketsManaged;
	}

	@Override
	public String toString() {
		return "Employee [ticketsManaged=" + ticketsManaged + "]"+ "]";
	}
	
	
}

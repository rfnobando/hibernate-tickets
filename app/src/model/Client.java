package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
	public List<Ticket> ticketsCreated;

	
	public Client(){}
	
	public Client(String name, String surname, String email, String password, ProfilePhoto profilePhoto) {
		super(name, surname, email, password, profilePhoto);
		this.ticketsCreated = new ArrayList<Ticket>();
	}

	public List<Ticket> getTicketsCreated() {
		return ticketsCreated;
	}

	public void setTicketsCreated(List<Ticket> ticketsCreated) {
		this.ticketsCreated = ticketsCreated;
	}

	@Override
	public String toString() {
		return "Client [ticketsCreated=" + ticketsCreated + "]" +"]";
	}
	
	
}

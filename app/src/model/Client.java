package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
	public List<Ticket> createdTickets;

	
	public Client(){}
	
	public Client(String name, String surname, String email, String password, ProfilePicture profilePhoto) {
		super(name, surname, email, password, profilePhoto);
		this.createdTickets = new ArrayList<Ticket>();
	}

	public List<Ticket> getCreatedTickets() {
		return createdTickets;
	}

	public void setCreatedTickets(List<Ticket> createdTickets) {
		this.createdTickets = createdTickets;
	}

	@Override
	public String toString() {
		return "Client [createdTickets=" + createdTickets + "]" +"]";
	}
	
	
}

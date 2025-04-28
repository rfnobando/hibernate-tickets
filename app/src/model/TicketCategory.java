package model;

import java.util.ArrayList;
import java.util.List;

public class TicketCategory {
	public long id;
	public String name;
	public List<Ticket> tickets;
	
	public TicketCategory(){}
	
	public TicketCategory(String name) {
		super();
		this.name = name;
		this.tickets = new ArrayList<Ticket>();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	@Override
	public String toString() {
		return "TicketCategory [id=" + id + ", name=" + name + ", tickets=" + tickets + "]";
	}
}

package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
	public long id;
	public String title;
	public LocalDate creationDate;
	public LocalDate updateDate;
	public Client client;
	public List<Employee> employees;
	public TicketCategory ticketCategory;
	public Status status;
	public List<TicketMessage> messages;
	
	public Ticket(){}
	
	public Ticket(String title, LocalDate creationDate, LocalDate updateDate, Client client,
			TicketCategory ticketCategory, Status status) {
		super();
		this.title = title;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.client = client;
		this.employees = new ArrayList<Employee>();
		this.ticketCategory = ticketCategory;
		this.status = status;
		this.messages = new ArrayList<TicketMessage>();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public TicketCategory getTicketCategory() {
		return ticketCategory;
	}

	public void setTicketCategory(TicketCategory ticketCategory) {
		this.ticketCategory = ticketCategory;
	}

	public Status getState() {
		return status;
	}

	public void setState(Status state) {
		this.status = state;
	}

	public List<TicketMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<TicketMessage> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", title=" + title + ", creationDate=" + creationDate + ", updateDate=" + updateDate
				+ ", client=" + client + ", employees=" + employees + ", ticketCategory=" + ticketCategory + ", state="
				+ status + ", messages=" + messages + "]";
	}
	
	
}

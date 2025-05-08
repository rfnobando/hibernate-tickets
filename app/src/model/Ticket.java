package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Ticket {
	private long id;
	private String title;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Customer customer;
	private Set<Employee> employees;
	private TicketCategory ticketCategory;
	private Status status;
	private Set<TicketMessage> messages;
	
	public Ticket(){}
	
	public Ticket(String title, Customer customer, TicketCategory ticketCategory, Status status) {
		super();
		this.title = title;
		this.createdAt = Timestamp.valueOf(LocalDateTime.now());
		this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
		this.customer = customer;
		this.employees = new HashSet<Employee>();
		this.ticketCategory = ticketCategory;
		this.status = status;
		this.messages = new HashSet<TicketMessage>();
	}

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp  getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp  createdAt) {
		this.createdAt = createdAt;
	}
	
	public Timestamp  getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp  updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public TicketCategory getTicketCategory() {
		return ticketCategory;
	}

	public void setTicketCategory(TicketCategory ticketCategory) {
		this.ticketCategory = ticketCategory;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<TicketMessage> getMessages() {
		return messages;
	}

	public void setMessages(Set<TicketMessage> messages) {
		this.messages = messages;
	}
	
	public void addMessage(TicketMessage newMessage) {
		this.messages.add(newMessage);
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", title=" + title + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]\n";
	}
	
	public boolean equals(Ticket ticket) {
		return this.id == ticket.getId(); 
	}
}

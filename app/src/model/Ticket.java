package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
	private long id;
	private String title;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Customer customer;
	private List<Employee> employees;
	private TicketCategory ticketCategory;
	private Status status;
	private List<TicketMessage> messages;
	
	public Ticket(){}
	
	public Ticket(String title, LocalDateTime createdAt, LocalDateTime updatedAt, Customer customer,
			TicketCategory ticketCategory, Status status) {
		super();
		this.title = title;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.customer = customer;
		this.employees = new ArrayList<Employee>();
		this.ticketCategory = ticketCategory;
		this.status = status;
		this.messages = new ArrayList<TicketMessage>();
		
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<TicketMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<TicketMessage> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", title=" + title + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", customer=" + customer.getId() + ", employees=" + (employees != null ? employees.stream().map(c -> c.getId()).collect(Collectors.toList()) : null) + ", ticketCategory=" + ticketCategory + ", status="
				+ status + ", messages=" + messages + "]";
	}
	
	public boolean equals(Ticket ticket) {
		return this.id == ticket.getId(); 
	}
}

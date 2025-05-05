package service;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.EmployeeDAO;
import dao.TicketDAO;
import model.AttachedPicture;
import model.Customer;
import model.Status;
import model.Ticket;
import model.TicketCategory;
import model.Employee;
import model.TicketMessage;
import model.User;

public class TicketService {
	TicketDAO dao = new TicketDAO();
	
	// Receives the data to set up the Ticket and the TicketMessage	
	public long createTicket(String title, Timestamp createdAt, Customer customer, TicketCategory ticketCategory, String body, Set<AttachedPicture> attachedPictures)throws Exception{
		if (title == null || title.isEmpty() || customer == null || ticketCategory == null || body == null || body.isEmpty()) {
		    throw new Exception("ERROR: Some required ticket fields are missing.");
		}
		
		TicketMessageService ticketMessageService = new TicketMessageService();// Creates the TicketMessageService (ABM)
		StatusService statusService = new StatusService();// Creates the StatusService (ABM)
		
		Ticket t = new  Ticket(title,createdAt, customer, ticketCategory,statusService.getStatus(1));// Creates a new Ticket; status with ID 1 is "pending"
		long id = dao.create(t);// Persists the Ticket in the database
		
		TicketMessage msg = ticketMessageService.createNewTicketMessage(body, createdAt, customer, attachedPictures);// Creates the first TicketMessage
		msg.setTicket(t);// Links the message to the ticket
		
		t.addMessage(msg);// Adds the message to the ticket's message list
		dao.update(t); // Updates the ticket in the database (saves the cascade to messages)
		return id;
	}
	
	public long createTicketMessage(Ticket ticket, Timestamp createdAt, User user, String body, Set<AttachedPicture> attachedPictures)throws Exception {
		if(ticket.getStatus().getName().equals("closed")||ticket.getStatus().getName().equals("resolved"))throw new Exception("ERROR: The ticket is closed, you can't create a new message");
		
		TicketMessageService ticketMessageService = new TicketMessageService();// Creates the TicketMessageService (ABM)
		TicketMessage msg = ticketMessageService.createNewTicketMessage(body, createdAt, user, attachedPictures);// Creates the TicketMessage
		msg.setTicket(ticket);// Link it to the Ticket
		
		ticket.addMessage(msg);// Add message to the Ticket
		ticket.setUpdatedAt(createdAt);// Update the Ticket's timestamp
		dao.update(ticket);// Persist the changes to the DB, including the new message (thanks to cascade="save-update")
		
		return msg.getId();// Now the ID should be set
	}
	
	public void closeTicket(Ticket ticket)throws Exception{
		if(ticket.getStatus().getName().equals("closed")||ticket.getStatus().getName().equals("resolved"))throw new Exception("ERROR: The ticket is already closed");
		StatusService statusService = new StatusService();// Creates the StatusService (ABM)
		ticket.setStatus(statusService.getStatus(4));//status with ID 1 is "closed"
		dao.update(ticket);
	}
	
	public Ticket getTicket(long id) {
		return dao.get(id);
	}
	public Ticket getTicketWithStatus(long id) {
		return dao.getTicketWithStatus(id);
	}
		
	public void deleteTicketId(long id)throws Exception {
		Ticket ticketFound = dao.get(id);
		if (ticketFound == null) throw new Exception("Error: the Ticket doesn't exist.");
		dao.delete(ticketFound);
	}
	
}

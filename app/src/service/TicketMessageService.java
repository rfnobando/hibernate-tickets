package service;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;

import dao.TicketMessageDAO;
import model.AttachedPicture;
import model.TicketMessage;
import model.User;

public class TicketMessageService {
	TicketMessageDAO dao = new TicketMessageDAO();
	
	public TicketMessage createNewTicketMessage(String body, Timestamp createdAt, User user, Set<AttachedPicture> attachedPictures){
		TicketMessage t = new TicketMessage(body,createdAt,user);// Creates a new ticket message
		t.setAttachedPictures(attachedPictures);// Sets the attached pictures for the message
		// We don't use dao.create(t) because it is automatically saved when updating the ticket,
		// thanks to the cascade="save-update" setting in Ticket.hbm.xml
		return t;// Returns the configured TicketMessage
	}
}

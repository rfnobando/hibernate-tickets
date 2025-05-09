package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import model.Ticket;

public class TicketDAO extends BaseDAO<Ticket> {
	public Ticket getTicketWithStatusAndMessage(long id) {
	    Ticket ticket = null;
	    
	    try {
	        initTransaction();
	        ticket = (Ticket) session.createQuery(
	            "SELECT t FROM Ticket t JOIN FETCH t.status LEFT JOIN FETCH t.messages m LEFT JOIN FETCH m.user WHERE t.id = :id"
	        ).setParameter("id", id)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }
	    
	    return ticket;
	}
	
	public Ticket getTicketWithStatus(long id) {
	    Ticket ticket = null;
	    
	    try {
	        initTransaction();
	        ticket = (Ticket) session.createQuery(
	            "SELECT t FROM Ticket t JOIN FETCH t.status WHERE t.id = :id"
	        ).setParameter("id", id)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }
	    
	    return ticket;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Ticket> getTicketsWithEmptyEmployees() {
	    Set<Ticket> tickets;
	    try {
	        initTransaction();
	        Query query = session.createQuery(
	        	"SELECT t FROM Ticket t JOIN FETCH t.status LEFT JOIN FETCH t.employees WHERE t.employees IS EMPTY AND t.status.name = :statusName"
	        );
	        query.setParameter("statusName", "pending");
	        List<Ticket> ticketList = (List<Ticket>) query.list();
	        tickets = new HashSet<Ticket>(ticketList);
	    } finally {
	        session.close();
	    }
	    return tickets;
	}
}

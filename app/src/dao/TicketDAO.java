package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import model.Ticket;

public class TicketDAO extends BaseDAO<Ticket> {
	public Ticket getTicketWithStatusAndMessage(long id) {
	    Ticket ticket = null;
	    try {
	        initTransaction();
	        ticket = (Ticket) session.createQuery(
	            "SELECT t FROM Ticket t JOIN FETCH t.status LEFT JOIN FETCH t.messages WHERE t.id = :id"
	        ).setParameter("id", id)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }
	    return ticket;
	}
}

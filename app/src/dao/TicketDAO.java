package dao;

import model.Ticket;

public class TicketDAO extends BaseDAO<Ticket> {
	public Ticket getTicketWithStatus(long id) {
	    Ticket ticket = null;
	    try {
	        initTransaction();
	        ticket = (Ticket) session.createQuery(
	            "FROM Ticket t JOIN FETCH t.status WHERE t.id = :id"
	        ).setParameter("id", id)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }
	    return ticket;
	}
}

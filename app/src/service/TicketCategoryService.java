package service;

import java.util.List;

import dao.TicketCategoryDAO;
import model.TicketCategory;

public class TicketCategoryService {
	private final TicketCategoryDAO ticketCategoryDAO;
	
	public TicketCategoryService() {
		this.ticketCategoryDAO = new TicketCategoryDAO();
	}

    public TicketCategory getById(long id) {
        return ticketCategoryDAO.get(id);
    }

    public long create(TicketCategory category) {
        return ticketCategoryDAO.create(category);
    }

    public void update(TicketCategory category) {
    	ticketCategoryDAO.update(category);
    }

    public void delete(TicketCategory category) {
    	ticketCategoryDAO.delete(category);
    }

    public List<TicketCategory> getAll() {
        return ticketCategoryDAO.getAll();
    }

    public TicketCategory getByIdWithTickets(long id) {
        return ticketCategoryDAO.getWithTickets(id);
    }
}

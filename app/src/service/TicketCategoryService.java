package service;

import java.util.List;

import dao.TicketCategoryDAO;
import model.TicketCategory;

public class TicketCategoryService {
	 private TicketCategoryDAO categoryDAO = new TicketCategoryDAO();

	    public TicketCategory getById(long id) {
	        return categoryDAO.get(id);
	    }

	    public long create(TicketCategory category) {
	        return categoryDAO.create(category);
	    }

	    public void update(TicketCategory category) {
	    	categoryDAO.update(category);
	    }

	    public void delete(TicketCategory category) {
	    	categoryDAO.delete(category);
	    }

	    public List<TicketCategory> getAll() {
	        return categoryDAO.getAll();
	    }

	    public TicketCategory filterTicketsByCategories(long id) {
	        return categoryDAO.filterTicketsByCategory(id);
	    }
}

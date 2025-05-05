package service;

import dao.TicketCategoryDAO;
import model.TicketCategory;

public class TicketCategoryService {
	TicketCategoryDAO dao = new TicketCategoryDAO();
	
	public TicketCategory getId(long id) {
		return dao.get(id);
	}
}

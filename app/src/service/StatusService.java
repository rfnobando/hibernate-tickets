package service;

import dao.StatusDAO;
import model.Status;

public class StatusService {
	StatusDAO dao = new StatusDAO();
	
	public Status getStatus(long id) {
		return dao.get(id);
	}
	
	
}

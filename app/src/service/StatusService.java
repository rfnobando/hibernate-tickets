package service;

import dao.StatusDAO;
import model.Status;

public class StatusService {
	private final StatusDAO statusDAO;

	public StatusService() {
		this.statusDAO = new StatusDAO();
	}
	
	public Status getStatus(long id) {
		return statusDAO.get(id);
	}
}

package service;

import dao.UserDAO;
import model.User;

public class UserService {
	private final UserDAO userDAO;
	
	public UserService() {
		this.userDAO = new UserDAO();
	}
	
	public User getById(long id) {
		return userDAO.get(id);
	}
	
	public User getByIdWithInProgressTickets(long id) {
		return userDAO.getWithInProgressTickets(id);
	}
}

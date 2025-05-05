package service;

import dao.CustomerDAO;
import model.Customer;

public class CustomerService {
	CustomerDAO dao = new CustomerDAO();
	
	public Customer getId(long id) {
		return dao.get(id);
	}
}

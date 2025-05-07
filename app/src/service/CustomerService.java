package service;

import java.util.List;

import dao.CustomerDAO;
import model.Customer;

public class CustomerService {
	private final CustomerDAO customerDAO;
	
	public CustomerService() {
		this.customerDAO = new CustomerDAO();
	}

    public Customer getById(long id) {
        return customerDAO.get(id);
    }

    public long create(Customer customer) {
        return customerDAO.create(customer);
    }

    public void update(Customer customer) {
    	customerDAO.update(customer);
    }

    public void delete(Customer customer) {
    	customerDAO.delete(customer);
    }

    public List<Customer> getAll() {
        return customerDAO.getAll();
    }

    public Customer getByIdWithTickets(long id) {
        return customerDAO.getWithCreatedTickets(id);
    }
}

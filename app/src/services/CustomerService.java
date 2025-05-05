package services;

import java.util.List;
import dao.CustomerDao;
import model.Customer;
import model.Ticket;

public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();

    public Customer getById(long id) {
        return customerDao.get(id);
    }

    public long create(Customer customer) {
        return customerDao.create(customer);
    }

    public void update(Customer customer) {
        customerDao.update(customer);
    }

    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    public Customer getByIdWithTickets(long id) {
        return customerDao.getWithCreatedTickets(id);
    }
}
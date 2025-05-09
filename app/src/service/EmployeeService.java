package service;

import java.util.List;
import dao.EmployeeDAO;
import dao.StatusDAO;
import model.Employee;
import model.Status;
import model.Ticket;

public class EmployeeService {
	private final EmployeeDAO employeeDAO;
	private final StatusDAO statusDAO;
	 
	public EmployeeService() {
		this.employeeDAO = new EmployeeDAO();
		this.statusDAO = new StatusDAO();
	}
	
    public Employee getById(long id) {
        return employeeDAO.get(id);
    }

    public long create(Employee employee) {
        return employeeDAO.create(employee);
    }

    public void update(Employee employee) {
    	employeeDAO.update(employee);
    }

    public void delete(Employee employee) {
    	employeeDAO.delete(employee);
    }

    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public Employee getByIdWithTickets(long id) {
        return employeeDAO.getWithManagedTickets(id);
    }
    
    public Employee getByIdWithInProgressTickets(long id) {
    	return employeeDAO.getWithInProgressTickets(id);
    }
    
    public void addTicket(Employee employee, Ticket ticket) {
    	Status inProgressStatus = statusDAO.getByName("in_progress");
    	ticket.setStatus(inProgressStatus);
    	employee.getManagedTickets().add(ticket);
    	employeeDAO.update(employee);
    }
}

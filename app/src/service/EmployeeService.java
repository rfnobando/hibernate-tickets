package service;

import java.util.List;
import dao.EmployeeDAO;
import model.Employee;
import model.Ticket;
public class EmployeeService {
	private final EmployeeDAO employeeDAO;
	 
	public EmployeeService() {
		this.employeeDAO = new EmployeeDAO();
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
    
    public void addTicket(Employee employee, Ticket ticket) {
    	employee.getManagedTickets().add(ticket);
    	employeeDAO.update(employee);
    }
}

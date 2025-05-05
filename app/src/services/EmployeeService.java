package services;

import java.util.List;
import dao.EmployeeDAO;
import model.Employee;


public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

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

    public Employee getManagedTickets(long id) {
        return employeeDAO.getWithManagedTickets(id);
    }
}
package services;

import java.util.List;
import java.util.Set;

import dao.EmployeeDao;
import model.Employee;
import model.Ticket;

public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    public Employee getById(long id) {
        return employeeDao.get(id);
    }

    public long create(Employee employee) {
        return employeeDao.create(employee);
    }

    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }

    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    public Employee getManagedTickets(long id) {
        return employeeDao.getWithManagedTickets(id);
    }
}
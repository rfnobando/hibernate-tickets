package service;

import java.util.List;
import dao.EmployeeDAO;
import model.Employee;
public class EmployeeService {
	EmployeeDAO dao = new EmployeeDAO();
	
	//return all employees from DB
	public List<Employee> getAllEmployees(){
		return dao.getAll();
	};
}

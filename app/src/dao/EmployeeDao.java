package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import model.Customer;
import model.Employee;
import model.Ticket;

public class EmployeeDao extends BaseDao<Employee> {

	public Employee getWithManagedTickets(long id) throws HibernateException {
	    Employee employee = null;
	    try {
	        initTransaction();
	        employee = (Employee) session.createQuery(
	            "select e from Employee e left join fetch e.managedTickets where e.id = :id"
	        ).setParameter("id", id)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }
	    return employee;
	}
}


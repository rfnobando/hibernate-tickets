package dao;

import org.hibernate.HibernateException;
import model.Employee;
import model.Ticket;

public class EmployeeDAO extends BaseDAO<Employee> {
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
	
	public Employee getWithInProgressTickets(long id) throws HibernateException {
	    Employee employee = null;
	    
	    try {
	        initTransaction();
	        employee = (Employee) session.createQuery(
	            "select e from Employee e left join fetch e.managedTickets t left join fetch t.status s where e.id = :id and s.name = 'in_progress'"
	        ).setParameter("id", id)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }
	    
	    return employee;
	}
}


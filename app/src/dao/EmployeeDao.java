package dao;

import org.hibernate.HibernateException;


import model.Employee;

public class EmployeeDao extends BaseDAO<Employee> {
	   
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


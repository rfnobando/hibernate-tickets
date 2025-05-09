package dao;


import org.hibernate.HibernateException;
import model.Customer;

public class CustomerDAO extends BaseDAO<Customer> {
    public Customer getWithCreatedTickets(long id) {
        Customer customer = null;
        
        try {
            initTransaction();
            customer = (Customer) session.createQuery(
                "select c from Customer c left join fetch c.createdTickets where c.id = :id"
            ).setParameter("id", id)
             .uniqueResult();
        } finally {
            session.close();
        }
        
        return customer;
    }
    
	public Customer getWithInProgressTickets(long id) throws HibernateException {
	    Customer customer = null;
	    
	    try {
	        initTransaction();
	        customer = (Customer) session.createQuery(
	            "select c from Customer c left join fetch c.createdTickets t left join fetch t.status s left join fetch t.messages where c.id = :id and s.name = 'in_progress'"
	        ).setParameter("id", id)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }
	    
	    return customer;
	}
}
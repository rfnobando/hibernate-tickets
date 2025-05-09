package dao;

import model.Employee;
import model.User;
import org.hibernate.HibernateException;

import java.util.Optional;

public class UserDAO extends BaseDAO<User> {
    // This method retrieves a user by their email address.
    public Optional<User> findUserByEmail(String email) throws HibernateException {
        try {
            // Initialize the Hibernate transaction.
            initTransaction();
            // Execute an HQL query to find the user by email.
            User user = (User) session
                    .createQuery("from User u where u.email = :email") // Query to fetch the user.
                    .setParameter("email", email) // Set the email parameter in the query.
                    .uniqueResult(); // Retrieve a single result or null if no match is found.
            // Commit the transaction after successful query execution.
            tx.commit();
            // Return the user wrapped in an Optional.
            return Optional.ofNullable(user);
        } finally {
            // Ensure the session is closed to release resources.
            session.close();
        }
    }
    
	public User getWithInProgressTickets(long id) throws HibernateException {
	    User user = null;
	    
	    try {
	        initTransaction();
	        user = (User) session.createQuery(
	            "select e from Employee e left join fetch e.managedTickets t left join fetch t.status s where e.id = :id and s.name = 'in_progress'"
	        ).setParameter("id", id)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }
	    
	    return user;
	}
}
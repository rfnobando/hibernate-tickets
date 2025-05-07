package dao;


import org.hibernate.Hibernate;

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
}
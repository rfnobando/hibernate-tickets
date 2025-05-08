package dao;

import org.hibernate.HibernateException;

import model.Status;

public class StatusDAO extends BaseDAO<Status>{
	public Status getByName(String name) throws HibernateException {
	    Status status = null;
	    try {
	        initTransaction();
	        status = (Status) session.createQuery(
	            "select s from Status s where s.name = :name"
	        ).setParameter("name", name)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }
	    return status;
	}
}

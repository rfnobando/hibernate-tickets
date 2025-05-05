package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

public abstract class BaseDAO<T> {
    protected static Session session;
    protected Transaction tx;
    protected Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseDAO() {
        this.entityClass = (Class<T>) ((java.lang.reflect.ParameterizedType)
                getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected void initTransaction() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    protected void handleException(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Error in data access layer.", he);
    }

    public int create(T object) {
        int id = 0;

        try {
            initTransaction();
            id = Integer.parseInt(session.save(object).toString());
            tx.commit();
        } catch (HibernateException he) {
            handleException(he);
        } finally {
            session.close();
        }

        return id;
    }

    public void update(T object) {
        try {
            initTransaction();
            session.update(object);
            tx.commit();
        } catch (HibernateException he) {
            handleException(he);
        } finally {
            session.close();
        }
    }

    public void delete(T object) {
        try {
            initTransaction();
            session.delete(object);
            tx.commit();
        } catch (HibernateException he) {
            handleException(he);
        } finally {
            session.close();
        }
    }

    public T get(long id) {
        T object = null;

        try {
            initTransaction();
            object = session.get(entityClass, id);
        } finally {
            session.close();
        }

        return object;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        List<T> list = new ArrayList<>();

        try {
            initTransaction();
            String hql = "from " + entityClass.getSimpleName();

            Query query = session.createQuery(hql);
            list = (List<T>) query.list();
        } finally {
            session.close();
        }

        return list;
    }
}
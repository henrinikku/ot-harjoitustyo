package flashcardapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public abstract class BaseDao implements Dao {

    @Autowired
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Query createQuery(String query) {
        return getSession().createQuery(query);
    }

    protected void persist(Object o) {
        getSession().persist(o);
    }
}

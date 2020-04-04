package flashcardapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

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

    protected boolean persist(Object o) {
        try {
            getSession().persist(o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected Object getFirst(Query query) {
        return getFirst(query.getResultList());
    }

    protected Object getFirst(List list) {
        return (list == null || list.size() == 0) ? null : list.get(0);
    }
}

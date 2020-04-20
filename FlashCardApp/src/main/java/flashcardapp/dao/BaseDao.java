package flashcardapp.dao;

import flashcardapp.model.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public abstract class BaseDao<T extends BaseEntity> implements Dao {

    // EntityManager requires class of the generic type
    @SuppressWarnings("unchecked")
    private final Class<T> genericTypeClass =
        (Class<T>) GenericTypeResolver.resolveTypeArgument(
            getClass(), BaseDao.class
        );

    @Autowired
    protected SessionFactory sessionFactory;
    @Autowired
    protected EntityManager entityManager;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Query createQuery(String query) {
        return getSession().createQuery(query);
    }

    @Transactional
    protected boolean deleteByPk(Object pk) {
        try {
            T entity = entityManager.getReference(genericTypeClass, pk);
            entityManager.remove(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    protected boolean persist(T o) {
        try {
            getSession().persist(o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    protected T getFirst(Query query) {
        return getFirst(query.getResultList());
    }

    @SuppressWarnings("unchecked")
    protected T getFirst(List list) {
        return (list == null || list.size() == 0) ? null : (T) list.get(0);
    }
}

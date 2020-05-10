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

/**
 * Base class for all Hibernate based Dao classes
 *
 * @see flashcardapp.model.BaseEntity
 *
 * @param <T> The model class for which the dao class provides db operations
 */
@Repository
public abstract class BaseDao<T extends BaseEntity> implements Dao {

    // EntityManager requires class of the generic type
    @SuppressWarnings("unchecked")
    private final Class<T> genericTypeClass =
        (Class<T>) GenericTypeResolver.resolveTypeArgument(
            getClass(), BaseDao.class
        );

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Creates a query from the given string
     *
     * @param query the query string
     * @return Query object created using the given string
     */
    protected Query createQuery(String query) {
        return getSession().createQuery(query);
    }

    /**
     * Removes entity by its primary key.
     *
     * @param pk The primary key of the object we want to remove.
     * @return A boolean value indicating whether the operation was successful
     */
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

    /**
     * Inserts the given object to the database.
     *
     * @param object The object we want to insert to the database
     * @return A boolean value indicating whether the operation was successful
     */
    @Transactional
    protected boolean persist(T object) {
        try {
            getSession().persist(object);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Inserts the given object to the database, or updates it if it exists
     *
     * @param object The object we want to save or update
     * @return A boolean value indicating whether the operation was successful
     */
    @Transactional
    protected boolean addOrUpdate(T object) {
        try {
            getSession().saveOrUpdate(object);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Executes the given query and returns the first object from the result.
     *
     * @param query the query we want to get the first value from
     * @return the first value from the result, or null if the result is empty
     */
    @Transactional
    protected T getFirst(Query query) {
        return getFirst(query.getResultList());
    }

    @SuppressWarnings("unchecked")
    private T getFirst(List list) {
        return (list == null || list.size() == 0) ? null : (T) list.get(0);
    }
}

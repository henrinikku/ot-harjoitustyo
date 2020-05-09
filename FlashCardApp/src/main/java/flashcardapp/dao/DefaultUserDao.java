package flashcardapp.dao;

import flashcardapp.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 * Provides database operations related to the User model
 *
 * @see flashcardapp.model.User
 */
@Repository
public class DefaultUserDao extends BaseDao<User> implements UserDao {

    /**
     * Adds the given user to the database.
     *
     * @param user The user we want to insert to the database
     * @return A boolean value indicating whether the operation was successful
     */
    @Override
    @Transactional
    public boolean addUser(User user) {
        return persist(user);
    }

    /**
     * Retrieves an user by the given username
     *
     * @param username The username we want to search for
     * @return The user with the given username, or null
     */
    @Override
    @Transactional
    public User getByUsername(String username) {
        Query query = createQuery("from User where username=?0");
        return getFirst(query.setParameter(0, username));
    }
}

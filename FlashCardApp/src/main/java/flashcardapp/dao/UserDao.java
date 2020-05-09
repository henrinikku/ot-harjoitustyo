package flashcardapp.dao;

import flashcardapp.model.User;

/**
 * Interface that provides a persistence layer for the User model
 *
 * @see flashcardapp.model.User
 */
public interface UserDao extends Dao {
    /**
     * Adds the given user to some persistent storage
     *
     * @param user The user we want to add
     * @return A boolean value indicating whether the operation was successful
     */
    boolean addUser(User user);

    /**
     * Retrieves an user by its username
     *
     * @param username The username we want to search with
     * @return An user with the given username, or null
     */
    User getByUsername(String username);
}

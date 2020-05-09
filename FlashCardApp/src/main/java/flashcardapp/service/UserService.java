package flashcardapp.service;

import flashcardapp.model.User;

/**
 * Interface for User-related business logic
 *
 * @see flashcardapp.model.User
 */
public interface UserService {
    /**
     * Adds the given user to collection used by the implementing class
     * @param user The user we want to add
     * @return A boolean value indicating whether the operation was successful
     */
    boolean addUser(User user);

    /**
     * Checks if the given username and password match
     *
     * @param username The username to check
     * @param password The password to check
     * @return A boolean value indicating whether the credentials are valid
     */
    boolean checkCredentials(String username, String password);

    /**
     * Checks if the given username is valid
     *
     * @param username The username to validate
     * @return A boolean value indicating whether the given username is valid
     */
    boolean validateUsername(String username);

    /**
     * Retrieves user by username from the collection used by the implementing
     * class.
     *
     * @param username The username to search for
     * @return User with the given username
     */
    User getByUsername(String username);
}

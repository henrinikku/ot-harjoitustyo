package flashcardapp.service;

import flashcardapp.dao.UserDao;
import flashcardapp.model.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static flashcardapp.util.StringUtils.isNullOrWhitespace;

/**
 * Encapsulates user-related business logic.
 */
@Service
public class DefaultUserService implements UserService {

    private UserDao userDao;
    private PasswordEncoder encoder;

    /**
     * Used to inject dependencies.
     *
     * @param userDao Object implementing the UserDao-interface.
     * @param encoder Object implementing the PasswordEncoder-interface.
     */
    @Autowired
    public DefaultUserService(
        @NonNull UserDao userDao,
        @NonNull PasswordEncoder encoder
    ) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    /**
     * If the given user is valid, hashes its password and saves it.
     *
     * @param user The user to be added.
     * @return a boolean value indicating whether the operation was successful.
     */
    @Override
    public boolean addUser(@NonNull User user) {
        if (isNullOrWhitespace(user.getUsername())
            || isNullOrWhitespace(user.getPassword())
        ) {
            return false;
        }
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        return userDao.addUser(user);
    }

    /**
     * Checks if the given username and password match.
     *
     * @param username The username to validate.
     * @param password The password to validate.
     * @return a boolean value indicating whether the given username
     *         and password match.
     */
    @Override
    public boolean checkCredentials(
        @NonNull String username,
        @NonNull String password
    ) {
        User claim = userDao.getByUsername(username);
        if (claim == null) {
            return false;
        }
        return encoder.matches(password, claim.getPassword());
    }

    /**
     * Validates the given username. I.e. it is not empty or in use.
     *
     * @param username The username to validate.
     * @return a boolean value indicating whether the given username is valid.
     */
    @Override
    public boolean validateUsername(@NonNull String username) {
        return !username.isBlank() && userDao.getByUsername(username) == null;
    }

    /**
     * Retrieves user with the given username.
     *
     * @param username The username to look for.
     * @return User-object with the given username, or null if it doesn't exist.
     */
    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }
}

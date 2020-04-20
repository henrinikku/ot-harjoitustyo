package flashcardapp.service;

import flashcardapp.dao.UserDao;
import flashcardapp.model.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static flashcardapp.util.StringUtils.isNullOrWhitespace;

@Service
public class DefaultUserService implements UserService {

    private UserDao userDao;
    private PasswordEncoder encoder;

    @Autowired
    public DefaultUserService(
        @NonNull UserDao userDao,
        @NonNull PasswordEncoder encoder
    ) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

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

    @Override
    public boolean validateUsername(@NonNull String username) {
        return !username.isBlank() && userDao.getByUsername(username) == null;
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }
}

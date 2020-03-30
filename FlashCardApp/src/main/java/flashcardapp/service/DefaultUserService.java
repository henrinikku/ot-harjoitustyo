package flashcardapp.service;

import flashcardapp.dao.DefaultUserDao;
import flashcardapp.dao.UserDao;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import flashcardapp.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private UserDao userDao;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public DefaultUserService(
        @NonNull UserDao userDao,
        @NonNull BCryptPasswordEncoder encoder
    ) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public boolean addUser(@NonNull User user) {
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        return userDao.addUser(user);
    }

    @Override
    @Transactional
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
    @Transactional
    public boolean validateUsername(@NonNull String username) {
        return !username.isBlank() && userDao.getByUsername(username) == null;
    }

    @Override
    @Transactional
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }
}

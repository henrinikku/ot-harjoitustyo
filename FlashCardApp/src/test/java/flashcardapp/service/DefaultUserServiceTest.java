package flashcardapp.service;

import flashcardapp.dao.FakeUserDao;
import flashcardapp.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class DefaultUserServiceTest {

    private DefaultUserService userService;

    @Before
    public void setUp() {
        FakeUserDao userDao = new FakeUserDao();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userService = new DefaultUserService(userDao, encoder);
    }

    @Test
    public void userWithoutNameCannotBeAdded() {
        User user = new User();
        user.setUsername(null);
        user.setPassword("test");
        assertFalse(userService.addUser(user));
    }

    @Test
    public void userWithoutPasswordCannotBeAdded() {
        User user = new User();
        user.setUsername("test");
        user.setPassword(null);
        assertFalse(userService.addUser(user));
    }

    @Test
    public void userWithValidCredentialsCanBeAdded() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        assertTrue(userService.addUser(user));
    }

    @Test
    public void emptyUsernameIsNotAccepted() {
        assertFalse(userService.validateUsername(""));
    }

    @Test
    public void addedUserCanBeFound() {
        User user = new User("testi123", "testi123");
        userService.addUser(user);
        assertNotNull(userService.getByUsername(user.getUsername()));
    }

    @Test
    public void wrongPasswordIsNotAccepted() {
        User user = new User("testi123", "testi123");
        userService.addUser(user);
        assertFalse(
            userService.checkCredentials(user.getUsername(), "wrong")
        );
    }

    @Test
    public void correctPasswordIsAccepted() {
        User user = new User("testi123", "testi123");
        userService.addUser(user);
        assertTrue(
            userService.checkCredentials(user.getUsername(), "testi123")
        );
    }
}

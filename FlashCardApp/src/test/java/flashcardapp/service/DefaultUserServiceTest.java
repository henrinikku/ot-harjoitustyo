package flashcardapp.service;

import flashcardapp.dao.UserDao;
import flashcardapp.model.User;
import flashcardapp.util.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config-test.xml")
public class DefaultUserServiceTest {

    private DefaultUserService userService;

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Before
    @Transactional
    public void setUp() {
        userService = new DefaultUserService(userDao, encoder);
    }

    @Test
    @Transactional
    public void userWithoutNameCannotBeAdded() {
        User user = new User();
        user.setUsername(null);
        user.setPassword("test");
        assertFalse(userService.addUser(user));
    }

    @Test
    @Transactional
    public void userWithoutPasswordCannotBeAdded() {
        User user = new User();
        user.setUsername("test");
        user.setPassword(null);
        assertFalse(userService.addUser(user));
    }

    @Test
    @Transactional
    public void userWithValidCredentialsCanBeAdded() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        assertTrue(userService.addUser(user));
    }

    @Test
    @Transactional
    public void emptyUsernameIsNotAccepted() {
        assertFalse(userService.validateUsername(""));
    }

    @Test
    @Transactional
    public void addedUserCanBeFound() {
        User user = new User("testi123", "testi123");
        userService.addUser(user);
        assertNotNull(userService.getByUsername(user.getUsername()));
    }

    @Test
    @Transactional
    public void wrongPasswordIsNotAccepted() {
        User user = new User("testi123", "testi123");
        userService.addUser(user);
        assertFalse(
            userService.checkCredentials(user.getUsername(), "wrong")
        );
    }

    @Test
    @Transactional
    public void correctPasswordIsAccepted() {
        User user = new User("testi123", "testi123");
        userService.addUser(user);
        assertTrue(
            userService.checkCredentials(user.getUsername(), "testi123")
        );
    }

    @Test
    @Transactional
    public void userWithTooLongUsernameIsNotAccepted() {
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < StringUtils.STRING_MAX_LENGTH * 2; i++) {
            username.append("a");
        }
        assertFalse(userService.validateUsername(username.toString()));
    }
}

package flashcardapp.dao;

import flashcardapp.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config-test.xml")
public class DefaultUserDaoTest {

    @Autowired
    private DefaultUserDao userDao;

    @Before
    @Transactional
    public void setUp() {
    }

    @Test
    @Transactional
    public void nullUserCannotBeAdded() {
        assertFalse(userDao.addUser(null));
    }

    @Test
    @Transactional
    public void validUserCanBeAdded() {
        User user = new User("test", "test");
        assertTrue(userDao.addUser(user));
    }

    @Test
    @Transactional
    public void userWithPredefinedIdCannotBeAdded() {
        User user = new User("test", "test");
        user.setId(666);
        assertFalse(userDao.addUser(user));
    }

    @Test
    @Transactional
    public void getByUsernameReturnsNullIfUserDoesNotExist() {
        User user = new User("test", "test");
        assertTrue(userDao.addUser(user));
        assertNull(userDao.getByUsername("this username does not exist"));
    }
}

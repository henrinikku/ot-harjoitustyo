package flashcardapp.service;

import flashcardapp.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultSessionServiceTest {

    private SessionService sessionService;
    private User user;

    @Before
    public void setUp() {
        sessionService = new DefaultSessionService();
        user = new User("test", "test");
    }

    @Test
    public void userCanBeLoggedIn() {
        sessionService.setLoggedInUser(user);
        assertEquals(sessionService.getLoggedInUser(), user);
    }

    @Test
    public void loggingOutSetsStateToNull() {
        sessionService.setLoggedInUser(user);
        sessionService.logOut();
        assertNull(sessionService.getLoggedInUser());
    }
}

package flashcardapp.service;

import flashcardapp.dao.DeckDao;
import flashcardapp.dao.UserDao;
import flashcardapp.model.Deck;
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
public class DefaultDeckServiceTest {

    private DefaultDeckService deckService;
    private SessionService sessionService;
    private User user;

    @Autowired
    private DeckDao deckDao;
    @Autowired
    private UserDao userDao;

    @Before
    @Transactional
    public void setUp() {
        sessionService = new DefaultSessionService();
        deckService = new DefaultDeckService(deckDao, sessionService);
        user = new User("testi", "testi");
        userDao.addUser(user);
        sessionService.setLoggedInUser(user);
    }

    @Test
    @Transactional
    public void deckWithoutNameCannotBeAdded() {
        Deck deck = new Deck(null, "testtt");
        assertFalse(deckService.addDeck(deck));
    }

    @Test
    @Transactional
    public void deckCannotBeAddedWithoutLoggingIn() {
        Deck deck = new Deck("testt", "testt");
        sessionService.setLoggedInUser(null);
        assertFalse(deckService.addDeck(deck));
    }

    @Test
    @Transactional
    public void deckWithValidNameCanBeAdded() {
        Deck deck = new Deck("testt", "testt");
        assertTrue(deckService.addDeck(deck));
    }

    @Test
    @Transactional
    public void getAllOnlyReturnsDecksForLoggedInUser() {
        int initialCount = deckService.getAll().size();
        Deck newDeck = new Deck("other", "deck");
        User owner = new User("other", "user");
        userDao.addUser(owner);
        newDeck.setOwner(owner);
        deckDao.addDeck(newDeck);
        assertEquals(deckService.getAll().size(), initialCount);
    }

    @Test
    @Transactional
    public void selectedDeckIsNotDeletedIfNull() {
        assertFalse(deckService.deleteSelected());
    }

    @Test
    @Transactional
    public void deletingDeckAffectsDeckCount() {
        Deck deck = new Deck("to be", "deleted");
        deckService.addDeck(deck);
        deckService.setSelectedDeck(deck);
        int countBefore = deckService.getAll().size();
        assertTrue(deckService.deleteSelected());
        assertNotEquals(countBefore, deckService.getAll().size());
    }
}

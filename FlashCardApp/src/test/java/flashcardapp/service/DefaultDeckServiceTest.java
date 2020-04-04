package flashcardapp.service;

import flashcardapp.dao.FakeDeckDao;
import flashcardapp.model.Deck;
import flashcardapp.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultDeckServiceTest {

    private DefaultDeckService deckService;
    private SessionService sessionService;

    @Before
    public void setUp() {
        FakeDeckDao deckDao = new FakeDeckDao();
        sessionService = new DefaultSessionService();
        deckService = new DefaultDeckService(deckDao, sessionService);
        sessionService.setLoggedInUser(
            new User("testi", "testi"));
    }

    @Test
    public void deckWithoutNameCannotBeAdded() {
        Deck deck = new Deck(null, "testtt");
        assertFalse(deckService.addDeck(deck));
    }

    @Test
    public void deckCannotBeAddedWithoutLoggingIn() {
        Deck deck = new Deck("testt", "testt");
        sessionService.setLoggedInUser(null);
        assertFalse(deckService.addDeck(deck));
    }

    @Test
    public void deckWithValidNameCanBeAdded() {
        Deck deck = new Deck("testt", "testt");
        assertTrue(deckService.addDeck(deck));
    }
}

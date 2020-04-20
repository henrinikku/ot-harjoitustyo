package flashcardapp.service;

import flashcardapp.dao.DeckDao;
import flashcardapp.dao.FakeDeckDao;
import flashcardapp.model.Deck;
import flashcardapp.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultDeckServiceTest {

    private DefaultDeckService deckService;
    private SessionService sessionService;
    private DeckDao deckDao;
    private User user;

    @Before
    public void setUp() {
        deckDao = new FakeDeckDao();
        sessionService = new DefaultSessionService();
        deckService = new DefaultDeckService(deckDao, sessionService);
        user = new User("testi", "testi");
        user.setId(999);
        sessionService.setLoggedInUser(user);
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

    @Test
    public void getAllOnlyReturnsDecksForLoggedInUser() {
        int initialCount = deckService.getAll().size();
        Deck newDeck = new Deck("other", "deck");
        newDeck.setOwner(new User("other", "user"));
        deckDao.addDeck(newDeck);
        assertEquals(deckService.getAll().size(), initialCount);
    }

    @Test
    public void selectedDeckIsNotDeletedIfNull() {
        assertFalse(deckService.deleteSelected());
    }

    @Test
    public void deletingDeckAffectsDeckCount() {
        Deck deck = new Deck("to be", "deleted");
        deck.setId(1);
        deckService.addDeck(deck);
        deckService.setSelectedDeck(deck);
        int countBefore = deckService.getAll().size();
        assertTrue(deckService.deleteSelected());
        assertNotEquals(countBefore, deckService.getAll().size());
    }
}

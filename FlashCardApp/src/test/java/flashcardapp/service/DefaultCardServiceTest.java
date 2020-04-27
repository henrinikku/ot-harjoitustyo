package flashcardapp.service;

import flashcardapp.dao.CardDao;
import flashcardapp.dao.DeckDao;
import flashcardapp.dao.FakeCardDao;
import flashcardapp.dao.FakeDeckDao;
import flashcardapp.model.Card;
import flashcardapp.model.Deck;
import flashcardapp.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DefaultCardServiceTest {

    private DefaultCardService cardService;
    private DefaultDeckService deckService;
    private SessionService sessionService;
    private CardDao cardDao;
    private DeckDao deckDao;

    private Card card;

    @Before
    public void setUp() {
        cardDao = new FakeCardDao();
        deckDao = new FakeDeckDao();
        sessionService = new DefaultSessionService();
        deckService = new DefaultDeckService(deckDao, sessionService);
        cardService = new DefaultCardService(
            cardDao, deckService, sessionService
        );

        User user = new User("testi", "testi");
        user.setId(999);
        sessionService.setLoggedInUser(user);

        Deck deck = new Deck("test deck", "test description");
        deck.setId(666);
        deckService.setSelectedDeck(deck);

        card = new Card("test card", "test question", "test answer");
    }

    @Test
    public void nullCardCannotBeSaved() {
        assertFalse(cardService.saveCard(null));
    }

    @Test
    public void cardWithoutNameCannotBeSaved() {
        card.setName(null);
        assertFalse(cardService.saveCard(card));
    }

    @Test
    public void cardCannotBeAddedWithoutLoggingIn() {
        sessionService.setLoggedInUser(null);
        assertFalse(cardService.saveCard(card));
    }

    @Test
    public void cardCannotBeAddedWithoutSelectingDeck() {
        deckService.setSelectedDeck(null);
        assertFalse(cardService.saveCard(card));
    }

    @Test
    public void cardWithValidNameCanBeAdded() {
        assertTrue(cardService.saveCard(card));
    }

    @Test
    public void ownerAndDeckAreSetOnSave() {
        assertTrue(cardService.saveCard(card));
        assertEquals(deckService.getSelectedDeck(), card.getDeck());
    }

    @Test
    public void addedCardCanBeFoundByDeck() {
        assertTrue(cardService.saveCard(card));
        assertFalse(cardService.getForSelectedDeck().isEmpty());
        assertEquals(cardService.getForSelectedDeck().get(0), card);
    }

    @Test
    public void getForSelectedDeckReturnsEmptyListOfNoDeckIsSelected() {
        assertTrue(cardService.saveCard(card));
        deckService.setSelectedDeck(null);
        List<Card> cards = cardService.getForSelectedDeck();
        assertNotNull(cards);
        assertTrue(cards.isEmpty());
    }

    @Test
    public void deletingSelectedCardRemovesIt() {
        assertTrue(cardService.saveCard(card));
        int countBefore = cardService.getForSelectedDeck().size();
        cardService.setSelectedCard(card);
        assertTrue(cardService.deleteSelected());
        assertNotEquals(cardService.getForSelectedDeck().size(), countBefore);
    }

    @Test
    public void deletingSelectedFailsIfSelectedIsNull() {
        cardService.setSelectedCard(null);
        assertFalse(cardService.deleteSelected());
    }
}

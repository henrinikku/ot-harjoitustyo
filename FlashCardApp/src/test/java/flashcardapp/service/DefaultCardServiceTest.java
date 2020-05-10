package flashcardapp.service;

import flashcardapp.dao.CardDao;
import flashcardapp.dao.DeckDao;
import flashcardapp.dao.UserDao;
import flashcardapp.model.Card;
import flashcardapp.model.Deck;
import flashcardapp.model.User;
import flashcardapp.util.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config-test.xml")
public class DefaultCardServiceTest {

    private Card card;
    private DefaultCardService cardService;
    private DefaultDeckService deckService;
    private SessionService sessionService;

    @Autowired
    private CardDao cardDao;
    @Autowired
    private DeckDao deckDao;
    @Autowired
    private UserDao userDao;

    @Before
    @Transactional
    public void setUp() {
        sessionService = new DefaultSessionService();
        deckService = new DefaultDeckService(deckDao, sessionService);
        cardService = new DefaultCardService(
            cardDao, deckService, sessionService
        );

        User user = new User("testi", "testi");
        userDao.addUser(user);
        sessionService.setLoggedInUser(user);

        Deck deck = new Deck("test deck", "test description");
        deck.setOwner(user);
        deckDao.addDeck(deck);
        deckService.setSelectedDeck(deck);

        card = new Card("test card", "test question", "test answer");
    }

    private static String getTooLongString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < StringUtils.STRING_MAX_LENGTH * 2; i++) {
            result.append("a");
        }
        return result.toString();
    }

    @Test
    @Transactional
    public void nullCardCannotBeSaved() {
        assertFalse(cardService.saveCard(null));
    }

    @Test
    @Transactional
    public void cardWithoutNameCannotBeSaved() {
        card.setName(null);
        assertFalse(cardService.saveCard(card));
    }

    @Test
    @Transactional
    public void cardWithoutQuestionCannotBeSaved() {
        card.setQuestion(null);
        assertFalse(cardService.saveCard(card));
    }

    @Test
    @Transactional
    public void cardWithoutAnswerCannotBeSaved() {
        card.setAnswer(null);
        assertFalse(cardService.saveCard(card));
    }

    @Test
    @Transactional
    public void cardCannotBeAddedWithoutLoggingIn() {
        sessionService.setLoggedInUser(null);
        assertFalse(cardService.saveCard(card));
    }

    @Test
    @Transactional
    public void cardCannotBeAddedWithoutSelectingDeck() {
        deckService.setSelectedDeck(null);
        assertFalse(cardService.saveCard(card));
    }

    @Test
    @Transactional
    public void cardWithValidNameCanBeAdded() {
        assertTrue(cardService.saveCard(card));
    }

    @Test
    @Transactional
    public void ownerAndDeckAreSetOnSave() {
        assertTrue(cardService.saveCard(card));
        assertEquals(deckService.getSelectedDeck(), card.getDeck());
    }

    @Test
    @Transactional
    public void addedCardCanBeFoundByDeck() {
        assertTrue(cardService.saveCard(card));
        assertFalse(cardService.getForSelectedDeck().isEmpty());
        assertEquals(cardService.getForSelectedDeck().get(0), card);
    }

    @Test
    @Transactional
    public void getForSelectedDeckReturnsEmptyListOfNoDeckIsSelected() {
        assertTrue(cardService.saveCard(card));
        deckService.setSelectedDeck(null);
        List<Card> cards = cardService.getForSelectedDeck();
        assertNotNull(cards);
        assertTrue(cards.isEmpty());
    }

    @Test
    @Transactional
    public void deletingSelectedCardRemovesIt() {
        assertTrue(cardService.saveCard(card));
        int countBefore = cardService.getForSelectedDeck().size();
        cardService.setSelectedCard(card);
        assertTrue(cardService.deleteSelected());
        assertNotEquals(cardService.getForSelectedDeck().size(), countBefore);
    }

    @Test
    @Transactional
    public void deletingSelectedFailsIfSelectedIsNull() {
        cardService.setSelectedCard(null);
        assertFalse(cardService.deleteSelected());
    }

    @Test
    @Transactional
    public void nextCardReturnsNullIfNoCardsExist() {
        assertNull(cardService.nextCard());
    }

    @Test
    @Transactional
    public void nextCardReturnsNullIfNoDeckIsSelected() {
        Card card = new Card("test", "test", "test");
        cardService.saveCard(card);
        deckService.setSelectedDeck(null);
        assertNull(cardService.nextCard());
    }

    @Test
    @Transactional
    public void nextCardReturnsCardIfItExists() {
        Card card = new Card("test", "test", "test");
        cardService.saveCard(card);
        Card next = cardService.nextCard();
        assertNotNull(next);
        assertEquals(next.getId(), card.getId());
    }

    @Test
    @Transactional
    public void cardWithTooLongQuestionIsNotSaved() {
        Card card = new Card("test", getTooLongString(), "test");
        assertFalse(cardService.saveCard(card));
    }
}

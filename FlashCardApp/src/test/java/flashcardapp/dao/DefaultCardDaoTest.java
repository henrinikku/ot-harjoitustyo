package flashcardapp.dao;

import flashcardapp.model.Card;
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
public class DefaultCardDaoTest {

    @Autowired
    private DefaultCardDao cardDao;
    @Autowired
    private DefaultDeckDao deckDao;
    @Autowired
    private DefaultUserDao userDao;

    private Card card;

    @Before
    @Transactional
    public void setUp() {
        User owner = new User("test", "test");
        userDao.addUser(owner);
        Deck deck = new Deck("test", "test");
        deck.setOwner(owner);
        deckDao.addDeck(deck);
        card = new Card("test", "test", "test");
        card.setOwner(owner);
        card.setDeck(deck);
    }

    @Test
    @Transactional
    public void nullCardCannotBeSaved() {
        assertFalse(cardDao.addOrUpdateCard(null));
    }

    @Test
    @Transactional
    public void cardWithoutDeckCannotBeSaved() {
        card.setDeck(null);
        assertFalse(cardDao.addOrUpdateCard(card));
    }

    @Test
    @Transactional
    public void cardWithoutOwnerCannotBeSaved() {
        card.setOwner(null);
        assertFalse(cardDao.addOrUpdateCard(card));
    }

    @Test
    @Transactional
    public void validCardCanBeSaved() {
        assertTrue(cardDao.addOrUpdateCard(card));
    }

    @Test
    @Transactional
    public void getRandomFromDeckReturnsNullIfDeckDoesNotExist() {
        assertTrue(cardDao.addOrUpdateCard(card));
        assertNull(cardDao.getRandomFromDeck(999));
    }

    @Test
    @Transactional
    public void getRandomFromDeckReturnsCardIfDeckExists() {
        assertTrue(cardDao.addOrUpdateCard(card));
        assertNotNull(cardDao.getRandomFromDeck(card.getDeck().getId()));
    }
}

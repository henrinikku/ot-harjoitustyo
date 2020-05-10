package flashcardapp.dao;

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
public class DefaultDeckDaoTest {

    @Autowired
    private DefaultDeckDao deckDao;
    @Autowired
    private DefaultUserDao userDao;

    @Before
    @Transactional
    public void setUp() {
    }

    @Test
    @Transactional
    public void nullDeckCannotBeAdded() {
        assertFalse(deckDao.addDeck(null));
    }

    @Test
    @Transactional
    public void validDeckCanBeAdded() {
        User owner = new User("testuser", "testpassword");
        userDao.addUser(owner);
        Deck deck = new Deck("test", "test");
        deck.setOwner(owner);
        assertTrue(deckDao.addDeck(deck));
    }

    @Test
    @Transactional
    public void deckWithPredefinedIdCannotBeAdded() {
        Deck deck = new Deck("test", "test");
        deck.setId(666);
        assertFalse(deckDao.addDeck(deck));
    }

    @Test
    @Transactional
    public void deleteByIdDoesNotSucceedIfIdDoesNotExist() {
        assertFalse(deckDao.deleteById(0));
    }

    @Test
    @Transactional
    public void deleteByIdSucceedsIfIdExists() {
        User owner = new User("testuser", "testpassword");
        userDao.addUser(owner);
        Deck deck = new Deck("test", "test");
        deck.setOwner(owner);
        assertTrue(deckDao.addDeck(deck));
        assertTrue(deckDao.deleteById(deck.getId()));
    }
}

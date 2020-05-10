package flashcardapp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DeckTest {

    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck("name", "description");
    }

    @Test
    public void changingNameAffectsStringRepresentation() {
        String newName = "new name";
        deck.setName(newName);
        assertTrue(deck.getName().contains(newName));
    }

    @Test
    public void deckHasNoCardsInitially() {
        assertNull(deck.getCards());
    }
}

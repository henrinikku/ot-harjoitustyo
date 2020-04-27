package flashcardapp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CardTest {

    private Card card;

    @Before
    public void setUp() {
        card = new Card();
    }

    @Test
    public void changingNameAffectsStringRepresentation() {
        String newName = "new name";
        card.setName(newName);
        assertTrue(card.getName().contains(newName));
    }
}

package flashcardapp.dao;

import flashcardapp.model.Card;

import java.util.List;

/**
 * Interface that provides a persistence layer for the Card model
 *
 * @see flashcardapp.model.Card
 */
public interface CardDao extends Dao {
    /**
     * Adds the given card to persistent storage, or updates it if it exists
     *
     * @param card The card we want to persist
     * @return A boolean value indicating whether the operation was successful
     */
    boolean addOrUpdateCard(Card card);

    /**
     * Deletes the given card
     *
     * @param card The card we want to delete
     * @return A boolean value indicating whether the operation was successful
     */
    boolean deleteCard(Card card);

    /**
     * Retrieves a random card that belongs to the given deck
     *
     * @param deckId The deck from which to pull the random card
     * @return A random card that belongs to the given deck
     */
    Card getRandomFromDeck(long deckId);

    /**
     * Retrieves a list of cards belonging to the given deck
     *
     * @param deckId The deck we want to search with
     * @return A list of cards belonging to the given deck
     */
    List<Card> getByDeck(long deckId);
}

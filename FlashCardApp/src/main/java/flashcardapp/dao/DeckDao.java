package flashcardapp.dao;

import flashcardapp.model.Deck;

import java.util.List;

/**
 * Interface that provides a persistence layer for the Deck model
 *
 * @see flashcardapp.model.Deck
 */
public interface DeckDao extends Dao {
    /**
     * Adds the given deck to some persistent storage
     *
     * @param deck The deck we want to add
     * @return A boolean value indicating whether the operation was successful
     */
    boolean addDeck(Deck deck);

    /**
     * Retrieves a deck by its name
     *
     * @param name The name we want to search with
     * @return A deck with the given name, or null
     */
    Deck getByName(String name);

    /**
     * Deletes a deck by its id
     *
     * @param id Id of the deck we want to delete
     * @return A boolean value indicating whether the operation was successful
     */
    boolean deleteById(long id);

    /**
     * Retrieves list of decks belonging to the given user
     *
     * @param userId The user whose decks we want to retrieve
     * @return List of decks belonging to the given user
     */
    List<Deck> getByUserId(long userId);
}

package flashcardapp.service;

import flashcardapp.model.Deck;

import java.util.List;

/**
 * Interface for Deck-related business logic
 *
 * @see flashcardapp.model.Deck
 */
public interface DeckService {
    /**
     * Adds the given deck to collection used by the implementing class
     *
     * @param deck The deck we want to add
     * @return A boolean value indicating whether the operation was successful
     */
    boolean addDeck(Deck deck);

    /**
     * Checks if the given name is valid
     *
     * @param name The username to validate
     * @return A boolean value indicating whether the given name is valid
     */
    boolean validateName(String name);

    /**
     * Retrieves all decks for the currently logged in user
     *
     * @return List of decks belonging to the user that is logged in
     */
    List<Deck> getAll();

    /**
     * Deletes the given deck
     *
     * @param deck The deck we want to delete
     * @return A boolean value indicating whether the operation was successful
     */
    boolean deleteDeck(Deck deck);

    /**
     * Deletes the deck that is currently selected
     *
     * @return A boolean value indicating whether the operation was successful
     */
    boolean deleteSelected();

    /**
     * Retrieves the deck that is currently selected
     *
     * @return The deck that is currently selected
     */
    Deck getSelectedDeck();

    /**
     * Sets the selected deck
     *
     * @param deck The deck we want to set as selected
     */
    void setSelectedDeck(Deck deck);
}

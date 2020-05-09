package flashcardapp.service;

import flashcardapp.model.Card;

import java.util.List;

/**
 * Interface for Card-related business logic
 *
 * @see flashcardapp.model.Card
 */
public interface CardService {

    /**
     * Saves the given card.
     *
     * @param card The card we want to save
     * @return A boolean value indicating whether the operation was successful
     */
    boolean saveCard(Card card);

    /**
     * Retrieves the card that is currently active in the study view
     *
     * @return The card that is currently active in the study view
     */
    Card getActiveCard();

    /**
     * Picks a random card from the deck that is currently selected
     *
     * @return A random card from the deck that is currently selected
     */
    Card nextCard();

    /**
     * Retrieves the card that is currently selected
     *
     * @return The card that is currently selected
     */
    Card getSelectedCard();

    /**
     * Sets the selected card
     *
     * @param card The card we want to set as selected
     */
    void setSelectedCard(Card card);

    /**
     * Retrieves list of cards that belong to the deck that is currently
     * selected
     *
     * @return list of cards that belong to the deck that is currently selected
     */
    List<Card> getForSelectedDeck();

    /**
     * Deletes the card that is currently selected
     *
     * @return A boolean value indicating whether the operation was successful
     */
    boolean deleteSelected();
}

package flashcardapp.service;

import flashcardapp.dao.CardDao;
import flashcardapp.model.Card;
import flashcardapp.model.Deck;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static flashcardapp.util.StringUtils.isNullOrWhitespace;
import static flashcardapp.util.StringUtils.isTooLong;

/**
 * Encapsulates Card-related business logic
 */
@Service
public class DefaultCardService implements CardService {

    private CardDao cardDao;
    private DeckService deckService;
    private SessionService sessionService;

    @Getter
    @Setter
    private Card selectedCard;

    @Getter
    private Card activeCard;

    /**
     * Used to inject dependencies.
     *
     * @param cardDao        Object implementing the CardDao-interface
     * @param deckService    Object implementing the DeckService-interface
     * @param sessionService Object implementing the SessionService-interface
     */
    @Autowired
    public DefaultCardService(
        @NonNull CardDao cardDao,
        @NonNull DeckService deckService,
        @NonNull SessionService sessionService
    ) {
        this.cardDao = cardDao;
        this.deckService = deckService;
        this.sessionService = sessionService;
    }

    /**
     * Retrieves list of cards belonging to the selected deck.
     *
     * @return list of cards that belong to the selected deck
     */
    @Override
    public List<Card> getForSelectedDeck() {
        Deck selected = deckService.getSelectedDeck();
        return selected == null
            ? new ArrayList<>()
            : cardDao.getByDeck(selected.getId());
    }

    /**
     * Deletes the card that is currently selected.
     *
     * @return a boolean value indicating whether the operation was successful.
     */
    @Override
    public boolean deleteSelected() {
        Card card = getSelectedCard();
        boolean success = card != null && cardDao.deleteCard(card);
        if (success) {
            setSelectedCard(null);
        }
        return success;
    }

    /**
     * Validates and saves the given card after assigning it to the correct
     * deck and user.
     *
     * @param card The card to be saved
     * @return a boolean value indicating whether the operation was successful.
     */
    @Override
    public boolean saveCard(Card card) {
        if (card == null) {
            return false;
        }
        card.setDeck(deckService.getSelectedDeck());
        card.setOwner(sessionService.getLoggedInUser());
        return validateCard(card) && cardDao.addOrUpdateCard(card);
    }

    /**
     * Retrieves a random card belonging to the selected deck and sets it as
     * the active deck.
     *
     * @return Random card from the selected deck, or null if no deck is
     * selected
     */
    @Override
    public Card nextCard() {
        Deck deck = deckService.getSelectedDeck();
        Card card = deck == null || deck.getId() == 0
            ? null
            : cardDao.getRandomFromDeck(deck.getId());
        activeCard = card;
        return card;
    }

    private boolean validateCard(Card card) {
        return card != null
            && card.getDeck() != null
            && card.getOwner() != null
            && !isNullOrWhitespace(card.getName())
            && !isTooLong(card.getName())
            && !isNullOrWhitespace(card.getQuestion())
            && !isTooLong(card.getQuestion())
            && !isNullOrWhitespace(card.getAnswer())
            && !isTooLong(card.getAnswer());
    }
}

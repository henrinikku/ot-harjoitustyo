package flashcardapp.service;

import flashcardapp.dao.DeckDao;
import flashcardapp.model.Deck;
import flashcardapp.model.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static flashcardapp.util.StringUtils.isNullOrWhitespace;

/**
 * Encapsulates Deck-related business logic.
 */
@Service
public class DefaultDeckService implements DeckService {

    private DeckDao deckDao;
    private SessionService sessionService;

    @Getter
    @Setter
    private Deck selectedDeck;

    /**
     * Used to inject dependencies.
     *
     * @param deckDao        Object implementing the DeckDao-interface
     * @param sessionService Object implementing the SessionService-interface
     */
    @Autowired
    public DefaultDeckService(
        @NonNull DeckDao deckDao,
        @NonNull SessionService sessionService
    ) {
        this.deckDao = deckDao;
        this.sessionService = sessionService;
    }

    /**
     * Validates and saves the given deck after setting the logged in user as
     * its owner.
     *
     * @param deck The Deck-object to be saved.
     * @return a boolean value indicating whether the operation was successful.
     */
    @Override
    public boolean addDeck(Deck deck) {
        User user = sessionService.getLoggedInUser();
        if (user == null || !validateName(deck.getName())) {
            return false;
        }
        deck.setOwner(user);
        return deckDao.addDeck(deck);
    }

    /**
     * Validates the given name. I.e. ensures it is not empty or in use.
     *
     * @param name The name to validate.
     * @return true if the name is valid, false if not.
     */
    @Override
    public boolean validateName(String name) {
        return !isNullOrWhitespace(name) && deckDao.getByName(name) == null;
    }

    /**
     * Retrieves all decks owned by the logged in user.
     *
     * @return list of decks owned by the logged in user.
     */
    @Override
    public List<Deck> getAll() {
        User user = sessionService.getLoggedInUser();
        return (user == null)
            ? new ArrayList<>()
            : deckDao.getByUserId(user.getId());
    }

    /**
     * Deletes the given deck.
     *
     * @param deck The deck to be deleted.
     * @return a boolean value indicating whether the operation was successful.
     */
    @Override
    public boolean deleteDeck(Deck deck) {
        return deck != null
            && deck.getId() > 0
            && deckDao.deleteById(deck.getId());
    }

    /**
     * Deletes the selected deck.
     *
     * @return a boolean value indicating whether the operation was successful.
     */
    @Override
    public boolean deleteSelected() {
        boolean deleted = deleteDeck(getSelectedDeck());
        if (deleted) {
            setSelectedDeck(null);
        }
        return deleted;
    }
}

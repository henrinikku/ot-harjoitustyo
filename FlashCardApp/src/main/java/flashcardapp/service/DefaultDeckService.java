package flashcardapp.service;

import flashcardapp.dao.DeckDao;
import flashcardapp.model.Card;
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

@Service
public class DefaultDeckService implements DeckService {

    private DeckDao deckDao;
    private SessionService sessionService;

    @Getter
    @Setter
    private Deck selectedDeck;

    @Autowired
    public DefaultDeckService(
        @NonNull DeckDao deckDao,
        @NonNull SessionService sessionService
    ) {
        this.deckDao = deckDao;
        this.sessionService = sessionService;
    }

    @Override
    public boolean addDeck(Deck deck) {
        User user = sessionService.getLoggedInUser();
        if (user == null || !validateName(deck.getName())) {
            return false;
        }
        deck.setOwner(user);
        return deckDao.addDeck(deck);
    }

    @Override
    public boolean validateName(String name) {
        return !isNullOrWhitespace(name) && deckDao.getByName(name) == null;
    }

    @Override
    public List<Deck> getAll() {
        User user = sessionService.getLoggedInUser();
        return (user == null)
            ? new ArrayList<>()
            : deckDao.getByUserId(user.getId());
    }

    @Override
    public boolean deleteDeck(Deck deck) {
        return deck != null
            && deck.getId() > 0
            && deckDao.deleteById(deck.getId());
    }

    @Override
    public boolean deleteSelected() {
        boolean deleted = deleteDeck(getSelectedDeck());
        if (deleted) {
            setSelectedDeck(null);
        }
        return deleted;
    }
}

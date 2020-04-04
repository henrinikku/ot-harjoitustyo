package flashcardapp.service;

import flashcardapp.dao.DeckDao;
import flashcardapp.model.Deck;
import flashcardapp.model.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static flashcardapp.util.StringUtils.isNullOrWhitespace;

@Service
public class DefaultDeckService implements DeckService {

    private DeckDao deckDao;
    private SessionService sessionService;

    @Autowired
    public DefaultDeckService(
        @NonNull DeckDao deckDao,
        @NonNull SessionService sessionService
    ) {
        this.deckDao = deckDao;
        this.sessionService = sessionService;
    }

    @Override
    @Transactional
    public boolean addDeck(Deck deck) {
        User user = sessionService.getLoggedInUser();
        if (user == null) {
            return false;
        }
        if (!validateName(deck.getName())) {
            return false;
        }
        deck.setOwner(user);
        return deckDao.addDeck(deck);
    }

    @Override
    @Transactional
    public boolean validateName(String name) {
        return !isNullOrWhitespace(name) && deckDao.getByName(name) == null;
    }

    @Override
    @Transactional
    public List<Deck> getAll() {
        User user = sessionService.getLoggedInUser();
        if (user == null) {
            return new ArrayList<>();
        }
        return deckDao.getByUserId(user.getId());
    }
}

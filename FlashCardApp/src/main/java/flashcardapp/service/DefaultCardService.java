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

@Service
public class DefaultCardService implements CardService {

    private CardDao cardDao;
    private DeckService deckService;
    private SessionService sessionService;

    @Getter
    @Setter
    private Card selectedCard;

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

    @Override
    public List<Card> getForSelectedDeck() {
        Deck selected = deckService.getSelectedDeck();
        return selected == null
            ? new ArrayList<>()
            : cardDao.getByDeck(selected.getId());
    }

    @Override
    public boolean deleteSelected() {
        Card card = getSelectedCard();
        boolean success = card != null && cardDao.deleteCard(card);
        if (success) {
            setSelectedCard(null);
        }
        return success;
    }

    @Override
    public boolean saveCard(Card card) {
        if (card == null) {
            return false;
        }
        card.setDeck(deckService.getSelectedDeck());
        card.setOwner(sessionService.getLoggedInUser());
        return validateCard(card) && cardDao.addOrUpdateCard(card);
    }

    private boolean validateCard(Card card) {
        return card != null
            && card.getDeck() != null
            && card.getOwner() != null
            && !isNullOrWhitespace(card.getName())
            && !isNullOrWhitespace(card.getQuestion())
            && !isNullOrWhitespace(card.getAnswer());
    }
}

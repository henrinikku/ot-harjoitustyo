package flashcardapp.dao;

import flashcardapp.model.Card;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FakeCardDao implements CardDao {

    private int prevId = 0;
    private HashMap<Long, Card> cards = new HashMap<>();

    @Override
    public boolean addOrUpdateCard(Card card) {
        if (card.getId() == 0) {
            prevId++;
            card.setId(prevId);
        }
        cards.put(card.getId(), card);
        return true;
    }

    @Override
    public boolean deleteCard(Card card) {
        cards.remove(card.getId());
        return true;
    }

    @Override
    public List<Card> getByDeck(long deckId) {
        return cards
            .values()
            .stream()
            .filter(c -> c.getDeck() != null && c.getDeck().getId() == deckId)
            .collect(Collectors.toList());
    }
}

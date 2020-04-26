package flashcardapp.dao;

import flashcardapp.model.Card;
import flashcardapp.model.Deck;

import java.util.List;

public interface CardDao extends Dao {

    boolean addOrUpdateCard(Card card);

    boolean deleteCard(Card card);

    List<Card> getByDeck(long deckId);
}

package flashcardapp.dao;

import flashcardapp.model.Deck;

import java.util.List;

public interface DeckDao extends Dao {
    boolean addDeck(Deck deck);

    Deck getByName(String name);

    List<Deck> getByUserId(long userId);
}

package flashcardapp.service;

import flashcardapp.model.Deck;

import java.util.List;

public interface DeckService {
    boolean addDeck(Deck deck);

    boolean validateName(String name);

    List<Deck> getAll();
}

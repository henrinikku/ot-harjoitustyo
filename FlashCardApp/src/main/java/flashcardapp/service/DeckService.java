package flashcardapp.service;

import flashcardapp.model.Card;
import flashcardapp.model.Deck;

import java.util.List;

public interface DeckService {
    boolean addDeck(Deck deck);

    boolean validateName(String name);

    List<Deck> getAll();

    boolean deleteDeck(Deck deck);

    boolean deleteSelected();

    Deck getSelectedDeck();

    void setSelectedDeck(Deck deck);
}

package flashcardapp.service;

import flashcardapp.model.Card;
import flashcardapp.model.Deck;

import java.util.List;

public interface CardService {

    boolean saveCard(Card card);

    Card getActiveCard();

    Card nextCard();

    Card getSelectedCard();

    void setSelectedCard(Card card);

    List<Card> getForSelectedDeck();

    boolean deleteSelected();
}

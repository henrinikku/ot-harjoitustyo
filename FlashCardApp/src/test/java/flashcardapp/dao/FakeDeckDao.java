package flashcardapp.dao;

import flashcardapp.model.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FakeDeckDao implements DeckDao {

    private ArrayList<Deck> decks = new ArrayList<>();

    @Override
    public boolean addDeck(Deck deck) {
        return decks.add(deck);
    }

    @Override
    public Deck getByName(String name) {
        return decks
            .stream()
            .filter(d -> d.getName().equals(name))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Deck> getByUserId(long userId) {
        return decks
            .stream()
            .filter(d -> d.getId() == userId)
            .collect(Collectors.toList());
    }
}

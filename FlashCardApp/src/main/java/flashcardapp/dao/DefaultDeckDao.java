package flashcardapp.dao;

import flashcardapp.model.Deck;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class DefaultDeckDao extends BaseDao implements DeckDao {

    @Override
    public boolean addDeck(Deck deck) {
        return persist(deck);
    }

    @Override
    public Deck getByName(String name) {
        Query query = createQuery("from Deck where name = ?0")
            .setParameter(0, name);
        return (Deck) getFirst(query);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Deck> getByUserId(long userId) {
        Query query =
            createQuery("from Deck where owner.id = ?0 order by created desc");
        return query.setParameter(0, userId).getResultList();
    }
}

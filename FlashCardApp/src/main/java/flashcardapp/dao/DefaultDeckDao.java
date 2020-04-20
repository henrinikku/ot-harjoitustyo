package flashcardapp.dao;

import flashcardapp.model.Deck;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DefaultDeckDao extends BaseDao<Deck> implements DeckDao {

    @Override
    @Transactional
    public boolean addDeck(Deck deck) {
        return persist(deck);
    }

    @Override
    @Transactional
    public Deck getByName(String name) {
        Query query =
            createQuery("from Deck where name = ?0").setParameter(0, name);
        return getFirst(query);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        return deleteByPk(id);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Deck> getByUserId(long userId) {
        Query query =
            createQuery("from Deck where owner.id = ?0 order by created desc");
        return query.setParameter(0, userId).getResultList();
    }
}

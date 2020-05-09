package flashcardapp.dao;

import flashcardapp.model.Deck;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Provides database operations related to the Deck model
 *
 * @see flashcardapp.model.Deck
 */
@Repository
public class DefaultDeckDao extends BaseDao<Deck> implements DeckDao {

    /**
     * Inserts the given deck to the database
     *
     * @param deck The deck we want to insert
     * @return A boolean value indicating whether the operation was successful
     */
    @Override
    @Transactional
    public boolean addDeck(Deck deck) {
        return persist(deck);
    }

    /**
     * Retrieves a deck by the given name.
     *
     * @param name The name of the deck want to retrieve
     * @return The deck the given name belongs to, or null.
     */
    @Override
    @Transactional
    public Deck getByName(String name) {
        Query query =
            createQuery("from Deck where name = ?0").setParameter(0, name);
        return getFirst(query);
    }

    /**
     * Deletes a deck by the given id.
     *
     * @param id The id of the deck we want to delete from the database
     * @return A boolean value indicating whether the operation was successful
     */
    @Override
    @Transactional
    public boolean deleteById(long id) {
        return deleteByPk(id);
    }

    /**
     * Retrieves a list of decks belonging to the given user
     *
     * @param userId The id of the user whose decks we want to retrieve.
     * @return A list of decks belonging to the given user.
     */
    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Deck> getByUserId(long userId) {
        Query query =
            createQuery("from Deck where owner.id = ?0 order by created desc");
        return query.setParameter(0, userId).getResultList();
    }
}

package flashcardapp.dao;

import flashcardapp.model.Card;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Hibernate based implementation of the CardDao interface
 *
 * @see flashcardapp.model.Card
 * @see flashcardapp.dao.CardDao
 */
@Repository
public class DefaultCardDao extends BaseDao<Card> implements CardDao {

    /**
     * Updates the card if it exists, inserts it if it does not.
     *
     * @param card The card we want to add or update
     * @return A boolean value indicating whether the operation was successful
     */
    @Override
    @Transactional
    public boolean addOrUpdateCard(Card card) {
        return card != null && addOrUpdate(card);
    }

    /**
     * Deletes the given card from the database
     *
     * @param card The card we want to delete
     * @return A boolean value indicating whether the operation was successful
     */
    @Override
    @Transactional
    public boolean deleteCard(Card card) {
        return deleteByPk(card.getId());
    }

    /**
     * Retrieves a random card from the given deck
     *
     * @param deckId The deck for which we want to retrieve a card
     * @return A card that belongs to the given deck, selected by random.
     */
    @Override
    @Transactional
    public Card getRandomFromDeck(long deckId) {
        Query query =
            createQuery("from Card where deck.id = ?0 order by rand()");
        return getFirst(query.setMaxResults(1).setParameter(0, deckId));
    }

    /**
     * Retrieves all cards that belong to the given deck.
     *
     * @param deckId The deck for which we want to retrieve the cards.
     * @return List of cards that belong to the given deck
     */
    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Card> getByDeck(long deckId) {
        return createQuery("from Card where deck.id = ?0 order by created desc")
            .setParameter(0, deckId)
            .getResultList();
    }
}

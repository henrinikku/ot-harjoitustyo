package flashcardapp.dao;

import flashcardapp.model.Card;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DefaultCardDao extends BaseDao<Card> implements CardDao {

    @Override
    @Transactional
    public boolean addOrUpdateCard(Card card) {
        return card != null && addOrUpdate(card);
    }

    @Override
    @Transactional
    public boolean deleteCard(Card card) {
        return deleteByPk(card.getId());
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Card> getByDeck(long deckId) {
        return createQuery("from Card where deck.id = ?0 order by created desc")
            .setParameter(0, deckId)
            .getResultList();
    }
}

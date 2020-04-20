package flashcardapp.dao;

import flashcardapp.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
public class DefaultUserDao extends BaseDao<User> implements UserDao {

    @Override
    @Transactional
    public boolean addUser(User user) {
        return persist(user);
    }

    @Override
    @Transactional
    public User getByUsername(String username) {
        Query query = createQuery("from User where username=?0");
        return getFirst(query.setParameter(0, username));
    }
}

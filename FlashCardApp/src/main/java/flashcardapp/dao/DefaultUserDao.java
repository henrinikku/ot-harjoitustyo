package flashcardapp.dao;

import flashcardapp.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class DefaultUserDao extends BaseDao implements UserDao {
    @Override
    public boolean addUser(User user) {
        return persist(user);
    }

    @Override
    public User getByUsername(String username) {
        Query query = createQuery("from User where username=?0")
            .setParameter(0, username);
        return (User) getFirst(query);
    }
}

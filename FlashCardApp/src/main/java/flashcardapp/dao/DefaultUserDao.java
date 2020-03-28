package flashcardapp.dao;

import flashcardapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DefaultUserDao extends BaseDao implements UserDao {
    // TODO: Logging

    @Override
    public boolean addUser(User user) {
        try {
            persist(user);
            return true;
        } catch (Exception e) {
            throw e;
            // return false;
        }
    }

    @Override
    public User getByUsername(String username) {
        List result = createQuery("from User where username=?")
            .setParameter(0, username)
            .getResultList();
        return result.isEmpty() ? null : (User) result.get(0);
    }
}

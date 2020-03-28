package flashcardapp.dao;

import flashcardapp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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

    @Override
    public List<User> getAll() {
        return createQuery("from User order by id").getResultList();
    }
}

package flashcardapp.dao;

import flashcardapp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class DefaultUserDao extends BaseDao implements UserDao {
    // TODO: Logging
    @Override
    public boolean addUser(User user) {
        try {
            persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getByUsername(String username) {
        Query query = createQuery("from User where username=?0")
            .setParameter(0, username);
        return (User) getFirst(query);
    }
}

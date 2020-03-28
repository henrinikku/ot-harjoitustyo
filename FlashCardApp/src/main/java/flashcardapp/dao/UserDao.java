package flashcardapp.dao;

import flashcardapp.model.User;

public interface UserDao extends Dao {
    boolean addUser(User user);
    User getByUsername(String username);
}

package flashcardapp.dao;

import flashcardapp.model.User;

import java.util.List;

public interface UserDao extends Dao {
    boolean addUser(User user);
    User getByUsername(String username);
    List<User> getAll();
}

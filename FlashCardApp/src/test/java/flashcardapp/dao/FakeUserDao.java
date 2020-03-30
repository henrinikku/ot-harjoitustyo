package flashcardapp.dao;

import flashcardapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class FakeUserDao implements UserDao {
    private List<User> users = new ArrayList<>();

    @Override
    public boolean addUser(User user) {
        return users.add(user);
    }

    @Override
    public User getByUsername(String username) {
        return users
            .stream()
            .filter(u -> u.getUsername().equals(username))
            .findFirst()
            .orElse(null);
    }
}

package flashcardapp.service;

import flashcardapp.model.User;

public interface UserService {
    boolean addUser(User user);

    boolean checkCredentials(String username, String password);

    boolean validateUsername(String username);

    User getByUsername(String username);
}

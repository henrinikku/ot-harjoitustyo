package flashcardapp.service;

import flashcardapp.model.User;
import org.springframework.stereotype.Service;

public interface UserService {
    boolean addUser(User user);
    boolean checkCredentials(User user);
    boolean validateUsername(String username);
}

package flashcardapp.service;

import flashcardapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    boolean checkCredentials(String username, String password);
    boolean validateUsername(String username);
    User getByUsername(String username);
}

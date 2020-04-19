package flashcardapp.service;

import flashcardapp.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class DefaultSessionService implements SessionService {
    @Getter
    @Setter
    private User loggedInUser;

    @Override
    public void logOut() {
        setLoggedInUser(null);
    }
}

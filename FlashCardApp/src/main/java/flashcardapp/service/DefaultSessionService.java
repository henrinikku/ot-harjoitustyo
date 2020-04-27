package flashcardapp.service;

import flashcardapp.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Manages information about the current session.
 */
@Service
public class DefaultSessionService implements SessionService {
    @Getter
    @Setter
    private User loggedInUser;

    /**
     * Ends the current session.
     */
    @Override
    public void logOut() {
        setLoggedInUser(null);
    }
}

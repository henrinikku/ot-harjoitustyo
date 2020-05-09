package flashcardapp.service;

import flashcardapp.model.User;

/**
 * Interface for session-related business logic
 */
public interface SessionService {
    User getLoggedInUser();

    void setLoggedInUser(User user);

    /**
     * Logs the user out
     */
    void logOut();
}

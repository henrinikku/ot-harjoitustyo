package flashcardapp.service;

import flashcardapp.model.User;

public interface SessionService {
    User getLoggedInUser();

    void setLoggedInUser(User user);

    void logOut();
}

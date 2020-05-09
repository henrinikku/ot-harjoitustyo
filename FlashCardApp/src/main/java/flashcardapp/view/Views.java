package flashcardapp.view;

import java.net.URL;

/**
 * Provides paths to all views used in the application.
 */
public class Views {

    /**
     * Retrieves path to the login view
     *
     * @return Path to the login view
     */
    public static URL loginView() {
        return getPathFor("fxml/Login.fxml");
    }

    /**
     * Retrieves path to the default view for logged in user
     *
     * @return Path to the default view for logged in user
     */
    public static URL indexView() {
        return getPathFor("fxml/Index.fxml");
    }

    /**
     * Retrieves path to the form for creating new decks
     *
     * @return Path to the form for creating new decks
     */
    public static URL newDeckView() {
        return getPathFor("fxml/NewDeck.fxml");
    }

    /**
     * Retrieves path to the single deck view
     *
     * @return Path to the single deck view
     */
    public static URL singleDeckView() {
        return getPathFor("fxml/Deck.fxml");
    }

    /**
     * Retrieves path to the single card view
     *
     * @return Path to the single card view
     */
    public static URL singleCardView() {
        return getPathFor("fxml/Card.fxml");
    }

    /**
     * Retrieves path to the view for studying decks
     *
     * @return Path to the view for studying decks
     */
    public static URL studyView() {
        return getPathFor("fxml/Study.fxml");
    }

    private static URL getPathFor(String path) {
        return Views.class.getClassLoader().getResource(path);
    }
}

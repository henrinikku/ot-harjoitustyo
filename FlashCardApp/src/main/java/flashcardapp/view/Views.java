package flashcardapp.view;

import java.net.URL;

public class Views {

    public static URL loginView() {
        return getPathFor("fxml/Login.fxml");
    }

    public static URL indexView() {
        return getPathFor("fxml/Index.fxml");
    }

    public static URL newDeckView() {
        return getPathFor("fxml/NewDeck.fxml");
    }

    public static URL singleDeckView() {
        return getPathFor("fxml/Deck.fxml");
    }

    private static URL getPathFor(String path) {
        return Views.class.getClassLoader().getResource(path);
    }
}

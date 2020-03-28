package flashcardapp.helper;

import java.net.URL;

public class PageManager {

    public static URL mainPage() {
        return getPathFor("fxml/Login.fxml");
    }

    private static URL getPathFor(String path) {
        return PageManager.class.getClassLoader().getResource(path);
    }
}

package flashcardapp.helper;

import java.net.URL;

public class PageManager {

    public static URL loginView() {
        return getPathFor("fxml/Login.fxml");
    }

    private static URL getPathFor(String path) {
        return PageManager.class.getClassLoader().getResource(path);
    }
}

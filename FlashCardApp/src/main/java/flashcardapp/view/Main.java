package flashcardapp.view;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Provides an entry point for the application
 */
@SpringBootApplication
public class Main {
    /**
     * An entry point for the application
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Application.launch(FlashCardUi.class, args);
    }
}

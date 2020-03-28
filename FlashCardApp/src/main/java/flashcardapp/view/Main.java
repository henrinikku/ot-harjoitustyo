package flashcardapp.view;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("flashcardapp.configuration")
public class Main {
    public static void main(String[] args) {
        Application.launch(FlashCardUi.class, args);
    }
}

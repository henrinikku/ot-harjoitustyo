package flashcardapp.view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

import flashcardapp.helper.PageManager;

public class FlashCardUi extends Application {
    @Override
    public void init() {
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(PageManager.mainPage());
        stage.setTitle("FlashCardApp");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() {
    }

    public static void main(String[] args) {
        launch(args);
    }
}

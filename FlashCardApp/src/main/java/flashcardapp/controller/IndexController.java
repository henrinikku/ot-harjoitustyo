package flashcardapp.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    // Declared as public to omit @FXML annotation
    public Label lblWelcome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

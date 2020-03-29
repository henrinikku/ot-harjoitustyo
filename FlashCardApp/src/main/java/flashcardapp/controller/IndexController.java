package flashcardapp.controller;

import flashcardapp.model.User;
import flashcardapp.service.SessionService;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    @Autowired
    private SessionService sessionService;

    // Declared as public to omit @FXML annotation
    public Label lblWelcome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = sessionService.getLoggedInUser();
        if (user != null) {
            lblWelcome.setText("Welcome, " + user.getUsername() + "!");
        }
    }
}

package flashcardapp.controller;

import flashcardapp.model.User;
import flashcardapp.service.DeckService;
import flashcardapp.service.SessionService;
import flashcardapp.view.FlashCardUi;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    public Label lblWelcome;
    public Label lblDecks;
    public Button btnNewDeck;

    @Autowired
    private SessionService sessionService;
    @Autowired
    private DeckService deckService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = sessionService.getLoggedInUser();
        if (user == null) {
            FlashCardUi.displayLoginView();
            return;
        }
        lblWelcome.setText("Welcome, " + user.getUsername() + "!");
        lblDecks.setText(
            "You have created " + deckService.getAll().size() + " decks");
    }

    public void onNewDeckClick(ActionEvent actionEvent) {
        FlashCardUi.displayNewDeckView();
    }
}

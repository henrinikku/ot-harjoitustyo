package flashcardapp.controller;

import flashcardapp.model.Deck;
import flashcardapp.model.User;
import flashcardapp.service.DeckService;
import flashcardapp.service.SessionService;
import flashcardapp.view.FlashCardUi;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    public Label lblWelcome;
    public Label lblDecks;
    public Button btnNewDeck;
    public Button btnLogOut;
    public ListView<Deck> lvDecks;

    @Autowired
    private SessionService sessionService;
    @Autowired
    private DeckService deckService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkUser();
        displayDecks();
    }

    private void checkUser() {
        User user = sessionService.getLoggedInUser();
        if (user == null) {
            FlashCardUi.displayLoginView();
            return;
        }
        lblWelcome.setText("Welcome, " + user.getUsername() + "!");
    }

    private void displayDecks() {
        List<Deck> decks = deckService.getAll();
        if (decks.isEmpty()) {
            lblDecks.setText("No decks yet :(");
            return;
        }
        setupDeckList(decks);
    }

    private void setupDeckList(List<Deck> decks) {
        lvDecks.getItems().clear();
        lvDecks.getItems().addAll(decks);
        lvDecks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void onDeckSelected(MouseEvent mouseEvent) {
        Deck selected = lvDecks.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        deckService.setSelectedDeck(selected);
        FlashCardUi.displaySingleDeckView();
    }

    public void onNewDeckClick(ActionEvent actionEvent) {
        FlashCardUi.displayNewDeckView();
    }

    public void onLogOutClick(ActionEvent actionEvent) {
        sessionService.logOut();
        FlashCardUi.displayLoginView();
    }
}

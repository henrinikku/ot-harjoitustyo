package flashcardapp.controller;

import flashcardapp.model.Deck;
import flashcardapp.model.User;
import flashcardapp.service.DeckService;
import flashcardapp.service.SessionService;
import flashcardapp.view.FlashCardUi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Responsible for the UI logic of the index view
 */
@Component
public class IndexController implements Initializable {

    @FXML
    private Label lblWelcome;
    @FXML
    private Label lblDecks;
    @FXML
    private Button btnNewDeck;
    @FXML
    private Button btnLogOut;
    @FXML
    private ListView<Deck> lvDecks;

    @Autowired
    private SessionService sessionService;
    @Autowired
    private DeckService deckService;

    /**
     * Sets up the view.
     * @param url The url from which the view was loaded. Not used.
     * @param resourceBundle Resource Bundle. Not used.
     */
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

    /**
     * Fires when an item in the deck list is clicked.
     *
     * @param mouseEvent Caller event
     */
    public void onDeckSelected(MouseEvent mouseEvent) {
        Deck selected = lvDecks.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        deckService.setSelectedDeck(selected);
        FlashCardUi.displaySingleDeckView();
    }

    /**
     * Handles click of the 'new deck' button
     *
     * @param actionEvent Caller event
     */
    public void onNewDeckClick(ActionEvent actionEvent) {
        FlashCardUi.displayNewDeckView();
    }

    /**
     * Handles click of the 'log out' button
     *
     * @param actionEvent Caller event
     */
    public void onLogOutClick(ActionEvent actionEvent) {
        sessionService.logOut();
        FlashCardUi.displayLoginView();
    }
}

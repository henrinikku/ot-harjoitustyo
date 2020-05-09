package flashcardapp.controller;

import flashcardapp.model.Deck;
import flashcardapp.service.DeckService;
import flashcardapp.view.FlashCardUi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Responsible for the UI logic of the deck creation view
 */
@Component
public class NewDeckController implements Initializable {

    @FXML
    private Label lblError;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDescription;

    @Autowired
    private DeckService deckService;

    /**
     * Sets up the view.
     * @param url The url from which the view was loaded. Not used.
     * @param resourceBundle Resource Bundle. Not used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Handles click of the 'create' button
     *
     * @param actionEvent Caller event
     */
    public void onCreateClick(ActionEvent actionEvent) {
        Deck deck = new Deck(txtName.getText(), txtDescription.getText());
        if (deckService.addDeck(deck)) {
            FlashCardUi.displayIndexView();
        } else {
            lblError.setText("Invalid name");
        }
    }

    /**
     * Handles click of the 'cancel' button
     *
     * @param actionEvent Caller event
     */
    public void onCancelClick(ActionEvent actionEvent) {
        FlashCardUi.displayIndexView();
    }
}

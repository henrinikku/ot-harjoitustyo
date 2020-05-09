package flashcardapp.controller;

import flashcardapp.model.Card;
import flashcardapp.service.CardService;
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
 * Responsible for the UI logic of the single card view
 */
@Component
public class CardController implements Initializable {

    @FXML
    private Label lblHeader;
    @FXML
    private Label lblError;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtQuestion;
    @FXML
    private TextArea txtAnswer;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;

    @Autowired
    private CardService cardService;

    /**
     * Sets up the view.
     * @param url The url from which the view was loaded. Not used.
     * @param resourceBundle Resource Bundle. Not used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Card card = cardService.getSelectedCard();
        boolean newCard = (card == null);
        if (!newCard) {
            fillFields(card);
        }
        setTexts(newCard);
        btnDelete.setDisable(newCard);
    }

    private void setTexts(boolean newCard) {
        btnSave.setText(newCard ? "Create" : "Save");
        lblHeader.setText(newCard ? "New card" : "Edit card");
    }

    private void fillFields(Card card) {
        txtName.setText(card.getName());
        txtQuestion.setText(card.getQuestion());
        txtAnswer.setText(card.getAnswer());
    }

    private void displayError(String message) {
        lblError.setVisible(true);
        lblError.setText(message);
    }

    /**
     * Handles click of the 'Save' button
     *
     * @param actionEvent Caller event
     */
    public void onSaveClick(ActionEvent actionEvent) {
        Card card = cardService.getSelectedCard();
        card = (card != null) ? card : new Card();
        card.setName(txtName.getText());
        card.setQuestion(txtQuestion.getText());
        card.setAnswer(txtAnswer.getText());

        if (cardService.saveCard(card)) {
            FlashCardUi.displaySingleDeckView();
        } else {
            displayError("All fields are required. Name must be unique");
        }
    }

    /**
     * Handles click of the 'Cancel' button
     *
     * @param actionEvent Caller event
     */
    public void onCancelClick(ActionEvent actionEvent) {
        FlashCardUi.displaySingleDeckView();
    }

    /**
     * Handles click of the 'delete' button
     *
     * @param actionEvent Caller event
     */
    public void onDeleteClick(ActionEvent actionEvent) {
        if (cardService.deleteSelected()) {
            FlashCardUi.displaySingleDeckView();
        } else {
            lblError.setVisible(true);
            displayError("Could not delete card");
        }
    }
}

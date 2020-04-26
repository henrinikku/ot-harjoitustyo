package flashcardapp.controller;

import flashcardapp.model.Card;
import flashcardapp.model.Deck;
import flashcardapp.service.CardService;
import flashcardapp.service.DeckService;
import flashcardapp.service.SessionService;
import flashcardapp.view.FlashCardUi;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CardController implements Initializable {

    public Label lblHeader;
    public Label lblError;
    public TextField txtName;
    public TextField txtQuestion;
    public TextArea txtAnswer;
    public Button btnSave;
    public Button btnCancel;
    public Button btnDelete;

    @Autowired
    private CardService cardService;

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

    public void onCancelClick(ActionEvent actionEvent) {
        FlashCardUi.displaySingleDeckView();
    }

    public void onDeleteClick(ActionEvent actionEvent) {
        if (cardService.deleteSelected()) {
            FlashCardUi.displaySingleDeckView();
        } else {
            lblError.setVisible(true);
            displayError("Could not delete card");
        }
    }
}

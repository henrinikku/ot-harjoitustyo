package flashcardapp.controller;

import flashcardapp.model.Card;
import flashcardapp.service.CardService;
import flashcardapp.view.FlashCardUi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Responsible for the UI logic of the study view
 */
@Component
public class StudyController implements Initializable {

    @FXML
    private Label lblHeader;
    @FXML
    private Label lblQuestion;
    @FXML
    private Label lblAnswer;
    @FXML
    private Button btnNextCard;
    @FXML
    private Button btnAnswer;
    @FXML
    private Button btnCancel;

    @Autowired
    private CardService cardService;

    /**
     * Sets up the view.
     * @param url The url from which the view was loaded. Not used.
     * @param resourceBundle Resource Bundle. Not used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayNext();
    }

    private void displayNext() {
        Card card = cardService.nextCard();
        if (card == null) {
            return;
        }
        lblHeader.setText(card.getName());
        lblQuestion.setText(card.getQuestion());
        lblAnswer.setText("Press the button below to display answer");
    }

    /**
     * Handles click of the 'next card' button
     *
     * @param actionEvent Caller event
     */
    public void onNextCardClick(ActionEvent actionEvent) {
        displayNext();
    }

    /**
     * Handles click of the 'display answer' button
     *
     * @param actionEvent Caller event
     */
    public void onAnswerClick(ActionEvent actionEvent) {
        Card card = cardService.getActiveCard();
        if (card != null) {
            lblAnswer.setText(card.getAnswer());
        }
    }

    /**
     * Handles click of the 'cancel' button
     *
     * @param actionEvent Caller event
     */
    public void onCancelClick(ActionEvent actionEvent) {
        FlashCardUi.displaySingleDeckView();
    }
}

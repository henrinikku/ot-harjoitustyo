package flashcardapp.controller;

import flashcardapp.model.Card;
import flashcardapp.service.CardService;
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
public class StudyController implements Initializable {

    /**
     *
     */
    public Label lblHeader;
    public Label lblQuestion;
    public Label lblAnswer;
    public Button btnNextCard;
    public Button btnAnswer;
    public Button btnCancel;

    @Autowired
    private CardService cardService;

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

    public void onNextCardClick(ActionEvent actionEvent) {
        displayNext();
    }

    public void onAnswerClick(ActionEvent actionEvent) {
        Card card = cardService.getActiveCard();
        if (card != null) {
            lblAnswer.setText(card.getAnswer());
        }
    }

    public void onCancelClick(ActionEvent actionEvent) {
        FlashCardUi.displaySingleDeckView();
    }
}

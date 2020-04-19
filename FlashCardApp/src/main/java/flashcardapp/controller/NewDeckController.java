package flashcardapp.controller;

import flashcardapp.model.Deck;
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
public class NewDeckController implements Initializable {

    public Label lblError;
    public Button btnCreate;
    public Button btnCancel;
    public TextField txtName;
    public TextArea txtDescription;

    @Autowired
    private DeckService deckService;
    @Autowired
    private SessionService sessionService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onCreateClick(ActionEvent actionEvent) {
        Deck deck = new Deck(txtName.getText(), txtDescription.getText());
        if (deckService.addDeck(deck)) {
            FlashCardUi.displayIndexView();
        } else {
            lblError.setText("Invalid name");
        }
    }

    public void onCancelClick(ActionEvent actionEvent) {
        FlashCardUi.displayIndexView();
    }
}

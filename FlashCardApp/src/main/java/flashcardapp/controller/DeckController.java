package flashcardapp.controller;

import flashcardapp.model.Deck;
import flashcardapp.service.DeckService;
import flashcardapp.view.FlashCardUi;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static flashcardapp.util.StringUtils.*;

@Component
public class DeckController implements Initializable {

    public Label lblHeader;
    public Label lblDescription;
    public Button btnCancel;

    @Autowired
    private DeckService deckService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Deck deck = deckService.getSelectedDeck();
        if (deck == null) {
            FlashCardUi.displayIndexView();
            return;
        }
        lblHeader.setText(deck.getName());
        if (!isNullOrWhitespace(deck.getDescription())) {
            lblDescription.setText(deck.getDescription());
        }
    }

    public void onCancelClick(ActionEvent actionEvent) {
        deckService.setSelectedDeck(null);
        FlashCardUi.displayIndexView();
    }
}

package flashcardapp.controller;

import flashcardapp.model.Card;
import flashcardapp.model.Deck;
import flashcardapp.service.CardService;
import flashcardapp.service.DeckService;
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
import java.util.List;
import java.util.ResourceBundle;

import static flashcardapp.util.StringUtils.isNullOrWhitespace;

@Component
public class DeckController implements Initializable {

    public Label lblHeader;
    public Label lblCards;
    public Label lblDescription;
    public Button btnStudy;
    public Button btnNewCard;
    public Button btnCancel;
    public Button btnDelete;
    public ListView<Card> lvCards;

    @Autowired
    private CardService cardService;
    @Autowired
    private DeckService deckService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Deck deck = deckService.getSelectedDeck();
        if (deck == null) {
            return;
        }
        displayDeckInfo(deck);
        displayCards();
    }

    private void displayDeckInfo(Deck deck) {
        lblHeader.setText(deck.getName());
        if (!isNullOrWhitespace(deck.getDescription())) {
            lblDescription.setText(deck.getDescription());
        }
    }

    private void displayCards() {
        List<Card> cards = cardService.getForSelectedDeck();
        lvCards.getItems().clear();
        lvCards.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        btnStudy.setDisable(cards == null || cards.isEmpty());
        if (cards != null) {
            lvCards.getItems().addAll(cards);
        }
    }

    public void onCancelClick(ActionEvent actionEvent) {
        deckService.setSelectedDeck(null);
        FlashCardUi.displayIndexView();
    }

    public void onDeleteClick(ActionEvent actionEvent) {
        deckService.deleteSelected();
        FlashCardUi.displayIndexView();
    }

    public void onNewCardClick(ActionEvent actionEvent) {
        cardService.setSelectedCard(null);
        FlashCardUi.displaySingleCardView();
    }

    public void onCardClicked(MouseEvent mouseEvent) {
        Card selected = lvCards.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        cardService.setSelectedCard(selected);
        FlashCardUi.displaySingleCardView();
    }

    public void onStudyClick(ActionEvent actionEvent) {
        if (deckService.getSelectedDeck() != null) {
            FlashCardUi.displayStudyView();
        }
    }
}

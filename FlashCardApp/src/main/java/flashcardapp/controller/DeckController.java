package flashcardapp.controller;

import flashcardapp.model.Card;
import flashcardapp.model.Deck;
import flashcardapp.service.CardService;
import flashcardapp.service.DeckService;
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

import static flashcardapp.util.StringUtils.isNullOrWhitespace;

/**
 * Responsible for the UI logic of the single deck view
 */
@Component
public class DeckController implements Initializable {

    @FXML
    private Label lblHeader;
    @FXML
    private Label lblCards;
    @FXML
    private Label lblDescription;
    @FXML
    private Button btnStudy;
    @FXML
    private Button btnNewCard;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    @FXML
    private ListView<Card> lvCards;

    @Autowired
    private CardService cardService;
    @Autowired
    private DeckService deckService;

    /**
     * Sets up the view.
     * @param url The url from which the view was loaded. Not used.
     * @param resourceBundle Resource Bundle. Not used.
     */
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

    /**
     * Handles click of the 'cancel' button
     *
     * @param actionEvent Caller event
     */
    public void onCancelClick(ActionEvent actionEvent) {
        deckService.setSelectedDeck(null);
        FlashCardUi.displayIndexView();
    }

    /**
     * Handles click of the 'delete' button
     *
     * @param actionEvent Caller event
     */
    public void onDeleteClick(ActionEvent actionEvent) {
        deckService.deleteSelected();
        FlashCardUi.displayIndexView();
    }

    /**
     * Handles click of the 'new card' button
     *
     * @param actionEvent Caller event
     */
    public void onNewCardClick(ActionEvent actionEvent) {
        cardService.setSelectedCard(null);
        FlashCardUi.displaySingleCardView();
    }

    /**
     * Fires when an item in the card list is clicked.
     *
     * @param mouseEvent Caller event
     */
    public void onCardClicked(MouseEvent mouseEvent) {
        Card selected = lvCards.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        cardService.setSelectedCard(selected);
        FlashCardUi.displaySingleCardView();
    }

    /**
     * Handles click of the 'study deck' button
     *
     * @param actionEvent Caller event
     */
    public void onStudyClick(ActionEvent actionEvent) {
        if (deckService.getSelectedDeck() != null) {
            FlashCardUi.displayStudyView();
        }
    }
}

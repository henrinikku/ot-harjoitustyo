package flashcardapp.controller;

import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainController implements Initializable {

    public MainController() {
    }

    @FXML
    private Label display;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        display.setText("TESTIIIIIII");
    }
}

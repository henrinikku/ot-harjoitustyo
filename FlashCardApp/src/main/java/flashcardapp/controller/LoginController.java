package flashcardapp.controller;

import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static flashcardapp.helper.StringUtils.isNullOrWhitespace;

@Component
public class LoginController implements Initializable {
    // For now, declare element references as public to omit the @FXML annotation
    public Label header;
    public Label lblLogInError;
    public TextField txtLogInUsername;
    public PasswordField txtPassword;
    public Button btnLogIn;
    public Label lblRegisterError;
    public TextField txtRegisterUsername;
    public PasswordField txtRegisterPassword;
    public PasswordField txtRegisterPasswordAgain;
    public Button btnRegister;

    @Autowired
    public LoginController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onLoginClicked(MouseEvent mouseEvent) {
        if (isNullOrWhitespace(txtLogInUsername.getText())) {
            lblLogInError.setText("Username is empty");
            return;
        }
        if (isNullOrWhitespace(txtPassword.getText())) {
            lblLogInError.setText("Password is empty");
            return;
        }
        lblLogInError.setText("");
    }

    public void onRegisterClicked(MouseEvent mouseEvent) {
        if (isNullOrWhitespace(txtRegisterUsername.getText())) {
            lblRegisterError.setText("Username is empty");
            return;
        }
        String password = txtRegisterPassword.getText();
        String passwordAgain = txtRegisterPasswordAgain.getText();
        if (isNullOrWhitespace(password) || isNullOrWhitespace(passwordAgain)) {
            lblRegisterError.setText("Password is empty");
            return;
        }
        if (password.length() < 8) {
            lblRegisterError.setText("Password is too short");
            return;
        }
        if (!password.equals(passwordAgain)) {
            lblRegisterError.setText("Passwords do not match");
            return;
        }
        lblRegisterError.setText("");
    }
}

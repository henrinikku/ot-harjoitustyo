package flashcardapp.controller;

import flashcardapp.service.UserService;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static flashcardapp.helper.StringUtils.isNullOrWhitespace;

@Component
public class LoginController implements Initializable {

    @Autowired
    private UserService userService;

    // For now, declare element references as public to omit the @FXML annotation
    public Label lblLogInError;
    public TextField txtLogInUsername;
    public PasswordField txtPassword;
    public Button btnLogIn;
    public Label lblRegisterSuccess;
    public Label lblRegisterError;
    public TextField txtRegisterUsername;
    public PasswordField txtRegisterPassword;
    public PasswordField txtRegisterPasswordAgain;
    public Button btnRegister;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Make the ui behave like a form
        txtLogInUsername.addEventHandler(
            KeyEvent.KEY_PRESSED, this::submitLogin);
        txtPassword.addEventHandler(
            KeyEvent.KEY_PRESSED, this::submitLogin);
        txtRegisterUsername.addEventHandler(
            KeyEvent.KEY_PRESSED, this::submitRegister);
        txtRegisterPassword.addEventHandler(
            KeyEvent.KEY_PRESSED, this::submitRegister);
        txtRegisterPasswordAgain.addEventHandler(
            KeyEvent.KEY_PRESSED, this::submitRegister);
    }

    private void submitLogin(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            btnLogIn.fire();
        }
    }

    private void submitRegister(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            btnRegister.fire();
        }
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
        String username = txtRegisterUsername.getText();
        String password = txtRegisterPassword.getText();
        String passwordAgain = txtRegisterPasswordAgain.getText();
        if (isNullOrWhitespace(username)) {
            lblRegisterError.setText("Username is empty");
            return;
        }
        if (isNullOrWhitespace(password) || isNullOrWhitespace(passwordAgain)) {
            displayRegisterError("Password is empty");
            return;
        }
        if (password.length() < 8) {
            displayRegisterError("Password is too short");
            return;
        }
        if (!password.equals(passwordAgain)) {
            displayRegisterError("Passwords do not match");
            return;
        }
        displayRegisterSuccess("Account created!");
    }

    private void displayRegisterSuccess(String message) {
        lblRegisterError.setText("");
        lblRegisterSuccess.setText(message);
    }

    private void displayRegisterError(String message) {
        lblRegisterSuccess.setText("");
        lblRegisterError.setText(message);
    }
}

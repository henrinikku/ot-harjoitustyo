package flashcardapp.controller;

import flashcardapp.model.User;
import flashcardapp.service.SessionService;
import flashcardapp.service.UserService;
import flashcardapp.view.FlashCardUi;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static flashcardapp.util.StringUtils.isNullOrWhitespace;

@Component
public class LoginController implements Initializable {

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

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Make the ui behave like a form
        txtLogInUsername.addEventHandler(
            KeyEvent.KEY_PRESSED, this::submitLogin
        );
        txtPassword.addEventHandler(
            KeyEvent.KEY_PRESSED, this::submitLogin
        );
        txtRegisterUsername.addEventHandler(
            KeyEvent.KEY_PRESSED, this::submitRegister
        );
        txtRegisterPassword.addEventHandler(
            KeyEvent.KEY_PRESSED, this::submitRegister
        );
        txtRegisterPasswordAgain.addEventHandler(
            KeyEvent.KEY_PRESSED, this::submitRegister
        );
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

    public void onLoginClicked(ActionEvent mouseEvent) {
        String username = txtLogInUsername.getText();
        String password = txtPassword.getText();
        if (!userService.checkCredentials(username, password)) {
            lblLogInError.setText("Wrong credentials");
            return;
        }

        User user = userService.getByUsername(username);
        sessionService.setLoggedInUser(user);
        FlashCardUi.displayIndexView();
    }

    public void onRegisterClicked(ActionEvent mouseEvent) {
        String username = txtRegisterUsername.getText();
        String password = txtRegisterPassword.getText();
        String passwordAgain = txtRegisterPasswordAgain.getText();
        if (!validateUsername(username)
            || !validatePassword(password, passwordAgain)
        ) {
            return;
        }

        User user = new User(username, password);
        userService.addUser(user);
        clearRegisterForm();
        displayRegisterSuccess("Account created!");
    }

    private boolean validateUsername(String username) {
        if (isNullOrWhitespace(username)) {
            displayRegisterError("Username is empty");
            return false;
        }
        if (!userService.validateUsername(username)) {
            displayRegisterError("Username is taken");
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password, String passwordAgain) {
        if (isNullOrWhitespace(password) || isNullOrWhitespace(passwordAgain)) {
            displayRegisterError("Password is empty");
            return false;
        }
        if (password.length() < 8) {
            displayRegisterError("Password is too short");
            return false;
        }
        if (!password.equals(passwordAgain)) {
            displayRegisterError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void displayRegisterSuccess(String message) {
        lblRegisterError.setText("");
        lblRegisterSuccess.setText(message);
    }

    private void displayRegisterError(String message) {
        lblRegisterSuccess.setText("");
        lblRegisterError.setText(message);
    }

    private void clearRegisterForm() {
        txtRegisterUsername.clear();
        txtRegisterPassword.clear();
        txtRegisterPasswordAgain.clear();
    }
}

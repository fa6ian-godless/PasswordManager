package org.passwordmanager.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.passwordmanager.util.SecurityUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;

    private final String masterFile = "master.dat";
    private final String userFile = "user.dat";

    @FXML
    private void initialize() {
        if (new File(masterFile).exists()) {
            loginButton.setDisable(false);
            registerButton.setDisable(true);
        } else {
            loginButton.setDisable(true);
            registerButton.setDisable(false);
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        try {
            String storedUser = new String(Files.readAllBytes(Paths.get(userFile))).trim();
            String storedHash = new String(Files.readAllBytes(Paths.get(masterFile))).trim();

            String inputUser = usernameField.getText().trim();
            String inputHash = SecurityUtil.hashPassword(passwordField.getText());

            if (inputUser.isEmpty()) {
                showAlert("Enter the user's name");
                return;
            }

            if (!storedUser.equals(inputUser)) {
                showAlert("The username doesn't match");
            }

            if (storedHash.equals(inputHash)) {
                openMainWindow();
            } else {
                showAlert("Invalid password");
            }
        } catch (IOException ioException) {
            showAlert("Couldn't read the master file");
        }
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        try {
            String username = usernameField.getText().trim();
            String password = passwordField.getText();

            if (username.isEmpty()) {
                showAlert("Enter the user's name");
                return;
            }

            if (username.contains(" ")) {
                showAlert("The user name must not contain spaces.");
                return;
            }

            if (password.isEmpty()) {
                showAlert("Enter the password for registration");
                return;
            }

            String hash = SecurityUtil.hashPassword(password);
            Files.write(Paths.get(masterFile), hash.getBytes());
            Files.write(Paths.get(userFile), username.getBytes());

            showAlert("Registration is completed. You can enter now.");

            loginButton.setDisable(false);
            registerButton.setDisable(true);
        } catch (IOException ioException) {
            showAlert("Couldn't save the master password");
        }
    }


    private void openMainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();

            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException ioException) {
            showAlert("Couldn't open the main window");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }


}

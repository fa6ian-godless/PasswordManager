package org.passwordmanager.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.SecureRandom;

public class AddDataController {

    @FXML private TextField usingField;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private final SecureRandom random = new SecureRandom();

    @FXML
    private void handleGenerateLogin() {
        loginField.setText(generateRandomString(8));
    }

    @FXML
    private void handleGeneratePassword() {
        passwordField.setText(generateRandomString(16));
    }

    @FXML
    private void handleAdd() {
        String site = usingField.getText().trim();
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        if (site.isEmpty() || login.isEmpty() || password.isEmpty()) {
            showAlert();
            return;
        }

        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) usingField.getScene().getWindow();
        stage.close();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("All fields must be filled in!");
        alert.showAndWait();
    }

    private String generateRandomString(int count_sumbols) {
        StringBuilder stringBuilder = new StringBuilder(count_sumbols);

        for (int i = 0; i < count_sumbols; i++) {
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }

        return stringBuilder.toString();
    }

}

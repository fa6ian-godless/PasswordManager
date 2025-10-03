package org.passwordmanager.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.SecureRandom;

public class AddDataController {

    @FXML private TextField usingField;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private final SecureRandom random = new SecureRandom();

    private MainController mainController;

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    @FXML
    private void handleGenerateLogin(MouseEvent event) {
        loginField.setText(generateRandomString(8));
    }

    @FXML
    private void handleGeneratePassword(MouseEvent event) {
        passwordField.setText(generateRandomString(16));
    }

    @FXML
    private void handleAdd() throws IOException {
        String using = usingField.getText().trim();
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        if (!(using.isEmpty() || login.isEmpty() || password.isEmpty())) {
            mainController.addPasswordCard(using, login, password);
        } else {
            showAlert();
            return;
        }

        ((Stage) usingField.getScene().getWindow()).close();
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

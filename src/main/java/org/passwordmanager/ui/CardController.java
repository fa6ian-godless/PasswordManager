package org.passwordmanager.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CardController {
    @FXML private Label usingLabel;
    @FXML private Button showBtn;

    public void setData(String using, String login, String password) {
        usingLabel.setText(using);

        showBtn.setOnAction(event -> {
            System.out.println("Login: " + login);
            System.out.println("Password: " + password);
        });
    }
}

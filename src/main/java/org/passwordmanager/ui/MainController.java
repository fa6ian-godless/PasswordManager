package org.passwordmanager.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.TableView;

public class MainController {

    @FXML private TableView passwordTable;
    @FXML private TableColumn usingColumn;
    @FXML private TableColumn loginColumn;
    @FXML private TableColumn passwordColumn;

    @FXML private Button addButton;
    @FXML private Button settingsButton;

    @FXML
    private void handleAddPassword() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add_password.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.initOwner(addButton.getScene().getWindow());
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.WINDOW_MODAL);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void handleSettings() {

    }

}

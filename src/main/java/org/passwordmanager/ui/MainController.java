package org.passwordmanager.ui;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainController {


    @FXML private TableView passwordTable;
    @FXML private TableColumn usingColumn;
    @FXML private TableColumn loginColumn;
    @FXML private TableColumn passwordColumn;

    @FXML private Button addButton;
    @FXML private Button settingsButton;

    @FXML
    private void handleAddPassword(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add_data.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Color.TRANSPARENT);
            Stage stage = new Stage();

            stage.setScene(scene);

            Stage owner = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.initOwner(owner);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.WINDOW_MODAL);

            stage.centerOnScreen();

            stage.setOnShown(windowEvent -> {
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), scene.getRoot());

                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(1);
                fadeTransition.play();
            });

            Rectangle overlay = new Rectangle(owner.getWidth(), owner.getHeight(), Color.rgb(0, 0, 0,0.3));

            overlay.setMouseTransparent(true);

            ((Pane) owner.getScene().getRoot()).getChildren().add(overlay);

            stage.setOnHidden(windowEvent -> {
                ((Pane) owner.getScene().getRoot()).getChildren().remove(overlay);
            });

            stage.showAndWait();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void handleSettings() {

    }

}

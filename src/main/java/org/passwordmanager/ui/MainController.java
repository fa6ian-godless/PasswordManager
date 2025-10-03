package org.passwordmanager.ui;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class MainController {

    @FXML private Pane topBar;
    @FXML private VBox passwordList;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            Stage stage = (Stage) topBar.getScene().getWindow();
            Scene scene = topBar.getScene();

            scene.setOnMousePressed(mouseEvent -> {
                xOffset = mouseEvent.getScreenX() - stage.getX();
                yOffset = mouseEvent.getScreenY() - stage.getY();
            });

            scene.setOnMouseDragged(mouseEvent -> {
                stage.setX(mouseEvent.getScreenX() - xOffset);
                stage.setY(mouseEvent.getScreenY() - yOffset);
            });
        });
    }

    @FXML
    private void handleAddPassword(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addData.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Color.TRANSPARENT);

            AddDataController controller = fxmlLoader.getController();
            controller.setMainController(this);

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

            stage.setOnHidden(windowEvent ->
                    ((Pane) owner.getScene().getRoot()).getChildren().remove(overlay));

            stage.showAndWait();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void handleSettings() {

    }

    public void addPasswordCard(String using, String login, String password) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/passwordCard.fxml"));

        AnchorPane card = fxmlLoader.load();

        CardController controller = fxmlLoader.getController();
        controller.setData(using, login, password);

        passwordList.getChildren().add(card);
    }
}

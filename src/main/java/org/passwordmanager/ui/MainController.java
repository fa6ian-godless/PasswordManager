package org.passwordmanager.ui;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add_data.fxml"));
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

    public void addPasswordEntry(String using, String login, String password) {
        System.out.print("\nDEBUG : \n" + "Using : " + using + "\n" + "Login : " + login + "\n" + "Password : " + password + "\n");

        HBox entry = new HBox(10);
        entry.setStyle("-fx-background-color: #ddd; -fx-padding: 10; -fx-background-radius: 15;");

        Label usingLabel = new Label(using);
        usingLabel.setStyle("-fx-font-size: 14px;");

        Button showBtn = new Button("\uD83D\uDC41");
        showBtn.setOnAction(event -> {
            System.out.println("Login : " + login);
            System.out.println("Password : " + password);
        });

        entry.getChildren().addAll(usingLabel, showBtn);
        passwordList.getChildren().add(entry);

        System.out.print("DEBUG : Count elements in list : " + passwordList.getChildren().size() + "\n");
    }
}

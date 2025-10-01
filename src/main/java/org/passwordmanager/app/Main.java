package org.passwordmanager.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));

        stage.setScene(new Scene(fxmlLoader.load(), Color.TRANSPARENT));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Password manager");
        stage.centerOnScreen();
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
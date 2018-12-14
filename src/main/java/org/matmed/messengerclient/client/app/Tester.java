package org.matmed.messengerclient.client.app;

import org.matmed.messengerclient.client.controllers.ImagePicker;
import org.matmed.messengerclient.client.network.ClientSocket;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tester extends Application {
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);

        Parent p = ImagePicker.create().getRoot();
        primaryStage.setScene(new Scene(p));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

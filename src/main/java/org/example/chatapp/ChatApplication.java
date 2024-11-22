package org.example.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatApplication extends Application {
    private final ChatMediatorImpl mediator = new ChatMediatorImpl();
    private double initialX = 100;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mediator.registerClient(new ChatClientImpl("Masa", mediator, null));
        mediator.registerClient(new ChatClientImpl("Pena", mediator, null));
        launchClient("Masa");
        launchClient("Pena");
    }

    private void launchClient(String username) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/chatapp/chat-app.fxml"));
        stage.setScene(new Scene(loader.load()));
        ChatAppController controller = loader.getController();
        controller.initialize(username, mediator);

        // Set window title
        stage.setTitle(username + "'s Chat");

        // Position the window
        stage.setX(initialX);
        double initialY = 100; // Fixed Y position
        stage.setY(initialY);

        // Increment X position for the next window
        double offsetX = 350;
        initialX += offsetX;

        // Show the window
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

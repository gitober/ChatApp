package org.example.chatapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatAppController {
    @FXML private TextArea messageDisplay;
    @FXML private TextField messageInput;
    @FXML private ComboBox<String> recipientSelector;
    @FXML private Button sendButton;

    private ChatClient client;

    // Method to initialize the controller
    public void initialize(String username, ChatMediator mediator) {
        client = new ChatClientImpl(username, mediator, messageDisplay);

        // Populate the recipient selector with all usernames except the current user
        if (mediator instanceof ChatMediatorImpl chatMediator) {
            for (String otherUsername : chatMediator.getAllUsernames()) {
                if (!otherUsername.equals(username)) {
                    recipientSelector.getItems().add(otherUsername);
                }
            }
        }
        // Set the first recipient as the default value
        sendButton.setOnAction(event -> sendMessage());
    }

    // Method to send a message
    @FXML
    private void sendMessage() {
        String message = messageInput.getText();
        String recipient = recipientSelector.getValue();

        if (message != null && !message.isEmpty() && recipient != null) {
            client.sendMessage(message, recipient);
            messageDisplay.appendText("You: " + message + "\n");
            messageInput.clear();
        } else {
            messageDisplay.appendText("Please enter a valid message and select a recipient.\n");
        }
    }
}

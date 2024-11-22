package org.example.chatapp;

import javafx.scene.control.TextArea;

public class ChatClientImpl implements ChatClient {
    private final String username;
    private final ChatMediator mediator;
    private final TextArea messageDisplay;

    public ChatClientImpl(String username, ChatMediator mediator, TextArea messageDisplay) {
        this.username = username;
        this.mediator = mediator;
        this.messageDisplay = messageDisplay;
        mediator.registerClient(this);
    }

    @Override
    public void sendMessage(String message, String recipient) {
        mediator.sendMessage(message, recipient, username);
    }

    @Override
    public void receiveMessage(String message, String sender) {
        messageDisplay.appendText(sender + ": " + message + "\n");
    }

    @Override
    public String getUsername() {
        return username;
    }
}

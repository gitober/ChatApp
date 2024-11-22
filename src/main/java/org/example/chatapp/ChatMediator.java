package org.example.chatapp;

public interface ChatMediator {
    void sendMessage(String message, String recipient, String sender);
    void registerClient(ChatClient client);
}

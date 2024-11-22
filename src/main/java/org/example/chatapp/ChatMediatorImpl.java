package org.example.chatapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChatMediatorImpl implements ChatMediator {
    private final Map<String, ChatClient> clients = new HashMap<>();

    @Override
    public void sendMessage(String message, String recipient, String sender) {
        if (clients.containsKey(recipient)) {
            clients.get(recipient).receiveMessage(message, sender);
        }
    }

    @Override
    public void registerClient(ChatClient client) {
        clients.put(client.getUsername(), client);
    }

    // Method to get all registered usernames
    public Set<String> getAllUsernames() {
        return clients.keySet();
    }
}

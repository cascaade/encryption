package org.example;

public class Message {
    private final String message;
    private final boolean encrypted;

    public Message(String message, boolean encrypted) {
        this.message = message;
        this.encrypted = encrypted;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public String toString() {
        return message;
    }
}

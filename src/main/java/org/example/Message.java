package org.example;

public class Message {
    private final String message;
    private final boolean encrypted;

    private final byte[] iv;

    public Message(String message, boolean encrypted) {
        this(message, encrypted, new byte[0]);
    }

    public Message(String message, boolean encrypted, byte[] iv) {
        this.message = message;
        this.encrypted = encrypted;
        this.iv = iv;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public byte[] getIV() {
        return iv;
    }

    public String toString() {
        return message;
    }
}

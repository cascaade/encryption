package org.example;

public class CaesarCipher implements EncryptionAlgorithm {
    private final int shift;
    private final int max_value = 128;

    public CaesarCipher(int shift) {
        this.shift = shift % max_value;
    }

    @Override
    public Message encrypt(Message plaintext) {
        String message = "";
        for (char c : plaintext.toString().toCharArray()) {
            message += (char) (((int) c + shift) % max_value);
        }
        return new Message(message, true);
    }

    @Override
    public Message decrypt(Message ciphertext) {
        String message = "";
        for (char c : ciphertext.toString().toCharArray()) {
            message += (char) Math.floorMod((int) c - shift, max_value);
        }
        return new Message(message, false);
    }
}

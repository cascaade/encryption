package org.example;

public class CaesarCipher implements EncryptionAlgorithm<Integer> {
    private final int max_value = 128;

    @Override
    public Message encrypt(Message plaintext, Integer key) {
        if (plaintext.isEncrypted())
            throw new IllegalArgumentException("The message is already encrypted.");

        int shift = key % max_value;

        String message = "";
        for (char c : plaintext.toString().toCharArray()) {
            message += (char) (((int) c + shift) % max_value);
        }

        return new Message(message, true);
    }

    @Override
    public Message decrypt(Message ciphertext, Integer key) {
        if (!ciphertext.isEncrypted())
            throw new IllegalArgumentException("The message is already decrypted.");

        int shift = key % max_value;

        String message = "";
        for (char c : ciphertext.toString().toCharArray()) {
            message += (char) Math.floorMod((int) c - shift, max_value);
        }

        return new Message(message, false);
    }

    @Override
    public Integer parsePassword(String passwordInput) {
        return Integer.parseInt(passwordInput);
    }
}

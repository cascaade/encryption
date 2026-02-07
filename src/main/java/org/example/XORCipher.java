package org.example;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class XORCipher implements EncryptionAlgorithm {
    private final byte[] key;

    public XORCipher() {
        key = new byte[32];

        for (int i = 0; i < key.length; i++) {
            key[i] = (byte) (Math.random() * Math.pow(2, 8));
        }
    }

    @Override
    public Message encrypt(Message plaintext) {
        byte[] textBytes = plaintext.toString().getBytes();
        byte[] encrypted = new byte[textBytes.length];

        for (int i = 0; i < textBytes.length; i++) {
            encrypted[i] = (byte) (textBytes[i] ^ key[i % key.length]);
        }

        String encoded = Base64.getEncoder().encodeToString(encrypted);

        return new Message(encoded, true);
    }

    @Override
    public Message decrypt(Message ciphertext) {
        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertext.toString());
        byte[] decrypted = new byte[encryptedBytes.length];

        for (int i = 0; i < encryptedBytes.length; i++) {
            decrypted[i] = (byte) (encryptedBytes[i] ^ key[i % key.length]);
        }

        String plaintext = new String(decrypted, StandardCharsets.UTF_8);

        return new Message(plaintext, false);
    }
}

package org.example;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class XORCipher implements EncryptionAlgorithm<Byte[]> {
    @Override
    public Message encrypt(Message plaintext, Byte[] key) {
        if (plaintext.isEncrypted())
            throw new IllegalArgumentException("The message is already encrypted.");

        byte[] textBytes = plaintext.toString().getBytes();
        byte[] encrypted = new byte[textBytes.length];

        for (int i = 0; i < textBytes.length; i++) {
            encrypted[i] = (byte) (textBytes[i] ^ key[i % key.length]);
        }

        String encoded = Base64.getEncoder().encodeToString(encrypted);

        return new Message(encoded, true);
    }

    @Override
    public Message decrypt(Message ciphertext, Byte[] key) {
        if (!ciphertext.isEncrypted())
            throw new IllegalArgumentException("The message is already decrypted.");

        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertext.toString());
        byte[] decrypted = new byte[encryptedBytes.length];

        for (int i = 0; i < encryptedBytes.length; i++) {
            decrypted[i] = (byte) (encryptedBytes[i] ^ key[i % key.length]);
        }

        String plaintext = new String(decrypted, StandardCharsets.UTF_8);

        return new Message(plaintext, false);
    }

    @Override
    public Byte[] parsePassword(String passwordInput) throws Exception {
        return new Byte[0];
    }
}

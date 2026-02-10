package org.example;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.IntStream;

public class ScrambleCipher implements EncryptionAlgorithm<Byte[]> {
    public static byte[] createKey() {
        byte[] key = new byte[16];

        for (int i = 0; i < key.length; i++) {
            key[i] = (byte) (Math.pow(2, 8) * Math.random());
        }

        return key;
    }

    @Override
    public Message encrypt(Message plaintext, Byte[] key) {
        if (key.length != 16)
            throw new IllegalArgumentException("Unacceptable number of key bytes");

        byte[] bytes = plaintext.toString().getBytes();

        if (bytes.length != 16)
            throw  new IllegalArgumentException("Unacceptable number of message bytes");

        byte[][] cipherBytes = new byte[4][4];

        for (int i = 0; i < bytes.length; i++) {
            cipherBytes[i / 4][i % 4] = (byte) (bytes[i] ^ key[i]);
        }

        byte[] outBytes = new byte[16];

        int index = 0;
        for (int i = 0; i < cipherBytes.length; i++) {
            for (int j = 0; j < cipherBytes[i].length; j++) {
                outBytes[index++] = cipherBytes[i][j];
            }
        }

        return new Message(new String(outBytes, StandardCharsets.UTF_8), true);
    }

    @Override
    public Message decrypt(Message ciphertext, Byte[] key) {
        return new Message("not yet implemented", false);
    }
}

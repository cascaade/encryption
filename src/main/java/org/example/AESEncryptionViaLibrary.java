package org.example;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESEncryptionViaLibrary implements EncryptionAlgorithm<SecretKey> {
    // Constant for the GCM Tag length (128 bits is standard)
    private static final int GCM_TAG_LENGTH = 128;
    // Constant for the Initialization Vector length (12 bytes is standard for GCM)
    private static final int GCM_IV_LENGTH = 12;

    private static final byte[] salt = new byte[16];

    public AESEncryptionViaLibrary() {
        new SecureRandom().nextBytes(salt);
    }

    @Override
    public Message encrypt(Message plaintext, SecretKey key) throws Exception {
        if (plaintext.isEncrypted())
            throw new IllegalArgumentException("The message is already encrypted.");

        byte[] iv = new byte[GCM_IV_LENGTH];
        new SecureRandom().nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

        cipher.init(Cipher.ENCRYPT_MODE, key, gcmParameterSpec);

        byte[] cipherText = cipher.doFinal(plaintext.toString().getBytes(StandardCharsets.UTF_8));

        return new Message(Base64.getEncoder().encodeToString(cipherText), true, iv);
    }

    @Override
    public Message decrypt(Message ciphertext, SecretKey secretKey) throws Exception {
        if (!ciphertext.isEncrypted())
            throw new IllegalArgumentException("The message is already decrypted.");

        byte[] iv = ciphertext.getIV();

        if (iv.length != GCM_IV_LENGTH)
            throw new IllegalArgumentException("Invalid message for decryption.");

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertext.toString());
        byte[] plainText = cipher.doFinal(encryptedBytes);

        return new Message(new String(plainText, StandardCharsets.UTF_8), false);
    }

    @Override
    public SecretKey parsePassword(String password) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        // 65536 iterations, 256-bit key length
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);

        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }
}

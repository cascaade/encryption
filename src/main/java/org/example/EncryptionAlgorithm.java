package org.example;

public interface EncryptionAlgorithm {
    Message encrypt(Message plaintext);
    Message decrypt(Message ciphertext);
}

package org.example;

public interface EncryptionAlgorithm<K> {
    Message encrypt(Message plaintext, K key);
    Message decrypt(Message ciphertext, K key);
}
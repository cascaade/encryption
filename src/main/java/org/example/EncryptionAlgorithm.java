package org.example;

public interface EncryptionAlgorithm<K> {
    Message encrypt(Message plaintext, K key) throws IllegalArgumentException;
    Message decrypt(Message ciphertext, K key) throws IllegalArgumentException;
}
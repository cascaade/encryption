package org.example;

public interface EncryptionAlgorithm<K> {
    Message encrypt(Message plaintext, K key) throws Exception;
    Message decrypt(Message ciphertext, K key) throws Exception;

    K parsePassword(String passwordInput) throws Exception;
}
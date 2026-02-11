package org.example;

public class AESEncryption implements EncryptionAlgorithm<Byte[]> {
    private byte[][] KeyExpansion(byte[][] key) {
        return key;
    }

    private byte[][] AddRoundKey(byte[][] state, byte[][] roundKey) {
        return state;
    }

    private byte[][] SubBytes(byte[][] state) {
        return state;
    }

    private byte[][] ShiftRows(byte[][] state) {
        return state;
    }

    private byte[][] MixColumns(byte[][] state) {
        return state;
    }

    @Override
    public Message encrypt(Message plaintext, Byte[] key) throws IllegalArgumentException {
        return new Message("", true);
    }

    @Override
    public Message decrypt(Message ciphertext, Byte[] key) throws IllegalArgumentException {
        return new Message("", false);
    }
}

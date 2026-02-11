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
        byte[][] state = new byte[][] {};
        byte[][] roundKey = new byte[][] {};

        AddRoundKey(state, roundKey);
        roundKey = KeyExpansion(roundKey);

        for (int i = 0; i < 9; i++) {
            SubBytes(state);
            ShiftRows(state);
            MixColumns(state);
            AddRoundKey(state, roundKey);

            roundKey = KeyExpansion(roundKey);
        }

        SubBytes(state);
        ShiftRows(state);
        AddRoundKey(state, roundKey);

        return new Message("", true);
    }

    @Override
    public Message decrypt(Message ciphertext, Byte[] key) throws IllegalArgumentException {
        return new Message("", false);
    }
}

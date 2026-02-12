package org.example;

public class AESEncryption implements EncryptionAlgorithm<Byte[]> {
    private void KeyExpansion(byte[][] key, int round) {

    }

    private void AddRoundKey(byte[][] state, byte[][] roundKey) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                state[row][col] = (byte) (state[row][col] ^ roundKey[row][col]);
            }
        }
    }

    private void SubBytes(byte[][] state) {

    }

    private void ShiftRows(byte[][] state) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                state[row][col] = state[row][Math.floorMod(col + row, 4)];
            }
        }
    }

    private void MixColumns(byte[][] state) {

    }

    @Override
    public Message encrypt(Message plaintext, Byte[] key) throws IllegalArgumentException {
        byte[][] state = new byte[][] {};
        byte[][] roundKey = new byte[][] {};

        AddRoundKey(state, roundKey);
        KeyExpansion(roundKey, 0);

        for (int i = 0; i < 9; i++) {
            SubBytes(state);
            ShiftRows(state);
            MixColumns(state);
            AddRoundKey(state, roundKey);

            KeyExpansion(roundKey, i + 1);
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

    @Override
    public Byte[] parsePassword(String passwordInput) {
        return new Byte[0];
    }
}

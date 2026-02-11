package org.example;

public class AESEncryption implements EncryptionAlgorithm<Byte[]> {
    private byte[][] KeyExpansion(byte[][] key) {
        return key;
    }

    private byte[][] AddRoundKey(byte[][] state, byte[][] roundKey) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                state[row][col] = (byte) (state[row][col] ^ roundKey[row][col]);
            }
        }
        return state;
    }

    private byte[][] SubBytes(byte[][] state) {
        return state;
    }

    private byte[][] ShiftRows(byte[][] state) {
        byte[][] out = new byte[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                out[row][col] = state[row][Math.floorMod(col + row, 4)];
            }
        }
        return out;
    }

    private byte[][] MixColumns(byte[][] state) {
        return state;
    }

    @Override
    public Message encrypt(Message plaintext, Byte[] key) throws IllegalArgumentException {
        byte[][] state = new byte[][] {};
        byte[][] roundKey = new byte[][] {};

        state = AddRoundKey(state, roundKey);
        roundKey = KeyExpansion(roundKey);

        for (int i = 0; i < 9; i++) {
            state = SubBytes(state);
            state = ShiftRows(state);
            state = MixColumns(state);
            state = AddRoundKey(state, roundKey);

            roundKey = KeyExpansion(roundKey);
        }

        state = SubBytes(state);
        state = ShiftRows(state);
        state = AddRoundKey(state, roundKey);

        return new Message("", true);
    }

    @Override
    public Message decrypt(Message ciphertext, Byte[] key) throws IllegalArgumentException {
        return new Message("", false);
    }
}

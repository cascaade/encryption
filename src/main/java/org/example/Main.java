package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EncryptionAlgorithm algorithm = new XORCipher();

        Byte[] xorKey = new Byte[32];

        for (int i = 0; i < xorKey.length; i++) {
            xorKey[i] = (byte) (Math.random() * Math.pow(2, 8));
        }

        while (true) {
            System.out.print("\nEncrypt (e), decrypt (d), or quit (q)?: ");
            String option = scanner.nextLine();
            if (option.equals("e")) {
                System.out.print("Enter a message: ");
                String input = scanner.nextLine();

                try {
                    Message result = algorithm.encrypt(new Message(input, false), xorKey);

                    System.out.print(input);
                    System.out.print(" >> [encryption] >> ");
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println("Input could not be encoded.");
                }
            } else if (option.equals("d")) {
                System.out.print("Enter a message: ");
                String input = scanner.nextLine();

                try {
                    Message result = algorithm.decrypt(new Message(input, true), xorKey);

                    System.out.print(input);
                    System.out.print(" >> [decryption] >> ");
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println("Input could not be decoded.");
                }
            } else if (option.equals("q")) {
                System.out.println("bye :(");
                break;
            } else {
                System.out.println("Invalid statement.");
            }
        }
    }
}
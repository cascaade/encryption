package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EncryptionAlgorithm algorithm = new XORCipher();

        while (true) {
            System.out.print("\nEncrypt (e), decrypt (d), or quit (q)?: ");
            String option = scanner.nextLine();
            if (option.equals("e")) {
                System.out.print("Enter a message: ");

                String input = scanner.nextLine();
                Message result = algorithm.encrypt(new Message(input, false));

                System.out.print(input);
                System.out.print(" >> [encryption] >> ");
                System.out.println(result);
            } else if (option.equals("d")) {
                System.out.print("Enter a message: ");

                String input = scanner.nextLine();
                Message result = algorithm.decrypt(new Message(input, true));

                System.out.print(input);
                System.out.print(" >> [decryption] >> ");
                System.out.println(result);
            } else if (option.equals("q")) {
                System.out.println("bye :(");
                break;
            } else {
                System.out.println("Invalid statement.");
            }
        }
    }
}
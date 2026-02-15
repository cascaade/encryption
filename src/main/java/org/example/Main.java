package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EncryptionAlgorithm algorithm = new AESEncryptionViaLibrary();

        Byte[] xorKey = new Byte[32];

        for (int i = 0; i < xorKey.length; i++) {
            xorKey[i] = (byte) (Math.random() * Math.pow(2, 8));
        }

        ArrayList<Message> messageList = new ArrayList<>();

        while (true) {
            System.out.print("\nEncrypt (e), decrypt (d), or quit (q)?: ");
            String option = scanner.nextLine();

            if (option.equals("e")) {
                for (int i = 0; i < messageList.size(); i++) {
                    Message message = messageList.get(i);
                    System.out.print("[" + i + "]: ");
                    System.out.print(message.isEncrypted() ? "crypt" : "plain");
                    System.out.print("@" + message.hashCode());
                    System.out.println(" - " + message);
                }

                System.out.print("Enter a message or reference number (m0, m1, etc.): ");
                Message inputMsg = new Message(scanner.nextLine(), false);
                int referenceUsed = -1;

                try {
                    int n = Integer.parseInt(inputMsg.toString().substring(1));
                    if (n >= 0 && n < messageList.size() && inputMsg.toString().charAt(0) == 'm') {
                        inputMsg = messageList.get(n);
                        referenceUsed = n;
                    }
                } catch (Exception e) {

                }

                System.out.print("Enter a password: ");
                String password = scanner.nextLine();

                try {
                    Message result = algorithm.encrypt(inputMsg, algorithm.parsePassword(password));

                    System.out.print((referenceUsed >= 0 ? "[m" + referenceUsed + "]: " : "") + inputMsg);
                    System.out.print(" >> [encryption] >> ");
                    System.out.println(result);

                    if (referenceUsed < 0) messageList.add(result);
                } catch (Exception e) {
                    System.out.print("Input could not be encoded: ");
                    System.out.println(e.getMessage());
                }
            } else if (option.equals("d")) {
                for (int i = 0; i < messageList.size(); i++) {
                    Message message = messageList.get(i);
                    System.out.print("[" + i + "]: ");
                    System.out.print(message.isEncrypted() ? "crypt" : "plain");
                    System.out.print("@" + message.hashCode());
                    System.out.println(" - " + message);
                }

                System.out.print("Enter a message or reference number (m0, m1, etc.): ");
                Message inputMsg = new Message(scanner.nextLine(), true);
                int referenceUsed = -1;

                try {
                    int n = Integer.parseInt(inputMsg.toString().substring(1));
                    if (n >= 0 && n < messageList.size() && inputMsg.toString().charAt(0) == 'm') {
                        inputMsg = messageList.get(n);
                        referenceUsed = n;
                    }
                } catch (Exception e) {

                }

                System.out.print("Enter a password: ");
                String password = scanner.nextLine();

                try {
                    Message result = algorithm.decrypt(inputMsg, algorithm.parsePassword(password));

                    System.out.print((referenceUsed >= 0 ? "[m" + referenceUsed + "]: " : "") + inputMsg);
                    System.out.print(" >> [decryption] >> ");
                    System.out.println(result);

                    if (referenceUsed < 0) messageList.add(result);
                } catch (Exception e) {
                    System.out.print("Input could not be decoded: ");
                    System.out.println(e.getMessage());
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
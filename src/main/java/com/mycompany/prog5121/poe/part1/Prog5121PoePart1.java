package com.mycompany.prog5121.poe.part1;

import java.util.Scanner;

/**
 * Console application for the PROG5121 PoE.
 *
 * Part 1: the user registers and logs in.
 * Part 2: after a successful login, the QuickChat message menu opens
 * and runs until the user quits.
 */
public class Prog5121PoePart1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("======================================");
        System.out.println("         QuickChat Login     ");
        System.out.println("======================================");

        // ==================== PART 1: REGISTRATION ====================
        System.out.println("\n--- REGISTRATION ---");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        String username;
        while (true) {
            System.out.print("Enter username (must contain '_' and be max "
                    + "5 characters): ");
            username = scanner.nextLine();
            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; "
                        + "please ensure that your username contains an "
                        + "underscore and is no more than five characters "
                        + "in length.");
            }
        }

        String password;
        while (true) {
            System.out.print("Enter password (min 8 characters, capital, "
                    + "number, special character): ");
            password = scanner.nextLine();
            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; "
                        + "please ensure that the password contains at "
                        + "least eight characters, a capital letter, a "
                        + "number, and a special character.");
            }
        }

        String cellPhoneNumber;
        while (true) {
            System.out.print("Enter South African cell phone number "
                    + "(e.g. +27838968976): ");
            cellPhoneNumber = scanner.nextLine();
            if (login.checkCellPhoneNumber(cellPhoneNumber)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted "
                        + "or does not contain international code.");
            }
        }

        // names + details all handed to the Login object
        String registrationMessage = login.registerUser(firstName, lastName,
                username, password, cellPhoneNumber);
        System.out.println("\n" + registrationMessage);

        // ==================== PART 1: LOGIN ====================
        System.out.println("\n--- LOGIN ---");
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        String loginStatus = login.returnLoginStatus(loginUsername,
                loginPassword, firstName, lastName);
        System.out.println(loginStatus);

        // ==================== PART 2: QUICKCHAT ====================
        // Messages may only be sent once the user has logged in
        if (loginStatus.startsWith("Welcome")) {
            System.out.println("\nWelcome to QuickChat.");
            runQuickChat(scanner);
        }

        scanner.close();
    }

    /**
     * Runs the QuickChat numeric menu until the user selects quit.
     */
    private static void runQuickChat(Scanner scanner) {
        System.out.print("How many messages do you wish to enter? ");
        int numMessages = readInt(scanner);
        int entered = 0;
        boolean quit = false;

        while (!quit) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter choice: ");
            int option = readInt(scanner);

            switch (option) {
                case 1:
                    if (entered >= numMessages) {
                        System.out.println("You may only enter "
                                + numMessages + " message(s).");
                        break;
                    }

                    // Recipient: loop until valid
                    String recipient;
                    while (true) {
                        System.out.print("Enter recipient cell number: ");
                        recipient = scanner.nextLine();
                        String result = Message.checkRecipientCell(recipient);
                        System.out.println(result);
                        if (result.equals("Cell phone number successfully "
                                + "captured.")) {
                            break;
                        }
                    }

                    // Message text: loop until within 250 characters
                    String text;
                    while (true) {
                        System.out.print("Enter message (max 250 "
                                + "characters): ");
                        text = scanner.nextLine();
                        String result = Message.checkMessageLength(text);
                        System.out.println(result);
                        if (result.equals("Message ready to send.")) {
                            break;
                        }
                    }

                    // Build the message (ID and hash auto-generated)
                    Message message = new Message(recipient, text, entered);

                    System.out.println("What would you like to do with "
                            + "this message?");
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message to send later");
                    System.out.print("Enter choice: ");
                    int action = readInt(scanner);
                    String outcome = message.sentMessage(action);
                    System.out.println(outcome);

                    if (action == 1) {
                        message.storeMessage();
                        System.out.println("\nFull message details:");
                        System.out.println("Message ID: "
                                + message.getMessageID());
                        System.out.println("Message Hash: "
                                + message.getMessageHash());
                        System.out.println("Recipient: "
                                + message.getRecipient());
                        System.out.println("Message: "
                                + message.getMessage());
                    } else if (action == 2) {
                        System.out.print("Enter 0 to confirm deletion: ");
                        if (scanner.nextLine().trim().equals("0")) {
                            System.out.println("Message deleted.");
                        }
                    }

                    entered++;
                    break;

                case 2:
                    // Still in development
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        // Total accumulated once all messages have been handled
        System.out.println("\nTotal messages sent: "
                + Message.returnTotalMessages());
        System.out.println("Thank you for using QuickChat.");
    }

    /**
     * Reads a whole-number value from the user, re-prompting on bad input.
     */
    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}   // <-- THIS closing brace was missing
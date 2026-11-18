package com.mycompany.prog5121.poe.part1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessageManager {
    // Dynamic Parallel Arrays / Lists required by Part 3
    public static List<Message> allMessages = new ArrayList<>();
    public static List<String> sentMessages = new ArrayList<>();
    public static List<String> disregardedMessages = new ArrayList<>();
    public static List<Message> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();

    private static final String JSON_FILE_PATH = "stored_messages.json";

    // Populates all parallel arrays dynamically
    public static void populateArrays(List<Message> messages) {
        allMessages.clear();
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();

        for (Message msg : messages) {
            allMessages.add(msg);
            messageIDs.add(msg.getMessageId());
            messageHashes.add(msg.getMessageHash());

            if ("Sent".equalsIgnoreCase(msg.getFlag())) {
                sentMessages.add(msg.getMessageText());
            } else if ("Disregard".equalsIgnoreCase(msg.getFlag())) {
                disregardedMessages.add(msg.getMessageText());
            } else if ("Stored".equalsIgnoreCase(msg.getFlag())) {
                storedMessages.add(msg);
            }
        }
    }

    // Save stored messages array to a JSON file
    public static void saveStoredMessagesToJson() {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            writer.write("[\n");
            for (int i = 0; i < storedMessages.size(); i++) {
                Message msg = storedMessages.get(i);
                writer.write("  {\n");
                writer.write("    \"messageId\": \"" + msg.getMessageId() + "\",\n");
                writer.write("    \"sender\": \"" + msg.getSender() + "\",\n");
                writer.write("    \"recipient\": \"" + msg.getRecipient() + "\",\n");
                writer.write("    \"messageText\": \"" + msg.getMessageText().replace("\"", "\\\"") + "\",\n");
                writer.write("    \"flag\": \"" + msg.getFlag() + "\",\n");
                writer.write("    \"messageHash\": \"" + msg.getMessageHash() + "\"\n");
                writer.write("  }" + (i < storedMessages.size() - 1 ? "," : "") + "\n");
            }
            writer.write("]\n");
        } catch (IOException e) {
            System.err.println("Error saving JSON: " + e.getMessage());
        }
    }

    // Read stored messages from JSON file into an array
    public static List<Message> readStoredMessagesFromJson() {
        List<Message> loaded = new ArrayList<>();
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return loaded;
        }

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            String content = sb.toString();
            // Basic parsing of JSON objects
            String[] items = content.split("\\{");
            for (String item : items) {
                if (item.contains("\"messageId\"")) {
                    String id = extractJsonValue(item, "messageId");
                    String sender = extractJsonValue(item, "sender");
                    String recipient = extractJsonValue(item, "recipient");
                    String text = extractJsonValue(item, "messageText");
                    String flag = extractJsonValue(item, "flag");
                    String hash = extractJsonValue(item, "messageHash");
                    loaded.add(new Message(id, sender, recipient, text, flag, hash));
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading JSON: " + e.getMessage());
        }
        return loaded;
    }

    private static String extractJsonValue(String jsonBlock, String key) {
        String pattern = "\"" + key + "\":\\s*\"";
        int start = jsonBlock.indexOf(pattern);
        if (start == -1) return "";
        start += pattern.length();
        int end = jsonBlock.indexOf("\"", start);
        if (end == -1) return "";
        return jsonBlock.substring(start, end).replace("\\\"", "\"");
    }

    // a. Display sender and recipient of all stored messages
    public static String displayStoredSendersAndRecipients() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("--- STORED MESSAGES (SENDER & RECIPIENT) ---\n");
        for (Message msg : storedMessages) {
            sb.append("Sender: ").append(msg.getSender())
              .append(" | Recipient: ").append(msg.getRecipient()).append("\n");
        }
        return sb.toString();
    }

    // b. Display the longest stored message
    public static String getLongestMessage() {
        if (allMessages.isEmpty()) {
            return "No messages available.";
        }
        Message longest = allMessages.get(0);
        for (Message msg : allMessages) {
            if (msg.getMessageText().length() > longest.getMessageText().length()) {
                longest = msg;
            }
        }
        return longest.getMessageText();
    }

    // c. Search for a message ID and display corresponding recipient and message
    public static String searchByMessageId(String messageId) {
        for (Message msg : allMessages) {
            if (msg.getMessageId().equalsIgnoreCase(messageId)) {
                return "Recipient: " + msg.getRecipient() + "\nMessage: \"" + msg.getMessageText() + "\"";
            }
        }
        return "Message ID not found.";
    }

    // d. Search for all messages sent or stored for a particular recipient
    public static List<String> searchByRecipient(String recipient) {
        List<String> results = new ArrayList<>();
        for (Message msg : allMessages) {
            if (msg.getRecipient().equalsIgnoreCase(recipient)) {
                results.add(msg.getMessageText());
            }
        }
        return results;
    }

    // e. Delete a message using the message hash
    public static String deleteMessageByHash(String hash) {
        for (int i = 0; i < allMessages.size(); i++) {
            Message msg = allMessages.get(i);
            if (msg.getMessageHash().equalsIgnoreCase(hash)) {
                String deletedText = msg.getMessageText();
                allMessages.remove(i);
                populateArrays(new ArrayList<>(allMessages));
                saveStoredMessagesToJson();
                return "Message: \"" + deletedText + "\" successfully deleted.";
            }
        }
        return "Message with hash " + hash + " not found.";
    }

    // f. Display report listing full details of all messages
    public static String displayReport() {
        if (allMessages.isEmpty()) {
            return "No messages to display in report.";
        }
        StringBuilder report = new StringBuilder();
        report.append("=================== MESSAGE REPORT ===================\n");
        for (Message msg : allMessages) {
            report.append("Message Hash : ").append(msg.getMessageHash()).append("\n")
                  .append("Recipient    : ").append(msg.getRecipient()).append("\n")
                  .append("Message      : \"").append(msg.getMessageText()).append("\"\n")
                  .append("Status/Flag  : ").append(msg.getFlag()).append("\n")
                  .append("------------------------------------------------------\n");
        }
        return report.toString();
    }
}
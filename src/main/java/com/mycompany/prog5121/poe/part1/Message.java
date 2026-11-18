package com.mycompany.prog5121.poe.part1;

public class Message {
    private String messageId;
    private String sender;
    private String recipient;
    private String messageText;
    private String flag; // "Sent", "Stored", "Disregard"
    private String messageHash;
    
    private static int totalMessages = 0;

    // Default Constructor
    public Message() {}

    // Constructor for Part 2 (recipient, messageText, messageNumber)
    public Message(String recipient, String messageText, int messageNumber) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.sender = "Developer";
        this.messageId = String.format("%010d", messageNumber);
        this.messageHash = createMessageHash(this.messageId, messageNumber, messageText);
        this.flag = "Sent";
        totalMessages++;
    }

    // Constructor for Part 3
    public Message(String messageId, String sender, String recipient, String messageText, String flag, String messageHash) {
        this.messageId = messageId;
        this.sender = sender;
        this.recipient = recipient;
        this.messageText = messageText;
        this.flag = flag;
        this.messageHash = messageHash;
        totalMessages++;
    }

    // --- Validation Methods (Returning String for Part 2) ---
    public static String checkRecipientCell(String cellNumber) {
        if (cellNumber == null || cellNumber.trim().isEmpty()) {
            return "Cell phone number is incorrectly formatted or contains international code. Please rectify.";
        }
        String clean = cellNumber.trim();
        if (clean.length() <= 12 && (clean.startsWith("+27") || clean.startsWith("0") || clean.matches("\\d+"))) {
            return "Cell phone number is successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or contains international code. Please rectify.";
    }

    public static String checkMessageLength(String message) {
        if (message != null && !message.trim().isEmpty() && message.length() <= 250) {
            return "Message ready to send";
        }
        return "Please enter a message between 1 and 250 characters.";
    }

    public String createMessageHash(String id, int num, String text) {
        if (text == null || text.isEmpty()) return "HASH000";
        String prefix = text.length() >= 2 ? text.substring(0, 2).toUpperCase() : text.toUpperCase();
        return prefix + ":" + id + ":" + num;
    }

    // --- Action Methods (Part 2) ---
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                this.flag = "Sent";
                return "Message successfully sent.";
            case 2:
                this.flag = "Disregard";
                return "Press 0 to delete message.";
            case 3:
                this.flag = "Stored";
                return "Message successfully stored.";
            default:
                this.flag = "Sent";
                return "Message sent.";
        }
    }

    public String storeMessage() {
        this.flag = "Stored";
        return "Message stored successfully.";
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    public static void setTotalMessages(int total) {
        totalMessages = total;
    }

    public String printMessageDetails() {
        return "Message ID: " + messageId + "\n"
             + "Message Hash: " + messageHash + "\n"
             + "Recipient: " + recipient + "\n"
             + "Message: " + messageText + "\n"
             + "Status: " + flag;
    }

    // --- Getters & Setters ---
    public String getMessageId() { return messageId; }
    public String getMessageID() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getMessage() { return messageText; }
    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }

    public String getFlag() { return flag; }
    public void setFlag(String flag) { this.flag = flag; }

    public String getMessageHash() { return messageHash; }
    public void setMessageHash(String messageHash) { this.messageHash = messageHash; }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tinas
 */
package com.mycompany.prog5121.poe.part1;

import java.io.IOException;
    import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.util.ArrayList;
            import java.util.List;

/**
 * Message class (Sending Messages).
 */
public class Message {

    private String messageID;
      private String recipient;
    private String message;
             private String messageHash;
         private int messageNumber;

    /** Running the total of messages during this session */
         
            private static int totalMessagesSent = 0;

    /** The details  of every sent message, is stored while the program runs. */
    
            private static final List<Message> SENT_MESSAGES = new ArrayList<>();

    /**
     * Builds a message with an auto generated ten digit message ID.
     */
    public Message(String recipient, String message, int messageNumber)
    {
        this.messageID = generateMessageID();
            this.recipient = recipient;
        this.message = message;
                 this.messageNumber = messageNumber;
                  this.messageHash = createMessageHash();
    }

    /**
     * Test friendly constructor that approves  a known message ID.
     */
    Message(String messageID, String recipient, String message,
                 int messageNumber)
    {
        this.messageID = messageID;
                 this.recipient = recipient;
        this.message = message;
        
                   this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
    }

    /** The string will generate a random ten-digit message IDs. */
    
    private String generateMessageID() 
    {
        long id = (long) (Math.random() * 10_000_000_000L);
        
            return String.format("%010d", id);
    }

    /**
     * Makes sure that the message ID does not exceed more  than ten characters long.
     */
    
    public static boolean checkMessageID(String messageID)
    {
        return messageID != null && messageID.length()
                
                        <= 10;
    }

    /**
     * Makes sure that the recipients cell number must start with an international
     * code and that the number part is no more than ten digits long.
     */
    
    public static String checkRecipientCell(String recipient)
    {
        if (recipient != null && recipient.matches ("^\\+\\d{1,4}\\d{1,10}$")
                )
        {
            return "Cell phone number has been  successfully captured.";
        }
                 return "Cell phone number is incorrectly formatted or does not "
                
                + "contain an international code.";
    }

    /**
     * Ensures that it is a must for the message to not exceed 250 characters.
     */
    public static String checkMessageLength(String message) 
    {
         if (message.length() <= 250)
        {
                 return "Message ready to send.";
        }
         
        int excess = message.length() - 250;
                 return "Message exceeds 250 characters by " + excess
                + "; please reduce the amount.";
            }

    /**
     * Creates the message hash: first two digits of the message ID, the
     * message number, and the first and last words of the message in
     * caps.
     */
    
    
    public String createMessageHash() {
             String firstTwoDigits = messageID.substring(0, 2);
             
                String[] words = message.trim().split("\\s+");
                 String firstWord = words[0].toUpperCase();
                 
                 String lastWord = words[words.length - 1].toUpperCase();
                 
            return firstTwoDigits + ":" + messageNumber + ":"
                + firstWord + lastWord;
             }

    /**
     * Allows the user to send or store the message.

     */
    public String sentMessage(int choice) 
            {
        switch (choice) 
            {
            case 1:
                totalMessagesSent++;
                
                SENT_MESSAGES.add   (this);
                     return 
                             "Message successfully sent.";
            case 2:
                     return 
                             "Press 0 to delete the message.";
            case 3:
                      return 
                              "Message successfully stored.";
            default:
                     return 
                             "Invalid option.";
             }
             }

    /**
     * Returns full details of all messages sent when the program
     * was been running.
     */
    public String printMessages() 
            {
        StringBuilder output = new StringBuilder();
        for (Message sent : SENT_MESSAGES) 
            {
            output.append("Message ID: ").append(sent.messageID)
                    
                    .append("\nMessage Hash: ").append(sent.messageHash)
                            
                 .append("\nRecipient: ").append(sent.recipient)
                    .append("\nMessage: ").append(sent.message)
            .append("\n\n");
            }
        return      output.toString();
            }

    /** Brings back  the total number of messages sent. */
    
        public static int returnTotalMessages() 
    {
                 return totalMessagesSent;
    }

    /**
     * Stores all sent messages in a JSON file called messages.json in
     * the project folder.
     */
        
        
    public void storeMessage()
            {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < SENT_MESSAGES.size(); i++) 
            {
             Message m = SENT_MESSAGES.get(i);
        json.append("  {\n")
                    .append("    \"messageID\": \"").append(m.messageID)
                    .append("\",\n")
                
                    .append("    \"messageHash\": \"").append(m.messageHash)
               .append("\",\n")
                          .append("    \"recipient\": \"").append(m.recipient)
                    .append("\",\n")
               .append("    \"message\": \"")
                    .append(m.message.replace("\"", "'"))
                        .append("\"\n")
            .append("  }");
                 json.append(i < SENT_MESSAGES.size() - 1 ? ",\n" : "\n");
                   }
        
        
        json.append("]\n");
        try {
            Files.writeString(Paths.get("messages.json"), json.toString());
             } catch (IOException ex)
             {
            System.out.println  ("Could not store message: " + ex.getMessage());
                }
             }

    public String getMessageID()
    {
                return messageID;
    }

    public String getRecipient()
    {
                 return recipient;
    }

    public String getMessage() 
    {
                return message;
    }

    public String getMessageHash()
    {
                return messageHash;
    }

    public int getMessageNumber()
    {
                return messageNumber;
    }
}
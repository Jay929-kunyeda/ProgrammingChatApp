package com.mycompany.prog5121.poe.part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @BeforeEach
    public void setUp() {
        // Initialize test data required by Rubric Table 3
        List<Message> testData = List.of(
            new Message("MSG001", "Developer", "+27834557896", "Did you get the cake?", "Sent", "HASH001"),
            new Message("MSG002", "Developer", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "HASH002"),
            new Message("MSG003", "Developer", "+27834484567", "Yohoooo, I am at your gate.", "Disregard", "HASH003"),
            new Message("0838884567", "Developer", "0838884567", "It is dinner time !", "Sent", "HASH004"),
            new Message("MSG005", "Developer", "+27838884567", "Ok, I am leaving without you.", "Stored", "HASH005")
        );
        MessageManager.populateArrays(testData);
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        List<String> expectedSent = List.of("Did you get the cake?", "It is dinner time !");
        assertEquals(expectedSent, MessageManager.sentMessages);
    }

    @Test
    public void testDisplayLongestMessage() {
        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expectedLongest, MessageManager.getLongestMessage());
    }

    @Test
    public void testSearchForMessageID() {
        String result = MessageManager.searchByMessageId("0838884567");
        assertTrue(result.contains("0838884567"));
        assertTrue(result.contains("It is dinner time !"));
    }

    @Test
    public void testSearchAllMessagesByRecipient() {
        List<String> results = MessageManager.searchByRecipient("+27838884567");
        assertEquals(2, results.size());
        assertTrue(results.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(results.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        String deleteResponse = MessageManager.deleteMessageByHash("HASH002");
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", deleteResponse);

        // Assert message no longer exists
        for (Message msg : MessageManager.allMessages) {
            assertNotEquals("HASH002", msg.getMessageHash());
        }
    }

    @Test
    public void testDisplayReport() {
        String report = MessageManager.displayReport();
        assertNotNull(report);
        assertTrue(report.contains("HASH001"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
    }
}
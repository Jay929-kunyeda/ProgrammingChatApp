package com.mycompany.prog5121poe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Message class using the test data specified in the
 * PROG5121 PoE brief (Part 2).
 */
public class MessageTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        // Uses the fixed-ID constructor so results are predictable
        message = new Message("0012345678", "+27718693002",
                "Hi Mike, can you join us for dinner tonight?", 0);
    }

    // ---------- checkMessageID ----------
    @Test
    public void testCheckMessageIDNotMoreThanTenCharacters() {
        assertTrue(Message.checkMessageID("0012345678"));
    }

    @Test
    public void testCheckMessageIDMoreThanTenCharacters() {
        assertFalse(Message.checkMessageID("00123456789"));
    }

    // ---------- checkRecipientCell (assertEquals) ----------
    // Test data: +27718693002
    @Test
    public void testCheckRecipientCellCorrectlyFormatted() {
        assertEquals("Cell phone number successfully captured.",
                Message.checkRecipientCell("+27718693002"));
    }

    // Test data: 08575975889
    @Test
    public void testCheckRecipientCellIncorrectlyFormatted() {
        assertEquals("Cell phone number is incorrectly formatted or does "
                + "not contain an international code.",
                Message.checkRecipientCell("08575975889"));
    }

    // ---------- checkMessageLength (assertEquals) ----------
    @Test
    public void testMessageWithinTwoHundredFiftyCharacters() {
        String shortMessage = "Hi Mike, can you join us for dinner tonight?";
        assertEquals("Message ready to send.",
                Message.checkMessageLength(shortMessage));
    }

    @Test
    public void testMessageExceedsTwoHundredFiftyCharacters() {
        String longMessage = "a".repeat(260);
        assertEquals("Message exceeds 250 characters by 10; please reduce "
                + "the size.", Message.checkMessageLength(longMessage));
    }

    // ---------- createMessageHash ----------
    @Test
    public void testCreateMessageHashFormat() {
        // ID 0012345678 -> "00", message 0, first word HI, last word TONIGHT
        assertEquals("00:0:HITONIGHT", message.createMessageHash());
    }

    // ---------- sentMessage ----------
    @Test
    public void testSentMessageSend() {
        assertEquals("Message successfully sent.", message.sentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        assertEquals("Press 0 to delete the message.",
                message.sentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        assertEquals("Message successfully stored.",
                message.sentMessage(3));
    }

    // ---------- returnTotalMessages ----------
    @Test
    public void testReturnTotalMessagesAccumulates() {
        int before = Message.returnTotalMessages();
        message.sentMessage(1);
        assertEquals(before + 1, Message.returnTotalMessages());
    }
}


package com.mycompany.prog5121.poe.part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class MessageTest

        {

    private Message message;

    @BeforeEach
    
    public void setUp()
    
        {
            
            
      // Uses the fixed-ID constructor so results are predictable
            
        message = new Message
                
        ("0012345678", "+27718693002",
        "Hi Mike, can you join us for dinner tonight?", 0);
        
    }

    // ---------- checkMessageID ----------
    
    
  @Test
    public void testCheckMessageIDNotMoreThanTenCharacters() 
    
              {
        assertTrue(Message.checkMessageID
                  ("0012345678"));
                                          }

    @Test
    public void testCheckMessageIDMoreThanTenCharacters()
    {
        assertFalse(Message.checkMessageID
                   ("00123456789"));
    }

    // ---------- checkRecipientCell (assertEquals) ----------
  
    @Test
    public void testCheckRecipientCellCorrectlyFormatted() {
        assertEquals
                
        ("Cell phone number successfully captured.",
  Message.checkRecipientCell
        ("+27718693002"));
        
                                          }

                     // My Test data 08575975889
    
    @Test
    public void testCheckRecipientCellIncorrectlyFormatted() 
            
                             {
        assertEquals
     ("Cell phone number is incorrectly formatted or does "
    + "not contain an international code.",
                
           Message.checkRecipientCell
     ("08575975889"));
                             }

                    // ---------- checkMessageLength (assertEquals) ----------
    
    
    @Test
    public void testMessageWithinTwoHundredFiftyCharacters() {
        String shortMessage = "Hi Mike, can you join us for dinner tonight?";
        assertEquals
                   ("Message ready to send.",
                Message.checkMessageLength(shortMessage));
                              }

    @Test
    public void testMessageExceedsTwoHundredFiftyCharacters()
   
                        {
        String longMessage = "a".repeat(260);
        assertEquals
                  ("Message exceeds 250 characters by 10; please reduce "
      + "the size.", Message.checkMessageLength(longMessage));
                        }

            package com.mycompany.prog5121.poe.part1; // ---------- createMessageHash ----------
    
    
    @Test
    public void testCreateMessageHashFormat() 
                    
                {
        
        assertEquals
                    ("00:0:HITONIGHT", message.createMessageHash());
    }

              // ---------- sentMessage ----------
    @Test
            public void testSentMessageSend() 
            
                    {
        assertEquals
                    ("Message successfully sent.", message.sentMessage(1));
                                     }

    @Test
    public void testSentMessageDisregard() 
    
    {
        assertEquals
                 
                    ("Press 0 to delete the message.",
                      message.sentMessage(2));
    }

    @Test
    public void testSentMessageStore()
                    
    {
        assertEquals
                    ("Message successfully stored.",
                message.sentMessage(3));
                         }

            // ---------- returnTotalMessages ----------
   
    
 @Test
    public void testReturnTotalMessagesAccumulates()
                      {
              int before = Message.returnTotalMessages();
        message.sentMessage(1);
        assertEquals(before + 1, Message.returnTotalMessages());
             
                      }
            }
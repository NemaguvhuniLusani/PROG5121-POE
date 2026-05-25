/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.sendmessagep2;

import java.util.Random;
import java.lang.reflect.Method;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.sendmessagep2.SendMessageP2;

/**
 *
 * @author Lusani
 */
public class SendMessageP2Test {
    
    //Test Data for Task1
 @Test
 public void testMsgLengthSuc()
 {
    String msg = "Hi Mike, can you join us for dinner tonight?";
    String results;
    
    if(msg.length() <=250)
    {
      results = "Message ready to send";
    }
    else
    {
        int over = msg.length()-250;
        results = "Message exceeds 250 characters by X[enter number here]; please reduce the size.";
    }
    
    assertEquals("Message ready to send",results);
 }
 
 @Test
 public void testRecipientNumSuc()  {
     String results = SendMessageP2.checkRecipientcell("+27718693002");
     
     assertEquals("Cell phone number successfully captured.", results);
 }
 
 
@Test
public void testMessageHashCorr() 
{
    String results = SendMessageP2.createMessageHash("003243434", 0, "HI TONIGHT");
   assertEquals("00:0:HITONIGHT", results);
}
 
@Test
public void testMessageID()
{
    Random newNum = new Random();
    
    StringBuilder messageID = new StringBuilder();
    
    for(int i = 0; i <10; i++)
    {
        messageID.append(newNum.nextInt(10));
    }
     String results = "<"+ messageID +">"; 
        
       assertEquals(10, messageID.length());
        
}
@Test
public void testMessageSent() throws Exception
{
    String input = "1\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    SendMessageP2 obj = new SendMessageP2();
    
   String option = "1. Send Message";
   
   Method method = SendMessageP2.class.getDeclaredMethod("sentMessages", String.class,  String.class,  String.class);
  method.setAccessible(true);
   String results = (String) method.invoke(null, "0057348759", "Hi Mike, can you join us for dinner tonight?", "00:0HITONIGHT");
   
   
   assertEquals("Message successfully sent", results);
 
}
 
//Test Data for Message 2
 @Test
 public void testMsg2()
 {
    String msg = "Hi Keegan, did you recieve the payment?";
    String results;
    
    if(msg.length() <=250)
    {
      results = "Message ready to send";
    }
    else
    {
        int over = msg.length()-250;
        results = "Message exceeds 250 characters by X[enter number here]; please reduce the size.";
    }
    
    assertEquals("Message ready to send",results);
 }

@Test
 public void testRecipientNumFail()  
 {
     String results = SendMessageP2.checkRecipientcell("08575975889");
     
     assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", results);
 }
 
 @Test
 public void testMessageDis() throws Exception {
     String input = "0\n";
     System.setIn(new ByteArrayInputStream(input.getBytes()));
     
     Method method = SendMessageP2.class.getDeclaredMethod("sentMessages", String.class, String.class, String.class);
     method.setAccessible(true);
     
     String results = (String) method.invoke(null, "49523424", "Hi Keegan, did you recieve the payment?", "00:0HITONIGHT");
     assertEquals("Message deleted successfully", results);
     
 }
 
 
 


}

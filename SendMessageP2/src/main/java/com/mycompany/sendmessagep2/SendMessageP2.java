/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sendmessagep2;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Lusani
 */
public class SendMessageP2 {
    
   static int messageNum = 0;
    static String sentMessages = "";
  
    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
    

    int option = 0;

    //A while loop that allows the user to input the one of the correct option
while(option !=3)   
{
   System.out.println("Welcome To QuickChat");
    System.out.println("1. Send a Message");
    System.out.println("2. Show recently sent message");
    System.out.println("3. Quit");
    System.out.println("Select the Option of Your Choice");
     option = myInput.nextInt(); 
     myInput.nextLine();
    
    //Options of what the user wants to do based on their selection on this if statement
    if(option == 1)
    {
        while(true)
      {
      System.out.println("You want to Send a Message");
      
   Random newNum = new Random();
      StringBuilder messageID = new StringBuilder();
      
      //Randomizing the numbers
      for (int i = 0; i<10; i++)
      {
          messageID.append
        (newNum.nextInt(10));
      }
        System.out.println("MessageID : " + messageID);
     
          System.out.println("Please Enter the Recipient's cellphone Number");
            System.out.println("Please Note that the Cellphone Number must : \n"
            + "- contains the international country code (+27)\n"
            + "followed by the number, which is no more than ten characters long");
          String reccellphoneNum;
          String results;
         
          //while loop that allows the user to input until the condion is followed
          while(true)
          {
            reccellphoneNum = myInput.next();
          System.out.println(checkRecipientcell(reccellphoneNum));
          
         
            if(checkRecipientcell(reccellphoneNum).equals("Cell phone number successfully captured"))
              {
                  break;
              }
           System.out.println("Please Try Again!");
          }
          
  //Prompting the user to enter the message
       System.out.println("Enter your message: ");
       myInput.nextLine();
       String message = myInput.nextLine();
  
       //An if statement that returns an appropriate message based users input for message
       if (message.length() > 250) 
          {
           System.out.println("Your Message Has exceeded 250 Characters!");
           return; 
          }
        
       //Message counter
         messageNum++;
           
         //A creation of a hash map
         String hash = createMessageHash(messageID.toString(), messageNum, message);
         System.out.println("The Message Hash " + hash);
         System.out.println(sentMessages(messageID.toString(), message, hash));
             
         //Printing out the message counted
         System.out.print("Message Number : " + returnTotalMessages());
         
         System.out.println();
          
          System.out.println("Do you want to send another message?");
          System.out.println("1. Yes");
          System.out.println("2. No");
          int anotherMsg = myInput.nextInt();
//An if statement that allows the user to send another message if they select an option of sending another one
      myInput.nextLine();
          if(anotherMsg == 2) 
          {
                  {
                      break;
                  }
          
          } 
        continue;     
      
      }
       
    }      
         //Continuation of Options of what the user wants to do based on their selection on if statement
             else if (option == 2)
           {
              System.out.println("Show recently sent message");
              
              
              if (sentMessages.isEmpty())
               {
                  System.out.println("No mesage sent!");
               }
              else
               {
                  System.out.println("Recenlty messages : " + printMessages());
               }
           
           }
              else if (option == 3)
           {
             System.out.println("Quit");
           } 
             else
           {
              System.out.println("OPTION NOT AVAILABLE, Try Another Option!");
           }    
               
    }
}

    
  
  
       //Boolean method that validates the user's message ID         
      public static boolean checkMessageID(String messageID)
      {
          return messageID.length() <=10;
      }
      
       //String Method that validates or check the Recipient cellphone Number if followed the condition given
        public static String checkRecipientcell(String reccellphoneNum)
        {
          if (reccellphoneNum.startsWith("+27") && reccellphoneNum.length() ==12 ) 
            {    
              return "Cell phone number successfully captured."; 
            }
           else
           {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
           }
        }
     
      //String method that creates the messageHash using a StringBuilder
        public static String createMessageHash(String messageID, int messageNum, String message)
        {
            StringBuilder sb = new StringBuilder();
            String firstTwo = messageID.substring(0, 2);
            
            String[] words = message.split(" ");
            String firstWord = words[0];
            String lastWord = words[words.length - 1];
          
            if(words.length == 1)
            {
            return firstTwo + ":" + messageNum + ":" + firstWord;
            }
            else
            {
                return firstTwo + ":" + messageNum + ":" + firstWord + lastWord;
            }
              
            
        }
      //String Method that is going to send,store or disregard messages
      public static String sentMessages(String messageID, String message, String hash)
      {
          Scanner myInput = 
                  new Scanner(System.in);
       while(true)
        {
          System.out.println("Message Options");
          System.out.println("1. Send Message");
          System.out.println("2. Store Message");
          System.out.println("0. Disregard Message");
          
          int msgOpt = myInput.nextInt();
          
          myInput.nextLine();
          
          // An if statement which returns an accurate message based on the user's input for options
          if (msgOpt == 1) 
            {
                sentMessages = sentMessages + message;
              return "Message successfully sent";
            }
           else if  (msgOpt == 2)
            {
                sentMessages = sentMessages + message;
            return storeMessages(messageID, message, hash);  
              
            }
           else if (msgOpt == 0)
            {
                sentMessages = "";
             return "Message deleted successfully";
            }
           else
           {
             System.out.println("OPTION NOT AVAILABLE, Try Another Option!");
           }
         
        }
      }
      
      //Method that returns all messages written by the user
     public static String printMessages() 
     {
         
         if(sentMessages.isEmpty())
         {
             return "No messages Entered and sent";
         }
         else
         {
           return sentMessages;  
         }
         
     }
     
     //Method that counts the total number of messages sent using a message counter and then returns it
     public static int returnTotalMessages() 
     {
      return messageNum;   
     }
      
 //Method that stores all messages on JSON file
     public static String storeMessages(String messageID, String message, String hash)
     {
       try  
       {
           FileWriter writer = new FileWriter("messages.json", true);
         
           writer.write("Message ID : " + messageID);
           
          writer.write(System.lineSeparator());
          
          writer.write("Recipient Number : " );
          
           writer.write(System.lineSeparator());
          
           writer.write("Message : " + message);
           
            writer.write(System.lineSeparator());
            
            writer.write("Hash : " + hash);
           
            writer.write(System.lineSeparator());
            
           writer.write("---------------------");
     
           writer.write(System.lineSeparator());
         
         writer.close();
        
        return "Message Stored successfully"; 
       }     
     catch(IOException e)  
     {
         return "Message Not Stored";
     }
     } 
         
       }
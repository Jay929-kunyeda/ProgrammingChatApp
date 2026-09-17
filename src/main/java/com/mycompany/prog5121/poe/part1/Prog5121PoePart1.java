package com.mycompany.prog5121.poe.part1;

 
/**
 *
 * @author tinas
 */
import java.util.Scanner;

public class Prog5121PoePart1 {

    public static void main(String[] args) 
    {
 try (Scanner scanner = new Scanner(System.in)) {

    System.out.println("======================================");
            System.out.println(" Registration and Login Application");
    System.out.println("======================================");

           System.out.println
        ("\n--- Registration ---");

            System.out.print
        ("Enter your first name: ");
                String firstName = scanner.nextLine();

            System.out.print
        ("Enter your last name: ");
            String lastName = scanner.nextLine();

            System.out.print
        ("Enter a username: ");
            String username = scanner.nextLine();

            System.out.print
        ("Enter a password: ");
                     String password = scanner.nextLine();

            System.out.print
        
        ("Enter your cellphone number, "
                    + "for example +27838968976: ");
            String cellPhone = scanner.nextLine();

            Login user = new Login(
                     firstName,
                         lastName,
                    username,
                     password,
                          cellPhone
            );
            

            String registrationMessage = user.registerUser();

     if (user.isRegistered()) 
     {
           System.out.println("\nUsername successfully captured.");
        System.out.println("Password successfully captured.");
              System.out.println("Cell phone number successfully added.");
            }

        System.out.println(registrationMessage);

            if (!user.isRegistered()) 
            {
                System.out.println
        ("\nRegistration was unsuccessful.");
                
                System.out.println
        ("Please correct the details and try again.");
                return;
            }

            System.out.println
        ("\n--- Login ---");

            System.out.print
        ("Enter your username to login: ");
                 String loginUsername = scanner.nextLine();

            System.out.print
        ("Enter your password to login: ");
        String loginPassword = scanner.nextLine();

     boolean loginSuccessful = user.loginUser(
                    loginUsername,
                          loginPassword
            );

   System.out.println(user.returnLoginStatus(loginSuccessful));
        }
    }
}
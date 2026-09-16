/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121.poe.part1;

import java.util.Objects;

/**
 *
 * @author tinas
 */
public class Login {
    
    
    private static final String USERNAME_ERROR
         = "Username isnt correctly formatted; please ensure that your username "
           + "contains an underscore and does not exceed more than five characters in length.";

    private static final String PASSWORD_ERROR
          = "Password is not correctly formatted; please ensure that the password "
          + "Must contains at least eight characters, a capital letter, a number, "
          + "plus a special character.";

    private static final String CELL_PHONE_ERROR
            
       = "Cell phone number is incorrectly formatted or does not contain "
       + "an international code.";

        private String firstName;
        private String lastName;
         private String username;
         private String password;
        private String cellPhone;

   private boolean registered;
   private boolean lastLoginSuccessful;

    /**
     *  This my first constructor.
     */
    public Login() {
            this.firstName = "";
        this.lastName = "";
            this.username = "";
            this.password = "";
            this.cellPhone = "";
    }

    /**
     * My Constructor will be used to create a user with registration details.
     *
     *   @param firstName the user's first name
     * 
     * @param lastName the users last name
     *  @param username the users username
     *      @param password the users password
     * 
     *     @param cellPhone the users cellphone number
     */
    public Login(String firstName, String lastName, String username,
            String password, String cellPhone) {

        setFirstName(firstName);
            setLastName(lastName);
                setUsername(username);
               setPassword(password);
         setCellPhone(cellPhone);
    }

    /**
     * Checks whether the username entered contains an underscore and does not exceed more
     * than five characters.
     * @return true if the username is correctly formatted
     */
    
    
        public boolean checkUserName() {
            return username.length() <= 5
                 && username.contains("_");
    }

    /**
     * This must checks whether the password entered meets the required rules i placed
     * There must at least be eight characters, a capital letter, a number and a special
     * character.
     *
     *Needs to @return true if the password entered is correctly formatted
     * @return 
     */
            public boolean checkPasswordComplexity()
            {
                
             return password.matches(".*\\d.*")
                      && password.matches(".*[A-Z].*")
                     && password.length() >= 8
                     && password.matches(".*[^A-Za-z0-9\\s].*");
    }

    /**
     * The following must checks whether the cellphone number starts with an international code
     * and does not contains not more than ten digits after the country code has been entered.
     *
     * i folowed the example fromRegex syntax ,heres the reference:
     * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html
     *
     *Must be able to @return true if the cellphone number is correctly formatted
     * @return 
     */
   
            
         public boolean checkCellPhoneNumber() {
             
                String cellPhoneRegex =  "^\\+\\d{1,3}\\d{1,10}$";
             return cellPhone.matches   (cellPhoneRegex);
    }

    /**
     * My registerUser must validate all registration details.
     *
     * @return a fitting  registration message
     */
         
          public String registerUser() {
              registered = false;

             if (!checkUserName()) {
              return USERNAME_ERROR;
                                  }

              if (!checkPasswordComplexity()) {
              return PASSWORD_ERROR;
                                  }

                if (!checkCellPhoneNumber()) {
                 return CELL_PHONE_ERROR;
        }

                 registered = true;
                 return "User has been successfully registered.";
    }

    /**
     * My LoginUser must be able to compare the entered login details with the stored registration details.
     *
     * @param enteredUsername is the username entered during login phase
     * @param enteredPassword is the password entered during login phase
     * @return true if/ when the login details match accordingly
     */
          
         public boolean loginUser(String enteredUsername, String enteredPassword)
    {
        lastLoginSuccessful = Objects.equals (password, enteredPassword)
                && Objects.equals(username, enteredUsername);

                return lastLoginSuccessful;
    }

    /**
     * returnLoginStatus must be able to return the correct login status message.
     *
     * @param loginStatus is the final result of a login attempt
     * @return Either a welcome or error message will pop up
     */
             public String returnLoginStatus(boolean loginStatus) {
         if (loginStatus) {
       
             return lastName + " IT IS SUCH A PLEASURE TO SEE YOU AGAIN."
               + "Welcome " + firstName + ", ";
        }

        return "Username or PASSWORD IS INCORRECT, please try again.";
    }

    /**
     * The LoginUser is responsible to return the result of the latest login attempt.
     *
     * @returnit will return  true if the last login attempt was successful
     */
        public boolean loginUser()
        {
              return lastLoginSuccessful;
    }

    /**
     * returnLoginStatus will return a status message for the latest login attempt.
     *
     * @return the latest login status message
     */
    public String returnLoginStatus() {
        return returnLoginStatus(lastLoginSuccessful);
                                     }

    public boolean isRegistered() {
             return registered;
                                    }

    public void setFirstName(String firstName) {
             this.firstName = cleanValue(firstName);
                                  }

    public void setLastName(String lastName) {
            this.lastName = cleanValue(lastName);
                                             }

    public void setUsername(String username) {
                 this.username = cleanValue(username);
                                        }

    public void setPassword(String password) {
                this.password = cleanValue(password);
                                  }
    
    public void setCellPhone(String cellPhone) {
                this.cellPhone = cleanValue(cellPhone);
                                            }
    
    private String cleanValue(String value) {
               return value == null ? "" : value.trim();
                         

    }
package com.mycompany.prog5121.poe.part1;

import java.util.Objects;

/**
 *
 * @author tinas
 */
public class Login {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhone;

    private boolean registered;
    private boolean lastLoginSuccessful;

    public Login() {
        this("", "", "", "", "");
    }

    public Login(String firstName, String lastName,
                 String username, String password, String cellPhone) {
        this.firstName = cleanValue(firstName);
        this.lastName = cleanValue(lastName);
        this.username = cleanValue(username);
        this.password = cleanValue(password);
        this.cellPhone = cleanValue(cellPhone);
    }

    // ---------- validation (uses the fields) ----------

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*\\d.*")
                && password.matches(".*[^A-Za-z0-9\\s].*");
    }

    /*
     * Regex reference:
     *      https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html
     */
    public boolean checkCellPhoneNumber() {
        return cellPhone.matches("^\\+\\d{1,3}\\d{1,10}$");
    }

    // ---------- registration ----------

    public String registerUser() {
        registered = false;

        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that "
                    + "your username contains an underscore and is no more "
                    + "than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that "
                    + "the password contains at least eight characters, "
                    + "a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number is incorrectly formatted or does not "
                    + "contain an international code.";
        }

        registered = true;
        return "User has been successfully registered.";
    }

    // ---------- login ----------

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        lastLoginSuccessful = registered
                && Objects.equals(username, enteredUsername)
                && Objects.equals(password, enteredPassword);
        return lastLoginSuccessful;
    }

    public String returnLoginStatus(boolean loginStatus) {
        if (loginStatus) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    // ---------- setters / getters ----------

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

    public boolean isRegistered() {
        return registered;
    }

    // ---------- overloads used by Prog5121PoePart1 ----------

    public boolean checkUserName(String userName) {
        setUsername(userName);
        return checkUserName();
    }

    public boolean checkPasswordComplexity(String password) {
        setPassword(password);
        return checkPasswordComplexity();
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        setCellPhone(cellNumber);
        return checkCellPhoneNumber();
    }

    public String registerUser(String userName, String password, String cellNumber) {
        setUsername(userName);
        setPassword(password);
        setCellPhone(cellNumber);
        return registerUser();
    }

    public String registerUser(String fName, String lName,
                               String userName, String password, String cellNumber) {
        setFirstName(fName);
        setLastName(lName);
        return registerUser(userName, password, cellNumber);
    }

    // signature used on line 86 of Prog5121PoePart1
    public String returnLoginStatus(String enteredUsername, String enteredPassword,
                                    String fName, String lName) {
        setFirstName(fName);
        setLastName(lName);
        return returnLoginStatus(loginUser(enteredUsername, enteredPassword));
    }

    private String cleanValue(String value) {
        return (value == null) ? "" : value.trim();
    }
}
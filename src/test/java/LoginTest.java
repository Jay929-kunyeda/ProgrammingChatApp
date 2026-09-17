/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author tinas
 */

package com.mycompany.prog5121.poe.part1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LoginTest {

    private Login createValidUser() {
        return new Login(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );
    }

    @Test
    public void validUsernameShouldBeAccepted() {
        Login user = createValidUser();

        assertTrue(user.checkUserName());
    }

    @Test
    public void invalidUsernameShouldBeRejected() {
        Login user = createValidUser();
        user.setUsername("kyle!!!!!!");

        assertFalse(user.checkUserName());
    }

    @Test
    public void validPasswordShouldBeAccepted() {
        Login user = createValidUser();

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void invalidPasswordShouldBeRejected() {
        Login user = createValidUser();
        user.setPassword("password");

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void validCellPhoneShouldBeAccepted() {
        Login user = createValidUser();

        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void invalidCellPhoneShouldBeRejected() {
        Login user = createValidUser();
        user.setCellPhone("08966553");

        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void validRegistrationShouldReturnSuccessMessage() {
        Login user = createValidUser();

        assertEquals(
                "User successfully registered.",
                user.registerUser()
        );
    }

    @Test
    public void successfulLoginShouldReturnTrue() {
        Login user = createValidUser();

        assertTrue(user.loginUser(
                "kyl_1",
                "Ch&&sec@ke99!"
        ));
    }

    @Test
    public void failedLoginShouldReturnFalse() {
        Login user = createValidUser();

        assertFalse(user.loginUser(
                "wrong_username",
                "wrong_password"
        ));
    }

    @Test
    public void successfulLoginShouldReturnCorrectMessage() {
        Login user = createValidUser();

        boolean result = user.loginUser(
                "kyl_1",
                "Ch&&sec@ke99!"
        );

        assertEquals(
                "Welcome Kyle, Smith it is great to see you again.",
                user.returnLoginStatus(result)
        );
    }

    @Test
    public void failedLoginShouldReturnCorrectMessage() {
        Login user = createValidUser();

        boolean result = user.loginUser(
                "wrong_username",
                "wrong_password"
        );

        assertEquals(
                "Username or password incorrect, please try again.",
                user.returnLoginStatus(result)
        );
    }
}
package com.example.orderup.logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserVerificationTest {

    @Test
    public void loginVerificationTest() {
        assertEquals("Email or Password is Empty.", UserVerification.loginVerification("", ""));
        assertEquals("Incorrect Email Format.", UserVerification.loginVerification("12@3@1@23.com", "2123456"));
        assertEquals("Incorrect Email Format.", UserVerification.loginVerification("123@123dotcom", "879789"));
        assertEquals("Email does not exist.", UserVerification.loginVerification("email@not.exist", "correctFormat"));
        assertEquals("Incorrect Password.", UserVerification.loginVerification("admin@email.com", "wrongPassword"));
        assertEquals(null, UserVerification.loginVerification("admin@email.com", "admin"));
    }

    @Test
    public void registrationVerificationTest() {
        assertEquals("Missing Field: Please check you have entered all fields.", UserVerification.registrationVerification("", "", "", "", ""));
        assertEquals("Incorrect Email Format.", UserVerification.registrationVerification("123@123@123.com", "123123", "123123", "123123", "123123"));
        assertEquals("Incorrect Email Format.", UserVerification.registrationVerification("123@123dotcom", "123123", "123123", "123123", "123123"));
        assertEquals("Password and Re-password do not match.", UserVerification.registrationVerification("imcorrect@email.com", "correct", "email", "noPasword", "YesPassword"));
        assertEquals("Password needs to be at least 6 characters long.", UserVerification.registrationVerification("imcorrect@email.com", "correct", "email", "only5", "only5"));
        assertEquals("Your first name is more than 7 character.", UserVerification.registrationVerification("imcorrect@email.com", "moreThan7?", "only5", "correctPass", "correctPass"));
        assertEquals("Your last name is more than 7 character.", UserVerification.registrationVerification("imcorrect@email.com", "only5", "moreThan7", "correctPass", "correctPass"));
        assertEquals("Email already in use.", UserVerification.registrationVerification("admin@email.com", "noSame", "noSame", "donotSame", "donotSame"));
        assertEquals(null, UserVerification.registrationVerification("newAcc@email.com", "newAcc", "ohYeah", "niceOne", "niceOne"));
    }

    @Test
    public void creditCardVerificationTest() {
        assertEquals("Missing Field: Please check you have entered all fields.", UserVerification.creditCardVerification("", "", "", ""));
        assertEquals("Error: Incorrect Card Number Format.", UserVerification.creditCardVerification("donotcare", "noteven16digit", "donotcare", "donotcare"));
        assertEquals("Error: Card is not Visa, American Express or Mastercard.", UserVerification.creditCardVerification("donotcare", "1234567891234567", "donotcare", "donotcare"));
        assertEquals("Error: Card is not Visa, American Express or Mastercard.", UserVerification.creditCardVerification("donotcare", "6234567891234567", "donotcare", "donotcare"));
        assertEquals("Error: Card is not Visa, American Express or Mastercard.", UserVerification.creditCardVerification("donotcare", "7234567891234567", "donotcare", "donotcare"));
        assertEquals("Error: Card is not Visa, American Express or Mastercard.", UserVerification.creditCardVerification("donotcare", "8234567891234567", "donotcare", "donotcare"));
        assertEquals("Error: Incorrect CVC length.", UserVerification.creditCardVerification("donotcare", "2234567891234567", "00", "donotcare"));
        assertEquals("Error: Incorrect CVC length.", UserVerification.creditCardVerification("donotcare", "2234567891234567", "00000", "donotcare"));
        assertEquals("Error: Incorrect Expiry date length.", UserVerification.creditCardVerification("donotcare", "2234567891234567", "000", "01/01/1990"));
        assertEquals("Error: Incorrect Expiry date.", UserVerification.creditCardVerification("donotcare", "2234567891234567", "000", "50/13"));
        assertEquals("Credit Card added.", UserVerification.creditCardVerification("imcorrect@email.com", "2234567891234567", "000", "01/01"));
    }

    @Test
    public void addressVerificationTest() {
        String validStreet = "123 Fake Street";
        String validCity = "Winnipeg";
        String validProvince = "Manitoba";
        String validPostal = "R3A5H4";
        String validAddress = validStreet + ", " + validCity + ", " + validProvince + ", " + validPostal;
        String invalidCity = "Edmonton";
        String invalidProvince = "Alberta";
        String invalidPostal = "G4D3U4";
        String invalidAddress= validStreet + ", " + invalidCity + ", " + invalidProvince + ", " + invalidPostal;;
        String email = "admin@email.com";
        assertEquals(UserVerification.addressVerification(validStreet, validCity, validProvince, validPostal, email, validAddress), "Address added.");
        assertEquals(UserVerification.addressVerification(validStreet, invalidCity, invalidProvince, invalidPostal, email, invalidAddress), "Error: The city you entered must be located within Manitoba.\n" + "Error: Currently does not support other province other than Manitoba.\n" + "Error: Postal Code not located in Manitoba.\n");

    }

    @Test
    public void emailCheckTest() {
        String validEmail = "admin@email.com";
        String noAt = "adminemail.com";
        String doubleAt = "admin@@email.com";
        String noNot = "admin@emailcom";

        assertEquals(UserVerification.emailCheck(validEmail),true);
        assertEquals(UserVerification.emailCheck(noAt),false);
        assertEquals(UserVerification.emailCheck(doubleAt),false);
        assertEquals(UserVerification.emailCheck(noNot),false);
    }
}
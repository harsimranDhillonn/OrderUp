package com.example.orderup.logic;

import static org.junit.Assert.assertEquals;

import com.example.orderup.persistance.UserPersistence;

import org.junit.Test;

public class UserServicesTest {

    @Test
    public void getFirstName() {
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        String result = "";

        try {
            result = UserServices.getFirstName(existingEmail);
        } catch(UserException e) {
            assertEquals("Couldn't find that email.", e.getMessage());
        }

        assertEquals("admin@email.com", result);

        try {
            result = UserServices.getFirstName(nonexistingEmail);
        } catch(UserException e) {
            assertEquals("Couldn't find that email.", e.getMessage());
        }
        assertEquals(null, result);
    }

    @Test
    public void getLastName() {
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        assertEquals("admin", UserServices.getLastName(existingEmail));
        assertEquals(null, UserServices.getLastName(nonexistingEmail));
    }

    @Test
    public void getAddress() {
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        String newAddress = "123 Main Street";

        userPersistence.updateAddress(newAddress, existingEmail);
        userPersistence.updateAddress(newAddress, nonexistingEmail);
        assertEquals("123 Main Street", UserServices.getAddress(existingEmail));
        assertEquals(null, UserServices.getAddress(nonexistingEmail));
    }

    @Test
    public void getBalance() {
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        float newBalance = 10.00F;
        userPersistence.modifyBalance(existingEmail, newBalance);
        userPersistence.modifyBalance(nonexistingEmail, newBalance);
        assertEquals("10.0", UserServices.getBalance(existingEmail));
        assertEquals(null, UserServices.getBalance(nonexistingEmail));
    }
}
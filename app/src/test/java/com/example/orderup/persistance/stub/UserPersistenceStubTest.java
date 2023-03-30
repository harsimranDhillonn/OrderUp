package com.example.orderup.persistance.stub;

import static org.junit.Assert.assertEquals;

import com.example.orderup.Objects.Giftcard;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;
import com.example.orderup.persistance.UserPersistence;

import org.junit.Test;

public class UserPersistenceStubTest {

    @Test
    public void addUser() {
        String email = "new@email.com";
        String password = "new123";
        String firstName = "Jane";
        String lastName = "Doe";

        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();
        userPersistence.addUser(email,password,firstName,lastName);
        //assertEquals(firstName, UserServices.getFirstName(email));
        assertEquals(lastName, UserServices.getLastName(email));

    }

    @Test
    public void addCreditCard() {
        String email = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        String cardNum = "5555555555555555";
        String cvc = "505";
        String expiry = "06/22";
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();
        userPersistence.addCreditCard(expiry, email,cardNum, cvc);
        assertEquals(cardNum, UserServices.getCredit(email));
        assertEquals(cvc, UserServices.getCvc(email));
        assertEquals(expiry, UserServices.getExpiry(email));

        userPersistence.addCreditCard(nonexistingEmail,cardNum, cvc,expiry);
        assertEquals(null, UserServices.getCredit(nonexistingEmail));
        assertEquals(null, UserServices.getCvc(nonexistingEmail));
        assertEquals(null, UserServices.getExpiry(nonexistingEmail));


    }

    @Test
    public void updateAddress() {
        String email = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        String newAddress = "123 Main Street";
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();

        userPersistence.updateAddress(email, newAddress);
        assertEquals(newAddress, UserServices.getAddress(email));

        userPersistence.updateAddress(nonexistingEmail, newAddress);
        assertEquals(null, UserServices.getAddress(nonexistingEmail));

    }

    @Test
    public void modifyBalance() {
        String email = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        float newBalance = 10.00F;
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();

        userPersistence.modifyBalance(email, newBalance);
        assertEquals("" + newBalance, UserServices.getBalance(email));

        userPersistence.modifyBalance(nonexistingEmail, newBalance);
        assertEquals(null, UserServices.getBalance(nonexistingEmail));
    }

    @Test
    public void getGiftcards() {
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();
        Giftcard[] test = new Giftcard[0];
        assertEquals(test.length, userPersistence.getGiftcards().length);
    }
}
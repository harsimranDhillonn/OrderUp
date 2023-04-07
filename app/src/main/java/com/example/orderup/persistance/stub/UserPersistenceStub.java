package com.example.orderup.persistance.stub;

import com.example.orderup.Objects.Giftcard;
import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

import java.util.HashMap;

//This is a pre-defined user database.
public class UserPersistenceStub implements UserPersistence
{
    private HashMap<String, User> userTable = null;

    public UserPersistenceStub()
    {
        this.userTable = new HashMap<String, User>();

        userTable.put("admin@email.com", new User("admin@email.com", "admin", "admin", "admin"));
        userTable.put("GaryChalmers@email.com", new User("GaryChalmers@email.com", "GaryChalmers", "Gary", "Chalmers"));
        userTable.put("SelmaBouvier@email.com", new User("SelmaBouvier@email.com", "SelmaBouvier", "Selma", "Bouvier"));
        userTable.put("ArniePye@email.com", new User("ArniePye@email.com", "ArniePye", "Arnie", "Pye"));
        userTable.put("MaryBailey@email.com", new User("MaryBailey@email.com", "MaryBailey", "Mary", "Bailey"));
        //add more if needed.
    }

    //Return the user table.
    @Override
    public HashMap<String, User> getUserTable()
    {
        return userTable;
    }

    //Add a new user object to the user table.
    @Override
    public void addUser(String email, String pass, String first, String last)
    {
        if(null != email && null != pass && null != first && null != last) {
            userTable.put(email, new User(first, last, email, pass));
        }
    }

    //Add the credit card info to the user object.
    public void addCreditCard(String email, String cardNum, String cvc, String expiry)
    {
        if(null != userTable.get(email) && null != email && null != cardNum && null != cvc && null != expiry) {
          userTable.get(email).addCreditCard(cardNum, cvc, expiry);
        }
    }

    //Update the user address.
    @Override
    public void updateAddress(String email, String address)
    {
        if(null != userTable.get(email)) {
            userTable.get(email).updateAddress(address);
        }
    }

    //Modify the user account balance.
    @Override
    public void modifyBalance(String email, float balance)
    {
        if(null != userTable.get(email)) {
            userTable.get(email).modifyBalance(balance);
        }
    }

    @Override
    public Giftcard[] getGiftcards() {
        return new Giftcard[0];
    }
}
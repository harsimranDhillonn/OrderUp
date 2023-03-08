package com.example.orderup.Objects;

import java.util.Objects;

public class User {

    private String firstName, lastName, email, password, expiry, address;
    private String creditCard, cvc;

    private int id;

    private float balance;

    public User(String firstName, String lastName, String email, String password) {

        this.firstName= firstName;
        this.lastName= lastName;
        this.email = email;
        this.password = password;
        this.balance = 0.00F;
    }

    public User(int id, String email, String password, String firstName, String lastName, String creditcard, String csv, String expiry, String address, String balance) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName= firstName;
        this.lastName= lastName;
        this.creditCard = creditcard;
        this.cvc = csv;
        this.expiry = expiry;
        this.address = address;
        this.balance = Float.parseFloat(balance);
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getAddress(){
        return address;
    }

    public void updateFirstName(String newFirstName){
        this.firstName= newFirstName;
    }

    public void updateLastName(String newLastName){
        this.lastName= newLastName;
    }

    public void updatePass(String newPassword){
        this.password= newPassword;
    }

    public void updateAddress(String newAddress){
        this.address= newAddress;
    }

    public void addCreditCard(String newCreditCard, String newCvc, String newExpiry){
        this.creditCard= newCreditCard;
        this.cvc= newCvc;
        this.expiry= newExpiry;
    }

    public void addBalance(float balance) {
        this.balance += balance;
    }

    public String getBalance() {
        return String.valueOf(this.balance);
    }

    public String toString(){
        return String.format("First name: %s\n" +
                "Last name: %s\n" +
                "Email: %s\n" +
                "Password: %s\n" +
                "Address: %s", firstName, lastName, email, password, address);
    }

    public boolean equals(Object other) {
        boolean equals = false;

        if (other instanceof User) {
            final User otherStudent = (User) other;
            equals = Objects.equals(this.email, otherStudent.email);
        }

        return equals;
    }
}

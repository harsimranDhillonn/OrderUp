package com.example.orderup.presentation;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.example.orderup.logic.UserData;
import com.example.orderup.persistance.DatabaseHelper;

public class UserRegister extends AppCompatActivity {
    DatabaseHelper myDatabase;

    private String firstname;
    private String lastname;
    private String creditcard;
    private String csv;
    private String expiry;
    private String address;
    private String email;
    private String password;

    EditText firstNameInput;
    EditText lastNameInput;
    EditText cardNumberInput;
    EditText csvInput;
    EditText expiryInput;
    EditText addressInput;
    Button submitButton2;
    Button submitButton3;

    Button submitButton4;

    //private float accountBalance;

    UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        myDatabase = new DatabaseHelper(this);

        Intent intent = getIntent();
        this.email = intent.getStringExtra("email");
        this.password = intent.getStringExtra("password");
        user = new UserData(this.email,this.password);


        firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        cardNumberInput = (EditText) findViewById(R.id.cardNumberInput);
        csvInput = (EditText) findViewById(R.id.csvInput);
        expiryInput = (EditText) findViewById(R.id.expiryInput);
        addressInput = (EditText) findViewById(R.id.addressInput);

        submitButton2 = (Button) findViewById(R.id.submitButton2);
        submitButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstname = firstNameInput.getText().toString();
                lastname = lastNameInput.getText().toString();
                creditcard = cardNumberInput.getText().toString();
                csv = csvInput.getText().toString();
                expiry = expiryInput.getText().toString();
                address = addressInput.getText().toString();
                user.setInfo(firstname, lastname, creditcard, csv, expiry, address);

                boolean isInserted = myDatabase.insertData(email,password,firstname,lastname,creditcard,csv,expiry,address,user.getBalance());
                if(isInserted) {
                    Log.d("this","USER DATA SUCCESSFULLY ADDED");
                    //user.print();
                } else {
                    Log.d("this","USER DATA FAILED TO BE ADDED");
                }
                //showToast(email);
                //showToast(password);
            }
        });

        submitButton4 = (Button) findViewById(R.id.submitButton4);
        submitButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printDatabase();
            }
        });

    };

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void printDatabase() {
        // Print database contents
        Cursor res = myDatabase.getAllData();
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Email :" + res.getString(1) + "\n");
            buffer.append("Password :" + res.getString(2) + "\n");
            buffer.append("First Name :" + res.getString(3) + "\n");
            buffer.append("Last Name :" + res.getString(4) + "\n");
            buffer.append("Credit Card :" + res.getString(5) + "\n");
            buffer.append("CSV :" + res.getString(6) + "\n");
            buffer.append("Expiry :" + res.getString(7) + "\n");
            buffer.append("Address :" + res.getString(8) + "\n");
            buffer.append("Account Balance :$" + res.getString(9) + "\n\n");
        }

        //Show all data
        showMessage("Data",buffer.toString());

    }

}

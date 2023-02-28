package com.example.orderup.presentation;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.example.orderup.logic.UserVerification;
import com.example.orderup.persistance.DatabaseHelper;

public class LoginActivity extends AppCompatActivity
{
    private Button signInButton, registerButton, viewDB;

    private String email, password;

    private EditText emailInput, passwordInput;

    private UserVerification verify;

    DatabaseHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDatabase = new DatabaseHelper(this);

        verify=new UserVerification();

        //Get email and password.
        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        signInButton= (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();


                if(verify.loginVerification(email, password, LoginActivity.this) || null != searchByEmail(email) && checkPassword(email,password))
                {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("email", email);

                    //Start the main page activity.
                    startActivity(intent);

                    //Remove current activity.
                    finish();
                }
            }
        });

        registerButton= (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Start the register page activity.
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                //Remove current activity.
                finish();
            }
        });

        viewDB = (Button) findViewById(R.id.viewDB);
        viewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printDatabase();
            }
        });
    }

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
    public String searchByEmail(String email) {
        String currId = null;
        boolean found = false;
        Cursor res = myDatabase.getAllData();
        while(res.moveToNext() && found == false) {
            if(email.equals(res.getString(1))) {
                found = true;
                currId = res.getString(0);
            }
        }
        return currId;
    }

    public boolean checkPassword(String email, String password) {
        //String currId = null;
        boolean match = false;
        boolean found = false;
        Cursor res = myDatabase.getAllData();
        while(res.moveToNext() && found == false) {
            if(email.equals(res.getString(1))) {
                found = true;
                //currId = res.getString(0);
                if(res.getString(2).equals(password)) {
                    match = true;
                }
            }
        }
        return match;
    }
}


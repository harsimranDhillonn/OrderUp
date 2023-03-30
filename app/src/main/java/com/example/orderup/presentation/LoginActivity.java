package com.example.orderup.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserException;
import com.example.orderup.logic.UserVerification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

//Login UI class.
public class LoginActivity extends AppCompatActivity
{
    private Button signInButton, registerButton;

    private String email, password;

    private EditText emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        copyDatabaseToDevice();

        //Build connection with xml file.
        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        //Event listener of the login button.
        signInButton= (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Get input data from xml file.
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();

                try
                {
                        //Verify the input data with databases. Go to home page if nothing wrong. Will pop up a window if error occurs.
                        String result = UserVerification.loginVerification(email, password);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        //Tell the system who is the current user.
                        Services.setCurrentUser(email);

                        //Start the main activity class.
                        startActivity(intent);

                        //Remove current activity.
                        finish();

                } catch (UserException e) {
                    ErrorPopUp.errorMsg(LoginActivity.this, e.getMessage());
                }
            }
        });

        //Event listener of the register button.
        registerButton= (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Start the register page activity.
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                //Remove current activity.
                finish();
            }
        });
    }

    private void copyDatabaseToDevice()
    {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try
        {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++)
            {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }
            copyAssetsToDirectory(assetNames, dataDirectory);
            Services.setDBPathName(dataDirectory.toString() + "/" + Services.getDBPathName());

        }
        catch (final IOException ioe)
        {
            ErrorPopUp.errorMsg(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException
    {
        AssetManager assetManager = getAssets();

        for (String asset : assets)
        {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);
            if (true)
            {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1)
                {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
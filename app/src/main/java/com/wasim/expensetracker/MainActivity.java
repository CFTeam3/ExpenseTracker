package com.wasim.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import com.wasim.expensetracker.activity.ProfilePageActivity;
import com.wasim.expensetracker.activity.SignUpActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MAIN ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLoginButton();
        setupSignUpButton();
    }

    private void setupLoginButton() {
        Button loginButton = findViewById(R.id.MainActivityLoginButton);

        loginButton.setOnClickListener(view ->  {
            Intent goToProfilePageActivity = new Intent(MainActivity.this, ProfilePageActivity.class);
            startActivity(goToProfilePageActivity);
        });
        Log.v(TAG, "Login Button: ");
    }

    private void setupSignUpButton() {
        Button loginButton = findViewById(R.id.MainActivitySignUpButton);

        loginButton.setOnClickListener(view ->  {
            Intent goToSignUpPageActivity = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(goToSignUpPageActivity);
        });
        Log.v(TAG, "SignUp Button: ");
    }
}
package com.wasim.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import com.amplifyframework.core.Amplify;
import com.wasim.expensetracker.activity.ProfilePageActivity;
import com.wasim.expensetracker.activity.SignUpActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MAIN ACTIVITY";

    Button loginButton;

    EditText passwordEditText;

    EditText emailEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.MainActivityLoginButton);
        passwordEditText = findViewById(R.id.MainActivityPasswordTextView);
        emailEditText = findViewById(R.id.MainActivityEmailTextView);

        setupLoginButton();
        setupSignUpButton();
    }

    void setupLoginButton() {
         loginButton.setOnClickListener(v -> {
            Amplify.Auth.signIn(emailEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    success -> {
                        Log.i(TAG, "Login SUCCEEDED" + success.toString());
                        Intent goToProfilePageActivity = new Intent(MainActivity.this, ProfilePageActivity.class);
                        startActivity(goToProfilePageActivity);
                    },
                    failure -> Log.i(TAG, "Login FAILED" + failure.toString())
            );
        });
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
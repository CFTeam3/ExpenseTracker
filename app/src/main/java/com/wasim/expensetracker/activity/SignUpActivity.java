package com.wasim.expensetracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.wasim.expensetracker.MainActivity;
import com.wasim.expensetracker.R;

public class SignUpActivity extends AppCompatActivity {
    private final String TAG = "SIGNUP ACTIVITY";

    Button signUpNowButton;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText emailEditText;  // Declare EditText for email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpNowButton = findViewById(R.id.SignUpActivitySignUpButton);
        usernameEditText = findViewById(R.id.SignUpActivityCreateUsernamePlainText);
        passwordEditText = findViewById(R.id.SignUpActivityCreatePasswordTextView);
        emailEditText = findViewById(R.id.SignUpActivityEmailEditText);  // Find email EditText by ID


        setupSignUpNowButton();
    }


    void setupSignUpNowButton() {
        signUpNowButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String email = emailEditText.getText().toString();

            Amplify.Auth.signUp(
                    email,
                    password,
                    AuthSignUpOptions.builder()
                            .userAttribute(AuthUserAttributeKey.email(), email)
//                            .userAttribute(AuthUserAttributeKey.preferredUsername(), username)
                            .build(),
                    successResponse -> {
                        Log.i(TAG, "Sign Up SUCCESSFUL" + successResponse);
                        Intent goToVerifyActivity = new Intent(SignUpActivity.this, VerifyActivity.class);
                        startActivity(goToVerifyActivity);
                    },
                    failureResponse -> Log.i(TAG, "Sign Up FAILED" + failureResponse)
            );
        });
    }


}
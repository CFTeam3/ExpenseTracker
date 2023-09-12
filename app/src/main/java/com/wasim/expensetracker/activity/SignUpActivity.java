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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpNowButton = findViewById(R.id.SignUpActivitySignUpButton);
        usernameEditText = findViewById(R.id.SignUpActivityCreateUsernamePlainText);
        passwordEditText = findViewById(R.id.SignUpActivityCreatePasswordTextView);

        setupSignUpNowButton();
    }

    void setupSignUpNowButton() {

        signUpNowButton.setOnClickListener(view -> {

            Amplify.Auth.signUp(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    AuthSignUpOptions.builder()
                            .userAttribute(AuthUserAttributeKey.preferredUsername(), usernameEditText.getText().toString())
                            .build(),
                    successResponse -> {
                        Log.i(TAG, "Sign Up SUCCESSFULL" + successResponse);
                        Intent goToProfilePageActivity = new Intent(SignUpActivity.this, ProfilePageActivity.class);
                        startActivity(goToProfilePageActivity);
                    },
                    failureResponse -> Log.i(TAG, "Sign Up FAILED" + failureResponse)
            );
        });

    }
}
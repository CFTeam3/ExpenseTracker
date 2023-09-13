package com.wasim.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Trip;
import com.wasim.expensetracker.activity.ProfilePageActivity;
import com.wasim.expensetracker.activity.SignUpActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MAIN ACTIVITY";

    Button loginButton;

    EditText passwordEditText;

    EditText emailEditText;
    Button logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.MainActivityLoginButton);
        passwordEditText = findViewById(R.id.MainActivityPasswordTextView);
        emailEditText = findViewById(R.id.MainActivityEmailTextView);
        logoutButton = findViewById(R.id.MainActivityLogoutButton);

        //Adding this to see if the current user is signed in. If so, go to Profile page to stop entering in information every freaking time.
        Amplify.Auth.fetchAuthSession(
                result -> {
                    if (result.isSignedIn()) {  // If the user is signed in
                        Log.i(TAG, "User is signed in, redirecting...");
                        Intent goToProfilePageActivity = new Intent(MainActivity.this, ProfilePageActivity.class);
                        startActivity(goToProfilePageActivity);
                        finish();  // Close MainActivity
                    } else {
                        Log.i(TAG, "User is not signed in.");
                    }
                },
                error -> Log.e(TAG, "Auth session fetch failed: " + error.toString())
        );



        setupLoginButton();
        setupSignUpButton();
        setupLogoutButton();
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

    void setupLogoutButton() {
        logoutButton.setOnClickListener(v -> {
            AuthSignOutOptions signOutOptions = AuthSignOutOptions.builder()
                    .globalSignOut(true)
                    .build();

            Amplify.Auth.signOut(signOutOptions,
                    signOutResult -> {
                        if (signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
                            Log.i(TAG, "Global sign out successful!!!");
                        } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
                            Log.i(TAG, "Partial sign out successful!!!");
                        } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
                            Log.i(TAG, "Logout Failed: " + signOutResult);
                        }
                    }
            );
        });
    }
}
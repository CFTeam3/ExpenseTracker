package com.wasim.expensetracker.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Trip;
import com.wasim.expensetracker.MainActivity;
import com.wasim.expensetracker.R;

public class ProfilePageActivity extends AppCompatActivity {
    private final String TAG = "*** PROFILE PAGE ACTIVITY";
    public final String USERNAME_TAG = "Username";
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //Get the username from sharedpreferences on SignUpActivity.java
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = preferences.getString("USERNAME", "default_value");

        logoutButton = findViewById(R.id.ProfilePageAcitivityLogoutButton);

        setupUsernameTextView(username);
        setupTripsImageButton();
        setupLogoutButton();

//        Trip testTrip = Trip.builder().userId("TestUserID").name("TestTripName").build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(testTrip),
//                success -> {
//                    Log.i(TAG, "Created Test Trip");
//                },
//                failure -> Log.i(TAG, "Test Trip Failed")
//        );
    }

    void setupUsernameTextView(String userName) {
        String addedText = userName;
        ((TextView) findViewById(R.id.ProfilePageActivityUsernameTextView)).setText(addedText);
    }

    private void setupTripsImageButton() {
        ImageButton tripsImageButton = findViewById(R.id.ProfilePageActivityTripsImageButton);

        tripsImageButton.setOnClickListener(view -> {
            Intent goToAllTripsActivityPage = new Intent(ProfilePageActivity.this, AllTripsActivity.class);
            startActivity(goToAllTripsActivityPage);
        });
        Log.v(TAG, "All Trips Image Button: ");
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
                            Intent goToMainActivityPage = new Intent(ProfilePageActivity.this, MainActivity.class);
                            startActivity(goToMainActivityPage);
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
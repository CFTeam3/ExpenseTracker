package com.wasim.expensetracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Trip;
import com.wasim.expensetracker.R;

public class CreateTripActivity extends AppCompatActivity {
    private final String TAG = "*** CREATE TRIP ACTIVITY: ";

    Button cancelButton;

    Button addTripButton;

    EditText tripNameEditText;

    AuthUser authUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        cancelButton = findViewById(R.id.CreateTripActivityCancelButton);
        addTripButton = findViewById(R.id.CreateTripActivityAddTripButton);
        tripNameEditText = findViewById(R.id.CreateTripActivityTextView);

        Amplify.Auth.getCurrentUser(
                success -> {
                    Log.i(TAG, "User authenticated with username: " + success.getUsername());
                    authUser = success;
                    runOnUiThread(() -> {
                    });
                },
                failure -> {
                    Log.i(TAG, "There is no current authenticated user");
                    authUser = null;
                    runOnUiThread(() -> {
                    });
                }
        );

        setupCancelButton();
        setupAddTripButton();
    }

    private void setupCancelButton() {
        cancelButton.setOnClickListener(v -> {
            Intent goToAllTripsActivityPage = new Intent(CreateTripActivity.this, AllTripsActivity.class);
            startActivity(goToAllTripsActivityPage);
        });
    }

    private void setupAddTripButton() {
        addTripButton.setOnClickListener(view -> {

            if (authUser != null) {
                String userId = authUser.getUserId();
                System.out.println("userId: " + userId);

                Trip tripToSave = Trip.builder()
                        .userId(userId)
                        .name(tripNameEditText.getText().toString())
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(tripToSave),
                        successResponse -> Log.i(TAG, "CreateTripActivity.setUpAddTripButton(): created trip successfully"),
                        failureResponse -> Log.i(TAG, "CreateTripActivity.setUpAddTripButton(): failure response " + failureResponse)
                );
            } else {
                Log.e(TAG, "Trip failed to build");
            }
            Intent goToAllTripsActivityPage = new Intent(CreateTripActivity.this, AllTripsActivity.class);
            startActivity(goToAllTripsActivityPage);
            Toast.makeText(CreateTripActivity.this, "Trip saved!", Toast.LENGTH_SHORT).show();
        });
    }
}
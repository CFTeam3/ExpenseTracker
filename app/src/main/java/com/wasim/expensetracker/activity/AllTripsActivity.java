package com.wasim.expensetracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Trip;
import com.wasim.expensetracker.R;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.amplifyframework.api.graphql.model.ModelQuery;
import java.util.ArrayList;
import java.util.List;


public class AllTripsActivity extends AppCompatActivity {
    private final String TAG = "*** ALL TRIP ACTIVITY: ";

    AuthUser authUser;


    Button viewTripButton;
    Button backButton;
    Button createNewTripButton;
    Spinner allTripsSpinner;

    ArrayList<Trip> userTrips = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);

        viewTripButton = findViewById(R.id.AllTripActivityViewTripButton);
        backButton = findViewById(R.id.AllTripsActivityBackButton);
        createNewTripButton = findViewById(R.id.AllTripsActivityCreateNewTripButton);
        allTripsSpinner = findViewById(R.id.AllTripsActivitySpinner);




        Amplify.Auth.getCurrentUser(
                authUser -> {
                    String currentUserId = authUser.getUserId();
                    Amplify.API.query(
                            ModelQuery.list(Trip.class, Trip.USER_ID.eq(currentUserId)),
                            response -> {
                                for (Trip trip : response.getData()) {
                                    userTrips.add(trip);
                                }
                                ArrayList<String> tripNames = new ArrayList<>();
                                for (Trip userTrip : userTrips) {
                                    tripNames.add(userTrip.getName());
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tripNames);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                runOnUiThread(() -> allTripsSpinner.setAdapter(adapter));
                            },
                            error -> Log.e(TAG, "Could not query DataStore", error)
                    );
                },
                authError -> Log.e(TAG, "User is not authenticated")
        );





        setupViewTripButton();
        setupBackButton();
        setupCreateTripButton();
    }


    private void setupViewTripButton() {
        viewTripButton.setOnClickListener(v -> {
            Trip selectedTrip = (Trip) allTripsSpinner.getSelectedItem();
            Intent goToTripDetailsActivityPage = new Intent(AllTripsActivity.this, TripDetailsActivity.class);

           goToTripDetailsActivityPage.putExtra("SELECTED_TRIP_ID", selectedTrip.getId());
            goToTripDetailsActivityPage.putExtra("SELECTED_TRIP_NAME", selectedTrip.getName());

            startActivity(goToTripDetailsActivityPage);
        });
    }


    private void setupBackButton() {
        backButton.setOnClickListener(v -> {
            Intent goToProfileActivityPage = new Intent(AllTripsActivity.this, ProfilePageActivity.class);
            startActivity(goToProfileActivityPage);
        });
    }

    private void setupCreateTripButton() {
        createNewTripButton.setOnClickListener(v -> {
            Intent goToCreateTripActivityPage = new Intent(AllTripsActivity.this, CreateTripActivity.class);
            startActivity(goToCreateTripActivityPage);
        });
    }
}
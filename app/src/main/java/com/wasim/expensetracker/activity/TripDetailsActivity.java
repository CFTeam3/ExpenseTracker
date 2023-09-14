package com.wasim.expensetracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wasim.expensetracker.R;

public class TripDetailsActivity extends AppCompatActivity {
    private final String TAG = "*** TRIP DETAILS ACTIVITY: ";

    TextView tripDetailsHeader;

    Button addExpenseButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        addExpenseButton = findViewById(R.id.TripDetailsActivityAddExpenseButton);
        backButton = findViewById(R.id.TripDetailsActivityBackButton);

        tripDetailsHeader = findViewById(R.id.TripDetailsActivityHeader);
        // Get the selected trip name from the Intent
        String selectedTripName = getIntent().getStringExtra("SELECTED_TRIP_NAME");
        String headerText = selectedTripName + " Summary";
        tripDetailsHeader.setText(headerText);

        fetchAndDisplayExpenses(selectedTripName);
        setupAddExpenseButton();
        setupBackButton();
    }

    private void fetchAndDisplayExpenses(String tripName) {
        // Need to get expenses now.
    }

    private void setupAddExpenseButton() {
        addExpenseButton.setOnClickListener(v -> {
            String selectedTripName = tripDetailsHeader.getText().toString().replace(" Summary", "");
            Intent goToAddExpenseActivity = new Intent(TripDetailsActivity.this, AddExpenseActivity.class);
            goToAddExpenseActivity.putExtra("SELECTED_TRIP_NAME", selectedTripName);
            startActivity(goToAddExpenseActivity);
        });
    }


    private void setupBackButton() {
        backButton.setOnClickListener(v -> {
            Intent goToAllTripsActivityPage = new Intent(TripDetailsActivity.this, AllTripsActivity.class);
            startActivity(goToAllTripsActivityPage);
        });
    }
}
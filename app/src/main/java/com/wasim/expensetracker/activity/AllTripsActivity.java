package com.wasim.expensetracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wasim.expensetracker.R;

public class AllTripsActivity extends AppCompatActivity {

    Button backButton;
    Button createNewTripButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);

        backButton = findViewById(R.id.AllTripsActivityBackButton);
        createNewTripButton = findViewById(R.id.AllTripsActivityCreateNewTripButton);

        setupBackButton();
        setupViewTripButton();
    }

    private void setupBackButton() {
        backButton.setOnClickListener(v -> {
            Intent goToProfileActivityPage = new Intent(AllTripsActivity.this, ProfilePageActivity.class);
            startActivity(goToProfileActivityPage);
        });
    }

    private void setupViewTripButton() {
        createNewTripButton.setOnClickListener(v -> {
            Intent goToCreateTripActivityPage = new Intent(AllTripsActivity.this, CreateTripActivity.class);
            startActivity(goToCreateTripActivityPage);
        });
    }
}
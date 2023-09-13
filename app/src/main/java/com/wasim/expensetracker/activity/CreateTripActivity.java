package com.wasim.expensetracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wasim.expensetracker.R;

public class CreateTripActivity extends AppCompatActivity {

    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        cancelButton = findViewById(R.id.CreateTripActivityCancelButton);

        setupCancelButton();
    }

    private void setupCancelButton() {
        cancelButton.setOnClickListener(v -> {
            Intent goToAllTripsActivityPage = new Intent(CreateTripActivity.this, AllTripsActivity.class);
            startActivity(goToAllTripsActivityPage);
        });
    }

}
package com.wasim.expensetracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.wasim.expensetracker.R;

public class ProfilePageActivity extends AppCompatActivity {
    private final String TAG = "PROFILE PAGE ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        setupTripsImageButton();
    }

    private void setupTripsImageButton() {
        ImageButton tripsImageButton = findViewById(R.id.ProfilePageActivityTripsImageButton);

        tripsImageButton.setOnClickListener(view ->  {
            Intent goToAllTripsActivityPage = new Intent(ProfilePageActivity.this, AllTripsActivity.class);
            startActivity(goToAllTripsActivityPage);
        });
        Log.v(TAG, "All Trips Image Button: ");
    }
}
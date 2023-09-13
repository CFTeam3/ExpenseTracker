package com.wasim.expensetracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.wasim.expensetracker.R;

public class AddExpenseActivity extends AppCompatActivity {
    private final String TAG = "*** ADD EXPENSE ACTIVITY: ";

    String selectedTripName; //need to look into using ID instead of name unless dynamo can see name to update as well

    EditText descriptionEditText;
    EditText amountNumberEditText;
    Button addThisExpenseButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        selectedTripName = getIntent().getStringExtra("SELECTED_TRIP_NAME");

        descriptionEditText = findViewById(R.id.AddExpenseActivityDescriptionPlainText);
        amountNumberEditText = findViewById(R.id.AddExpenseActivityAmountNumberTextView);
        addThisExpenseButton = findViewById(R.id.AddExpenseActivityAddThisExpenseButton);
        cancelButton = findViewById(R.id.AddExpenseActivityCancelButton);




        setupAddThisExpenseButton();
        setupCancelButton();

    }


    private void setupAddThisExpenseButton() {
        addThisExpenseButton.setOnClickListener(view -> {


        });
    }

    private void setupCancelButton() {

        cancelButton.setOnClickListener(v -> {
            Intent goToTripDetailsActivityPage = new Intent(AddExpenseActivity.this, TripDetailsActivity.class);
            goToTripDetailsActivityPage.putExtra("SELECTED_TRIP_NAME", selectedTripName);
            startActivity(goToTripDetailsActivityPage);
        });
    }
}
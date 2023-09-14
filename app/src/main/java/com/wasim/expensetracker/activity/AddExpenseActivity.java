package com.wasim.expensetracker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Expense;
import com.amplifyframework.datastore.generated.model.Trip;
import com.wasim.expensetracker.R;

public class AddExpenseActivity extends AppCompatActivity {
    private final String TAG = "*** ADD EXPENSE ACTIVITY: ";

    EditText descriptionEditText;
    EditText amountNumberEditText;
    Button addThisExpenseButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);



        descriptionEditText = findViewById(R.id.AddExpenseActivityDescriptionPlainText);
        amountNumberEditText = findViewById(R.id.AddExpenseActivityAmountNumberTextView);
        addThisExpenseButton = findViewById(R.id.AddExpenseActivityAddThisExpenseButton);
        cancelButton = findViewById(R.id.AddExpenseActivityCancelButton);



        setupAddThisExpenseButton();
        setupCancelButton();

    }


    private void setupAddThisExpenseButton() {

        addThisExpenseButton.setOnClickListener(view -> {
            String selectedTripId = getIntent().getStringExtra("SELECTED_TRIP_ID");

            if (selectedTripId != null) {

                Amplify.API.query(
                        ModelQuery.get(Trip.class, selectedTripId),
                        response -> {
                            Trip trip = response.getData();

                            Expense expenseToSave = Expense.builder()
                                    .description(descriptionEditText.getText().toString())
                                    .amount(Double.parseDouble(amountNumberEditText.getText().toString()))
                                    .trip(trip)
                                    .build();

                            Amplify.API.mutate(
                                    ModelMutation.create(expenseToSave),
                                    successResponse -> Log.i(TAG, "AddExpenseActivity.setUpAddThisExpenseButton(): created expense successfully"),
                                    failureResponse -> Log.i(TAG, "AddExpenseActivity.setUpAddThisExpenseButton(): failure response " + failureResponse)
                            );

                        },
                        error -> Log.e(TAG, "Could not get Trip", error)
                );
            } else {
                Log.e(TAG, "Expense failed to build");
            }

            String selectedTripName = getIntent().getStringExtra("SELECTED_TRIP_NAME");
            Intent goToTripDetailsActivityPage = new Intent(AddExpenseActivity.this, TripDetailsActivity.class);
            goToTripDetailsActivityPage.putExtra("SELECTED_TRIP_NAME", selectedTripName);
            startActivity(goToTripDetailsActivityPage);
            Toast.makeText(AddExpenseActivity.this, "Expense saved!", Toast.LENGTH_SHORT).show();
        });
    }


    private void setupCancelButton() {

        cancelButton.setOnClickListener(v -> {
            String selectedTripName = getIntent().getStringExtra("SELECTED_TRIP_NAME");
            Intent goToTripDetailsActivityPage = new Intent(AddExpenseActivity.this, TripDetailsActivity.class);
            goToTripDetailsActivityPage.putExtra("SELECTED_TRIP_NAME", selectedTripName);
            startActivity(goToTripDetailsActivityPage);
        });
    }
}
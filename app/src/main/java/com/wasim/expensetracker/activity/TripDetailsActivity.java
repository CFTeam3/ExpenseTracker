package com.wasim.expensetracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.datastore.generated.model.Expense;
import com.amplifyframework.datastore.generated.model.Trip;
import com.wasim.expensetracker.R;
import com.wasim.expensetracker.adapter.ExpenseAdapter;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TripDetailsActivity extends AppCompatActivity {
    private final String TAG = "*** TRIP DETAILS ACTIVITY: ";

    TextView tripDetailsHeader;
    Button addExpenseButton;
    Button backButton;
    String selectedTripName;
    private RecyclerView expenseRecyclerView;
    private ExpenseAdapter expenseAdapter;
    private final ArrayList<Expense> expenses = new ArrayList<>();
    private CompletableFuture<Trip> tripFuture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        // Initialize RecyclerView
        expenseRecyclerView = findViewById(R.id.TripDetailsActivityExpenseRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        expenseRecyclerView.setLayoutManager(layoutManager);

        // Initialize adapter
        expenseAdapter = new ExpenseAdapter(expenses);
        expenseRecyclerView.setAdapter(expenseAdapter);

        addExpenseButton = findViewById(R.id.TripDetailsActivityAddExpenseButton);
        backButton = findViewById(R.id.TripDetailsActivityBackButton);

        tripDetailsHeader = findViewById(R.id.TripDetailsActivityHeader);
        String selectedTripName = getIntent().getStringExtra("SELECTED_TRIP_NAME");
        String selectedTripID= getIntent().getStringExtra("SELECTED_TRIP_ID");
        String headerText = selectedTripName + " Summary";
        tripDetailsHeader.setText(headerText);

        fetchAndDisplayExpenses(selectedTripID);
        setupAddExpenseButton();
        setupBackButton();
    }

    private void fetchAndDisplayExpenses(String selectedTripId) {
            Amplify.API.query(
                ModelQuery.list(Expense.class),
                response -> {
                    if (response.hasData()) {
                        for (Expense expense : response.getData()) {
                            if (expense.getTrip().getId().equals(selectedTripId)) {
                                Log.d(TAG, "Expense: " + expense.getDescription());
                                expenses.add(expense);
                            }
                        }
                        runOnUiThread(() -> {
                            expenseAdapter.notifyDataSetChanged();
                        });
                    } else if (response.hasErrors()) {
                        for (GraphQLResponse.Error error : response.getErrors()) {
                            Log.e(TAG, "Error: " + error.getMessage());
                        }
                    }
                },
                error -> Log.e(TAG, "Query failure", error)
        );
  }


    private void setupAddExpenseButton() {
        addExpenseButton.setOnClickListener(v -> {
            String selectedTripName = getIntent().getStringExtra("SELECTED_TRIP_NAME");
            String selectedTripId = getIntent().getStringExtra("SELECTED_TRIP_ID");
            System.out.println("This is the selectedTripId " + selectedTripId);
            Intent goToAddExpenseActivity = new Intent(TripDetailsActivity.this, AddExpenseActivity.class);
            goToAddExpenseActivity.putExtra("SELECTED_TRIP_ID", selectedTripId);
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
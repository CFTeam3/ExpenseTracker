package com.wasim.expensetracker.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Expense;
import com.wasim.expensetracker.R;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private final ArrayList<Expense> expenses;

    public ExpenseAdapter(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        Log.d("ExpenseAdapter", "Binding expense at position " + position + ": " + expense.getDescription());
        holder.descriptionTextView.setText(expense.getDescription());
        holder.amountTextView.setText(String.valueOf(expense.getAmount()));
    }

    @Override
    public int getItemCount() {
        Log.d("ExpenseAdapter", "Item count is: " + expenses.size());
        return expenses.size();
    }

    static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        final TextView descriptionTextView;
        final TextView amountTextView;

        ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
        }
    }
}

package com.example.arcmobile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CardHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private List<Transaction> transactionList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_history);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy data
        transactionList = new ArrayList<>();
        transactionList.add(new Transaction("20/03/2024", "12:00", "$0.00", "Southgate Station"));
        transactionList.add(new Transaction("20/03/2024", "12:45", "$1.75", "Southgate Station"));
        transactionList.add(new Transaction("20/03/2024", "12:00", "$1.75", "Southgate Station"));
        transactionList.add(new Transaction("20/03/2024", "12:00", "$1.75", "Southgate Station"));
        transactionList.add(new Transaction("20/03/2024", "12:00", "$1.75", "Southgate Station"));
        transactionList.add(new Transaction("20/03/2024", "12:00", "$1.75", "Southgate Station"));
        transactionList.add(new Transaction("20/03/2024", "12:00", "$1.75", "Southgate Station"));
        transactionList.add(new Transaction("20/03/2024", "12:00", "$1.75", "Southgate Station"));

        // Set up adapter
        adapter = new TransactionAdapter(transactionList);
        recyclerView.setAdapter(adapter);
    }
}
package com.example.arcmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Make sure this matches your XML file name

        // Initialize buttons
        Button checkBalanceButton = findViewById(R.id.CheckBalanceButton);
        Button profileButton = findViewById(R.id.ProfileButton);
        Button paymentButton = findViewById(R.id.PaymentButton);
        Button cardHistoryButton = findViewById(R.id.button3);
        ImageView logoImage = findViewById(R.id.logo);

        // Handle "Check/Reload Balance" Button Click
        checkBalanceButton.setOnClickListener(v -> {
        });

        // Handle "Profile" Button Click (Navigates to ProfileActivity)
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, Profile.class);
            startActivity(intent);
        });

        // Handle "Payment Page" Button Click (Navigates to PaymentActivity)
        paymentButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PaymentActivity.class);
            startActivity(intent);
        });

        // Handle "Card History" Button Click (Navigates to CardHistoryActivity)
        cardHistoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CardHistoryActivity.class);
            startActivity(intent);
        });
    }
}

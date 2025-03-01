package com.example.arcmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class OnBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_bus);

        Button btnYes = findViewById(R.id.btnYes);
        Button btnNo = findViewById(R.id.btnNo);

        // Handle "Yes" button click
        btnYes.setOnClickListener(v -> {
            // Navigate to PaymentActivity
            Intent intent = new Intent(OnBusActivity.this, PaymentActivity.class);
            startActivity(intent);
        });

        // Handle "No" button click
        btnNo.setOnClickListener(v -> {
            // Close the activity
            finish();
        });
    }
}
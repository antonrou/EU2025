package com.example.arc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class QRCodeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qrcode); // This layout will contain the container for the fragment

        // If the activity is not being recreated (i.e., no saved state), add the fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FragmentQRCode()) // Replace the container with the fragment
                    .commit();
        }
    }
}
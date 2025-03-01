package com.example.arcmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    private EditText etName, etCity, etProvince;
    private Spinner spinnerAgeGroup;
    private Button btnSave;

    private String selectedAgeGroup = ""; // Stores selected age group

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        // Initialize UI elements
        etName = findViewById(R.id.etName);
        etCity = findViewById(R.id.etCity);
        etProvince = findViewById(R.id.etProvince);
        spinnerAgeGroup = findViewById(R.id.spinnerAgeGroup);
        btnSave = findViewById(R.id.btnSave);

        // Define age group options
        String[] ageGroups = {"Select Age Group", "Youth (6-18)", "Young adults (19-24)",
                "Students (19-24)", "Students (25+)"};

}
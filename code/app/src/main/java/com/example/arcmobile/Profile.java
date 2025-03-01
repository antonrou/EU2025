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

public class Profile extends AppCompatActivity {
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

        // Set up Spinner (dropdown)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ageGroups);
        spinnerAgeGroup.setAdapter(adapter);

        // Handle selection from spinner
        spinnerAgeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAgeGroup = ageGroups[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedAgeGroup = "";
            }
        });

        // Save button click event
        btnSave.setOnClickListener(v -> saveProfile());
    }

    private void saveProfile() {
        String name = etName.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String province = etProvince.getText().toString().trim();

        if (name.isEmpty() || city.isEmpty() || province.isEmpty() || selectedAgeGroup.equals("Select Age Group")) {
            Toast.makeText(this, R.string.please_fill_all_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        // Display entered details in a toast (for now)
        String message = "Profile Saved:\nName: " + name + "\nAge Group: " + selectedAgeGroup +
                "\nLocation: " + city + ", " + province;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
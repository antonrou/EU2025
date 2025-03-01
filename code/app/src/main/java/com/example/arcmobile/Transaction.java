package com.example.arcmobile;

import androidx.appcompat.app.AppCompatActivity;

public class Transaction extends AppCompatActivity {
    private String date;
    private String time;
    private String amount;
    private String location;

    public Transaction(String date, String time, String amount, String location) {
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAmount() {
        return amount;
    }

    public String getLocation() {
        return location;
    }
}
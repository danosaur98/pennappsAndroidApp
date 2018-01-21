package com.example.danielzhou.pennapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LotteryDetail extends AppCompatActivity {
    private Button mButton;
    private TextView name;
    private TextView charity;
    private TextView endDate;
    private TextView currentContributions;
    private EditText yourContributions;
    private String participantID = "5a563d1c5eaa612c093b0b20";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_detail);
        mButton = findViewById(R.id.joinLottery);
        name = findViewById(R.id.joinLotteryName);
        charity = findViewById(R.id.joinCharityName);
        endDate = findViewById(R.id.joinEndDate);
        currentContributions = findViewById(R.id.joinCurrentValue);
        yourContributions = findViewById(R.id.joinYourContribution);
        name.setText("Name: " + getIntent().getStringExtra("name"));
        currentContributions.setText("Current Amount: " + getIntent().getStringExtra("amount"));
        charity.setText("Charity: " + getIntent().getStringExtra("charity"));
        endDate.setText("End Date: " + getIntent().getStringExtra("endDate"));
    }
}

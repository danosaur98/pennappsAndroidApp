package com.example.danielzhou.pennapps;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

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
        currentContributions.setText("Current Amount: $" + getIntent().getStringExtra("amount"));
        charity.setText("Charity: " + getIntent().getStringExtra("charity"));
        endDate.setText("End Date: " + getIntent().getStringExtra("endDate"));
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();

                        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
                        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"empty\"\r\n\r\nempty\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
                        Uri.Builder builder = new Uri.Builder();
                        builder.scheme("https")
                                .authority("whispering-scrubland-39491.herokuapp.com")
                                .appendPath("joinLottery")
                                .appendQueryParameter("contribution", yourContributions.getText().toString())
                                .appendQueryParameter("lotteryID", getIntent().getStringExtra("lotteryID"))
                                .appendQueryParameter("participantID", participantID);
                        String myUrl = builder.build().toString();

                        Request request = new Request.Builder()
                                .url(myUrl)
                                .post(body)
                                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                                .addHeader("cache-control", "no-cache")
                                .addHeader("postman-token", "f4dc6c47-d32e-7241-5b1c-eaaffec06c7a")
                                .build();

                        try {
                            Response response = client.newCall(request).execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}

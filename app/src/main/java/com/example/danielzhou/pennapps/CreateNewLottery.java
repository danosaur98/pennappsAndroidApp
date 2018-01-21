package com.example.danielzhou.pennapps;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class CreateNewLottery extends AppCompatActivity {
    private Button mButton;
    private EditText name;
    private EditText total;
    private EditText charity;
    private TimePicker tp;
    private String participantID = "5a563d1c5eaa612c093b0b20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_lottery);
        mButton = findViewById(R.id.CreateLottery);
        name = findViewById(R.id.lotteryName);
        total = findViewById(R.id.total);
        charity = findViewById(R.id.charityName);
        tp = findViewById(R.id.timePicker);
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
                                .appendPath("addLottery")
                                .appendQueryParameter("name", name.getText().toString())
                                .appendQueryParameter("total", total.getText().toString())
                                .appendQueryParameter("participantID", participantID)
                                .appendQueryParameter("endDate",  tp.getHour() + ":" + tp.getMinute())
                                .appendQueryParameter("charity", charity.getText().toString());
                        String myUrl = builder.build().toString();
                        Request request = new Request.Builder()
                                .url(myUrl)
                                .post(body)
                                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                                .addHeader("cache-control", "no-cache")
                                .addHeader("postman-token", "3afe73b4-c012-c3e1-3293-1b79cf3915fe")
                                .build();

                        try {
                            Response response = client.newCall(request).execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}

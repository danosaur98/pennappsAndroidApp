package com.example.danielzhou.pennapps;

import android.support.v7.widget.Toolbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<String> nameArray = new ArrayList<>();
        nameArray.add("one");
        nameArray.add("one");
        nameArray.add("one");
        nameArray.add("one");
        nameArray.add("one");
        nameArray.add("one");
        nameArray.add("one");
        nameArray.add("one");
        nameArray.add("one");
        nameArray.add("one");

        ArrayList<String> amountArray = new ArrayList<>();
        amountArray.add("1");
        amountArray.add("1");
        amountArray.add("1");
        amountArray.add("1");
        amountArray.add("1");
        amountArray.add("1");
        amountArray.add("1");
        amountArray.add("1");
        amountArray.add("1");
        amountArray.add("1");

        ArrayList<String> charityArray = new ArrayList<>();
        charityArray.add("UNICEF");
        charityArray.add("UNICEF");
        charityArray.add("UNICEF");
        charityArray.add("UNICEF");
        charityArray.add("UNICEF");
        charityArray.add("UNICEF");
        charityArray.add("UNICEF");
        charityArray.add("UNICEF");
        charityArray.add("UNICEF");
        charityArray.add("UNICEF");

        ArrayList<String> endDateArray = new ArrayList<>();
        endDateArray.add("1/20/18 5:32:00PM");
        endDateArray.add("1/20/18 5:32:00PM");
        endDateArray.add("1/20/18 5:32:00PM");
        endDateArray.add("1/20/18 5:32:00PM");
        endDateArray.add("1/20/18 5:32:00PM");
        endDateArray.add("1/20/18 5:32:00PM");
        endDateArray.add("1/20/18 5:32:00PM");
        endDateArray.add("1/20/18 5:32:00PM");
        endDateArray.add("1/20/18 5:32:00PM");
        endDateArray.add("1/20/18 5:32:00PM");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, amountArray, charityArray, endDateArray);
        mListView = (ListView) findViewById(R.id.listviewID);
        mListView.setAdapter(whatever);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTextView = findViewById(R.id.textView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();

                        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
                        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"empty\"\r\n\r\nempty\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
                        Request request = new Request.Builder()
                                .url("https://whispering-scrubland-39491.herokuapp.com/addLottery?name=test5&amount=5&participantID=5a563d1c5eaa612c093b0b20")
                                .post(body)
                                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                                .addHeader("cache-control", "no-cache")
                                .addHeader("postman-token", "47f8c6c1-953d-b20c-42ec-9c8a2e5ce9f3")
                                .build();

                        try {
                            Response response = client.newCall(request).execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                mTextView.setText("rekt");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<String> nameArray = new ArrayList<>();

        final ArrayList<String> amountArray = new ArrayList<>();

        final ArrayList<String> charityArray = new ArrayList<>();

        final ArrayList<String> endDateArray = new ArrayList<>();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://whispering-scrubland-39491.herokuapp.com/getLotteries")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "3b2bb3c0-413b-c395-cb6b-2a4ab64c3570")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String jsonData = response.body().string();
                    JSONObject Jobject = null;
                    int timer = 30;
                    try {
                        Jobject = new JSONObject(jsonData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray Jarray = Jobject.getJSONArray("result");
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject lottery = Jarray.getJSONObject(i);
                        nameArray.add(lottery.getString("title"));
                        amountArray.add(lottery.getString("amount"));
                        charityArray.add(lottery.getString("charity"));
                        endDateArray.add(lottery.getString("endDate"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }});
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

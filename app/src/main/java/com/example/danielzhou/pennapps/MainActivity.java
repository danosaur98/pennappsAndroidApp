package com.example.danielzhou.pennapps;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private ListView mListView;
    private Button mButton;
    private CustomListAdapter mCustomListAdapter;
    final ArrayList<String> nameArray = new ArrayList<>();
    final ArrayList<String> amountArray = new ArrayList<>();
    final ArrayList<String> charityArray = new ArrayList<>();
    final ArrayList<String> endDateArray = new ArrayList<>();
    final ArrayList<String> lotteryIDArray = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTextView = findViewById(R.id.bankAccount);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://whispering-scrubland-39491.herokuapp.com/getLotteries")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "b866a099-64e6-4978-07e7-ce2304ab4b0d")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String jsonData = response.body().string();
                    JSONObject Jobject = null;
                    try {
                        Jobject = new JSONObject(jsonData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    final JSONArray Jarray = Jobject.getJSONArray("result");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < Jarray.length(); i++) {
                                JSONObject lottery = null;
                                try {
                                    lottery = Jarray.getJSONObject(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    nameArray.add(lottery.getString("title"));
                                    amountArray.add(lottery.getString("total"));
                                    charityArray.add(lottery.getString("charity"));
                                    endDateArray.add(lottery.getString("endDate"));
                                    lotteryIDArray.add(lottery.getString("lotteryID"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                mCustomListAdapter.notifyDataSetChanged();

                            }
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomListAdapter = new CustomListAdapter(this, nameArray, amountArray, charityArray, endDateArray);
        mListView = (ListView) findViewById(R.id.listviewID);
        mListView.setAdapter(mCustomListAdapter);
        Button newLottery = (Button) findViewById(R.id.button);
        newLottery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), CreateNewLottery.class);
                startActivityForResult(i, 1);
            }
        });
        mCustomListAdapter.notifyDataSetChanged();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, LotteryDetail.class);
                intent.putExtra("name", nameArray.get(position));
                intent.putExtra("amount", amountArray.get(position));
                intent.putExtra("charity", charityArray.get(position));
                intent.putExtra("endDate", endDateArray.get(position));
                intent.putExtra("lotteryID", lotteryIDArray.get(position));
                startActivity(intent);
            }
        });
        mButton = findViewById(R.id.refresh);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        nameArray.clear();
                        amountArray.clear();
                        charityArray.clear();
                        endDateArray.clear();
                        lotteryIDArray.clear();
                        OkHttpClient client = new OkHttpClient();

                        Request request = new Request.Builder()
                                .url("https://whispering-scrubland-39491.herokuapp.com/getLotteries")
                                .get()
                                .addHeader("cache-control", "no-cache")
                                .addHeader("postman-token", "b866a099-64e6-4978-07e7-ce2304ab4b0d")
                                .build();
                        try {
                            Response response = client.newCall(request).execute();
                            String jsonData = response.body().string();
                            JSONObject Jobject = null;
                            try {
                                Jobject = new JSONObject(jsonData);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            final JSONArray Jarray = Jobject.getJSONArray("result");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < Jarray.length(); i++) {
                                        JSONObject lottery = null;
                                        try {
                                            lottery = Jarray.getJSONObject(i);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            nameArray.add(lottery.getString("title"));
                                            amountArray.add(lottery.getString("total"));
                                            charityArray.add(lottery.getString("charity"));
                                            endDateArray.add(lottery.getString("endDate"));
                                            lotteryIDArray.add(lottery.getString("lotteryID"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        mCustomListAdapter.notifyDataSetChanged();
                                    }
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCustomListAdapter.notifyDataSetChanged();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final String input = data.getStringExtra("title") + " has ran out of time!";
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), input ,
                        Toast.LENGTH_LONG).show();
            }
        };
        handler.postDelayed(runnable, 2000);
    }
}

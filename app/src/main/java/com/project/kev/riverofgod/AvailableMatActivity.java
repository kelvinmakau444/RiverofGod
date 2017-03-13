package com.project.kev.riverofgod;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kev on 3/11/2017.
 */

public class AvailableMatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private availableMatAdapter availableMatAdapter;

    List<availableMat> carList;
    LinearLayoutManager myLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_available_mat);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i= new Intent(AvailableMatActivity.this,BackgroundService.class);
        //startService(i);


        recyclerView=(RecyclerView)findViewById(R.id.matRV);
        //autorefresh
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpTask asyncHttpTask = new AsyncHttpTask();
                asyncHttpTask.execute();
            }
        });
        // AutorefreshMatDetails();
        AsyncHttpTask asyncHttpTask = new AsyncHttpTask();
        asyncHttpTask.execute();


    }

    public class AsyncHttpTask extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog = new ProgressDialog(AvailableMatActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL("http://10.0.2.2/Rog/available_cars.php");


            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();

            }
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);

            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }
            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }


        @Override
        protected void onPostExecute(String result) {
             progressDialog.dismiss();
            carList = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_data = jsonArray.getJSONObject(i);
                    availableMat newCar = new availableMat();
                    newCar.plate = json_data.optString("car_plate");
                    newCar.available_seats = json_data.optString("available_seats");
                    newCar.driver_image = json_data.getString("driver_pic");
                    newCar.driver = json_data.getString("driver_names");
                    newCar.driver_id = json_data.optString("driver_id");


                    carList.add(newCar);
                }
                availableMatAdapter = new availableMatAdapter(AvailableMatActivity.this, carList);
                myLinearLayout = new LinearLayoutManager(AvailableMatActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(myLinearLayout);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(availableMatAdapter);


                //  wentWrong.setVisibility(View.GONE);


            } catch (JSONException e) {
                final View v = findViewById(R.id.snack);

                //  wentWrong.setVisibility(View.VISIBLE);

                Snackbar.make(v, "An error occurred", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Refresh", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                v.setVisibility(View.GONE);
                                AsyncHttpTask asyncHttpTask = new AsyncHttpTask();
                                asyncHttpTask.execute();
                            }
                        }).show();
            }

        }
    }
}
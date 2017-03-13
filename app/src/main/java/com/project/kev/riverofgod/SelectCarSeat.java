package com.project.kev.riverofgod;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectCarSeat extends AppCompatActivity {
    RequestQueue req;

    TextView one_a,one_b,two_a,two_b,two_c,three_a,three_b,three_c,four_a,four_b,four_c,five_a,five_b,five_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car_seat);
     //    CheckReserved();
    }

    private void CheckReserved() {
        final ProgressDialog progressDialog=new ProgressDialog(SelectCarSeat.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        JsonArrayRequest jsonarrreq = new JsonArrayRequest("http://10.0.2.2/Rog/fetch_reserved.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progressDialog.dismiss();
                int len=jsonArray.length();
                String leng= String.valueOf(len);
                if(!leng.isEmpty()|!leng.equals(" ")) {
                    //resultsFound.setText("Record(s) found : " + leng);

                }
                try {
                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < len; i++) {
                            JSONObject json_data = jsonArray.getJSONObject(i);
                            Toast.makeText(SelectCarSeat.this,json_data.toString(),Toast.LENGTH_LONG).show();

                           // String seat_type=(json_data.getString("seat_type"));







                        }


                    } else {

                        Toast.makeText(SelectCarSeat.this, "Nothing found", Toast.LENGTH_LONG).show();




                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(SelectCarSeat.this, volleyError.toString(), Toast.LENGTH_LONG).show();

            }
        });
        req.add(jsonarrreq);

    }




}

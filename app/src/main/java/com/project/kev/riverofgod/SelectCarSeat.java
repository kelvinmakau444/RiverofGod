package com.project.kev.riverofgod;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectCarSeat extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    RequestQueue req;
    LinearLayout contLayout;

    final int fare=50;

    TinyDB tiny;

    List<String> selectionlist= Collections.synchronizedList(new ArrayList<String>());

    TextView seatsNo,fareTotal;
    CheckBox one_a,one_b,two_a,two_b,two_c,three_a,three_b,three_c,four_a,four_b,four_c,five_a,five_b,five_c;
    TableLayout tbl;
    RelativeLayout tblRL;
    Button Confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car_seat);

        tblRL=(RelativeLayout)findViewById(R.id.tblRL);

        contLayout=(LinearLayout)findViewById(R.id.contLayout);
        seatsNo=(TextView)findViewById(R.id.seats_selected);
        fareTotal=(TextView)findViewById(R.id.fare);

     //    CheckReserved();
        tbl=(TableLayout)findViewById(R.id.tableLayout);
        one_a=(CheckBox) findViewById(R.id.one_a) ;
        one_a.setOnCheckedChangeListener(this);

        one_b=(CheckBox) findViewById(R.id.one_b) ;
        one_b.setOnCheckedChangeListener(this);

        two_a=(CheckBox) findViewById(R.id.two_a) ;
        two_a.setOnCheckedChangeListener(this);

        two_b=(CheckBox) findViewById(R.id.two_b) ;
        two_b.setOnCheckedChangeListener(this);

        two_c=(CheckBox) findViewById(R.id.two_c) ;
        two_c.setOnCheckedChangeListener(this);

        three_a=(CheckBox) findViewById(R.id.three_a) ;
        three_a.setOnCheckedChangeListener(this);

        three_b=(CheckBox) findViewById(R.id.three_b) ;
        three_b.setOnCheckedChangeListener(this);

        three_c=(CheckBox) findViewById(R.id.three_c) ;
        three_c.setOnCheckedChangeListener(this);

        four_a=(CheckBox) findViewById(R.id.four_a) ;
        four_a.setOnCheckedChangeListener(this);

        four_b=(CheckBox) findViewById(R.id.four_b) ;
        four_b.setOnCheckedChangeListener(this);

        four_c=(CheckBox) findViewById(R.id.four_c) ;
        four_c.setOnCheckedChangeListener(this);

        five_a=(CheckBox) findViewById(R.id.five_a) ;
        five_a.setOnCheckedChangeListener(this);

        five_b=(CheckBox) findViewById(R.id.five_b) ;
        five_b.setOnCheckedChangeListener(this);

        five_c=(CheckBox) findViewById(R.id.five_c) ;
        five_c.setOnCheckedChangeListener(this);


        Confirm=(Button)tbl.findViewById(R.id.confirm_btn);
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmSelection();
            }
        });


    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        int id=compoundButton.getId();
        switch (id){
            case R.id.one_a:
                if (isChecked) {
                    one_a.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                   // Toast.makeText(this, "Seat 1a selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("1a");
                }else{
                    one_a.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                   // Toast.makeText(this, "Seat 1a de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("1a");
                }
                break;
            case R.id.one_b:
                if (isChecked) {
                    one_b.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                  //  Toast.makeText(this, "Seat 1b selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("1b");
                }else{
                    one_b.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                   // Toast.makeText(this, "Seat 1b de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("1b");

                }
                break;

            case R.id.two_a:
                if (isChecked) {
                    two_a.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                 //   Toast.makeText(this, "Seat 2a selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("2a");
                }else{
                    two_a.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                  //  Toast.makeText(this, "Seat 2a de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("2a");

                }
                break;

            case R.id.two_b:
                if (isChecked) {
                    two_b.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                    //Toast.makeText(this, "Seat 2b selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("2b");
                }else{
                    two_b.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                   // Toast.makeText(this, "Seat 2b de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("2b");

                }
                break;

            case R.id.two_c:
                if (isChecked) {
                    two_c.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                   // Toast.makeText(this, "Seat 2c selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("2c");
                }else{
                    two_c.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                   // Toast.makeText(this, "Seat 2c de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("2c");

                }
                break;

            case R.id.three_a:
                if (isChecked) {
                    three_a.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                    //Toast.makeText(this, "Seat 3a selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("3a");
                }else{
                    three_a.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                    //Toast.makeText(this, "Seat 3a de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("3a");

                }
                break;

            case R.id.three_b:
                if (isChecked) {
                    three_b.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                    //Toast.makeText(this, "Seat 3b selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("3b");
                }else{
                    three_b.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                    //Toast.makeText(this, "Seat 3b de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("3b");

                }
                break;

            case R.id.three_c:
                if (isChecked) {
                    three_c.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                  //  Toast.makeText(this, "Seat 3c selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("3c");
                }else{
                    three_c.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                   // Toast.makeText(this, "Seat 3c de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("3c");

                }
                break;

            case R.id.four_a:
                if (isChecked) {
                    four_a.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                   // Toast.makeText(this, "Seat 4a selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("4a");
                }else{
                    four_a.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                   // Toast.makeText(this, "Seat 4a de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("4a");

                }
                break;

            case R.id.four_b:
                if (isChecked) {
                    four_b.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                   // Toast.makeText(this, "Seat 4b selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("4b");
                }else{
                    four_b.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                    //Toast.makeText(this, "Seat 4b de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("4b");

                }
                break;

            case R.id.four_c:
                if (isChecked) {
                    four_c.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                   // Toast.makeText(this, "Seat 4c selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("4c");
                }else{
                    four_c.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                    //Toast.makeText(this, "Seat 4c de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("4c");

                }
                break;

            case R.id.five_a:
                if (isChecked) {
                    five_a.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                    //Toast.makeText(this, "Seat 5a selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("5a");
                }else{
                    five_a.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                   // Toast.makeText(this, "Seat 5a de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("5a");

                }
                break;

            case R.id.five_b:
                if (isChecked) {
                    five_b.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                   // Toast.makeText(this, "Seat 5b selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("5b");
                }else{
                    five_b.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                   // Toast.makeText(this, "Seat 5b de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("5b");

                }
                break;

            case R.id.five_c:
                if (isChecked) {
                    five_c.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                   // Toast.makeText(this, "Seat 5c selected", Toast.LENGTH_SHORT).show();
                    selectionlist.add("5c");
                }else{
                    five_c.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                   // Toast.makeText(this, "Seat 5c de-selected", Toast.LENGTH_SHORT).show();
                    selectionlist.remove("5c");

                }


        }
    }

    public void confirmSelection() {
        if(selectionlist.isEmpty()){
            Toast.makeText(this, "Please select a seat", Toast.LENGTH_LONG).show();
        }else {
            int size = selectionlist.size();
            Toast.makeText(this,size + "" , Toast.LENGTH_SHORT).show();

            seatsNo.setText("Selected Seats: "+size);

            int fareInTotal=fare*size;
            fareTotal.setText("Total Fare: "+fareInTotal);

            Confirm.setVisibility(View.GONE);
            contLayout.setVisibility(View.VISIBLE);
            disableAllCheckBox();

        }
    }

    private void disableAllCheckBox() {
        one_a.setEnabled(false);
        one_b.setEnabled(false);
        two_a.setEnabled(false);
        two_b.setEnabled(false);
        two_c.setEnabled(false);
        three_a.setEnabled(false);
        three_b.setEnabled(false);
        three_c.setEnabled(false);
        four_a.setEnabled(false);
        four_b.setEnabled(false);
        four_c.setEnabled(false);
        five_a.setEnabled(false);
        five_b.setEnabled(false);
        five_c.setEnabled(false);


    }
    public void resetSelections(View view) {
//TO DO: create restart Activity select mat seat
        finish();
    }

    public void ContinueToPay(View view) {
        startActivity(new Intent(SelectCarSeat.this,PaymentActivity.class));
    }
}

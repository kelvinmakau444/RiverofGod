package com.project.kev.riverofgod;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout usernameTIL,passwordTIL;
    TextInputEditText username,password;
    String  USERNAME,PASSWORD;

    View snack;
int notifications;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        snack=(View)findViewById(R.id.snack);
        usernameTIL=(TextInputLayout)findViewById(R.id.username_textInput_layout);
        passwordTIL=(TextInputLayout)findViewById(R.id.password_textInput_layout);
        username=(TextInputEditText)findViewById(R.id.username);
        password=(TextInputEditText)findViewById(R.id.password);
        loginBtn=(Button)findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                USERNAME=username.getText().toString().trim();
                PASSWORD=password.getText().toString().trim();
                if(USERNAME.isEmpty()){
                    usernameTIL.setError("Username can't be empty !");
                }else if(PASSWORD.isEmpty()){
                    passwordTIL.setError("Password can't be empty !");
                }else {
                    AuthenticateCustomer(USERNAME, PASSWORD);
                }
            }
        });



    }

    private void AuthenticateCustomer(final String USERNAME, final String PASSWORD) {


        SessionManager sessionManager=new SessionManager();
        sessionManager.setPreferences(LoginActivity.this,"notification_count", String.valueOf(23));


        final ProgressDialog pdiag=new ProgressDialog(LoginActivity.this);
        // pdiag.setTitle("Contacting Servers");
        pdiag.setMessage("Signing in. Please Wait...");
        pdiag.setCancelable(false);
        pdiag.show();
        final String url="http://10.0.2.2/Rog/login.php";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pdiag.dismiss();
                if (!(response.contains("failure"))) {
                    SessionManager sessionManager=new SessionManager();
                    sessionManager.setPreferences(LoginActivity.this, "status", "1");
                    sessionManager.setPreferences(LoginActivity.this,"customer",response);
                    Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(snack, "Sorry Login "+ "Failed" +" "+"Check your credentials", Snackbar.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                pdiag.dismiss();

                Snackbar.make(snack, "An error occurred..Try again", Snackbar.LENGTH_LONG).show();
                //  Toast.makeText(Login.this,volleyError.toString(),Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> map=new HashMap<String, String>();
                map.put("username", USERNAME);
                map.put("password", PASSWORD);
                return  map;
            }
        };
        RequestQueue req= Volley.newRequestQueue(this);
        req.add(stringRequest);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(1);


    }
}

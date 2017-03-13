package com.project.kev.riverofgod;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView notifcount,book_mat;
    Button notificationsBtn;
    int mNotifcount=0;
    TextView profile_name;




TextView hello;
    LinearLayout prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//setting sustomer name in nav header
        NavigationView nav=(NavigationView)findViewById(R.id.nav_view) ;

        profile_name=(TextView)nav.getHeaderView(0).findViewById(R.id.customer);
        try{
            SessionManager sessionManager=new SessionManager();
           String name= sessionManager.getPreferences(MainActivity.this,"customer");
            profile_name.setText(name);
        }catch (Exception e){
            e.printStackTrace();
        }
 //setting onclick profile
        prof= (LinearLayout) nav.getHeaderView(0).findViewById(R.id.prof_LL);
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfileDetails();
            }
        });

        book_mat=(TextView)findViewById(R.id.book_a_mat);
        Typeface tf=Typeface.createFromAsset(getAssets(),"fontawesome.ttf");
        book_mat.setTypeface(tf);

        hello=(TextView)findViewById(R.id.hello);
        fetchVerse(hello);

        //example of any notification present

        setNotificationCount();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void goToProfileDetails() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        Toast.makeText(this, "Now go to my Profile Activity", Toast.LENGTH_SHORT).show();
    }

    private void setNotificationCount() {
        int count = Integer.parseInt(new SessionManager().getPreferences(MainActivity.this,"notification_count"));
        mNotifcount= count;

        invalidateOptionsMenu();
    }

    private void fetchVerse(final TextView hello) {
        RequestQueue req = Volley.newRequestQueue(this);
        JsonArrayRequest jsonarrreq = new JsonArrayRequest("http://10.0.2.2/Rog/fetch_inspiration.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                try {
                    if (jsonArray.length() > 0) {
                        //  Toast.makeText(MainActivity.this,leng,Toast.LENGTH_LONG).show();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json_data = jsonArray.getJSONObject(i);

                            String inspiration = json_data.getString("verse");

                           hello.setText(inspiration);

                        }


                    } else {

                       // Toast.makeText(MainActivity.this,"An error ocurred",Toast.LENGTH_LONG).show();
                        hello.setText("Smile, Jesus Loves You");


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();

            }
        });
        req.add(jsonarrreq);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder sure=new AlertDialog.Builder(MainActivity.this);
            sure.setCancelable(false);
            sure.setMessage("Are you sure you want to exit?");
            sure.setNegativeButton("Yap", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                    finish();


                }
            });
            sure.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            sure.show();
           // super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        View notifyView=menu.findItem(R.id.action_notify).getActionView();


        notificationsBtn=(Button)notifyView.findViewById(R.id.notificationsBtn);
        notificationsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Notify button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        notifcount=(TextView) notifyView.findViewById(R.id.notif_count);
        if(mNotifcount<1){
            notifcount.setVisibility(View.GONE);
        }else if(mNotifcount>5){
            notifcount.setText("5+");
        } else{
            notifcount.setText(String.valueOf(mNotifcount));
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
int id=item.getItemId();
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickHomeBtn(View view) {
        int id=view.getId();
        switch (id){
            case R.id.book_a_mat:
                startActivity(new Intent(MainActivity.this,AvailableMatActivity.class));
                break;
            case R.id.transactions_history:
                //startActivity(new Intent(MainActivity.this,AvailableMatActivity.class));
                break;
            case R.id.events:
                //startActivity(new Intent(MainActivity.this,AvailableMatActivity.class));
                break;
            case R.id.lost_and_found:
                //startActivity(new Intent(MainActivity.this,AvailableMatActivity.class));
                break;
            case R.id.staff:
                //startActivity(new Intent(MainActivity.this,AvailableMatActivity.class));
                break;
            case R.id.feedback:
                //startActivity(new Intent(MainActivity.this,AvailableMatActivity.class));
                break;

        }
    }

}

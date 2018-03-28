package com.timetable.kevin.timetable_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //Hamburger + settings
        setSupportActionBar(toolbar);

     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab); -->  Button Rechts unten
        fab.setOnClickListener(new View.OnClickListener() { --> click listener
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        findViewById(R.id.AendernButton).setOnClickListener(new handleButton());
        findViewById(R.id.reloadButton).setOnClickListener(new handleButton());
        connectWS();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;
        if (id == R.id.nav_dashboard) {
            if (DashboardActivity.this != this) {
                intent = new Intent(DashboardActivity.this, DashboardActivity.class);   //no relativ sinnfrei, da man fost lai afn dashboard es menü aufriafen konn^^
                startActivity(intent);
            }
        } else if (id == R.id.nav_logoout) {
            intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void connectWS(){
        final AsyncHttpClient client = new AsyncHttpClient();
      /*  client.get("http://kevinsorg.bplaced.net", new AsyncHttpResponseHandler()) {

            @Override
            public void onStart() {
                TextView t1 = (TextView)findViewById(R.id.r2TextView1);  // --> IT WORKS
                t1.setText("trying...");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                TextView t1 = (TextView)findViewById(R.id.r2TextView1);  // --> IT WORKS
                t1.setText("Success");

                JSONObject daten = new JSONObject();

            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                TextView t1 = (TextView)findViewById(R.id.r2TextView1);

                t1.setText("Failure");

            }

            @Override
            public void onRetry(int retryNo) {
                TextView t1 = (TextView)findViewById(R.id.r2TextView1);  // --> IT WORKS
                t1.setText("retrying...");
            }
        });
*/
    //  DO FAHLTS MITN JSON DESSWEGEN FAILURE
        client.get("http://kevinsorg.bplaced.net", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                if (response != null) {
                    TextView t1 = (TextView)findViewById(R.id.r2TextView1);  // --> IT WORKS
                    t1.setText("Success");

                    //JSONObject daten = Max traurig :,( --> Kevin moch du des!
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                TextView t1 = (TextView)findViewById(R.id.r2TextView1);  // --> IT WORKS
                t1.setText("Failure");

            }
            @Override
            public void onRetry(int retryNo) {
                TextView t1 = (TextView)findViewById(R.id.r2TextView1);  // --> IT WORKS
                t1.setText("retrying...");
            }

        });
    }

    class handleButton implements View.OnClickListener {
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.AendernButton) {
                Intent intent = new Intent(DashboardActivity.this, EditTTActivity.class);
                startActivity(intent);
            }
            else if (id == R.id.reloadButton){
                connectWS();
            }
        }

    }


}

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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    boolean nEm = true;

    Stundenplan st = new Stundenplan();
    TextView[][] valuesFuerST = new TextView[9][7];



    private ResponseHandler rh = new ResponseHandler("DBA", this);
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar); //Hamburger + settings
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

        valuesFuerST[1][0]=findViewById(R.id.r1TextView1);
        valuesFuerST[1][1]=findViewById(R.id.r1TextView2);
        valuesFuerST[1][2]=findViewById(R.id.r1TextView3);
        valuesFuerST[1][3]=findViewById(R.id.r1TextView4);
        valuesFuerST[1][4]=findViewById(R.id.r1TextView5);
        valuesFuerST[1][5]=findViewById(R.id.r1TextView6);
        valuesFuerST[1][6]=findViewById(R.id.r1TextView7);


        valuesFuerST[2][0]=findViewById(R.id.r2TextView1);
        valuesFuerST[2][1]=findViewById(R.id.r2TextView2);
        valuesFuerST[2][2]=findViewById(R.id.r2TextView3);
        valuesFuerST[2][3]=findViewById(R.id.r2TextView4);
        valuesFuerST[2][4]=findViewById(R.id.r2TextView5);
        valuesFuerST[2][5]=findViewById(R.id.r2TextView6);
        valuesFuerST[2][6]=findViewById(R.id.r2TextView7);


        valuesFuerST[3][0]=findViewById(R.id.r3TextView1);
        valuesFuerST[3][1]=findViewById(R.id.r3TextView2);
        valuesFuerST[3][2]=findViewById(R.id.r3TextView3);
        valuesFuerST[3][3]=findViewById(R.id.r3TextView4);
        valuesFuerST[3][4]=findViewById(R.id.r3TextView5);
        valuesFuerST[3][5]=findViewById(R.id.r3TextView6);
        valuesFuerST[3][6]=findViewById(R.id.r3TextView7);


        valuesFuerST[4][0]=findViewById(R.id.r4TextView1);
        valuesFuerST[4][1]=findViewById(R.id.r4TextView2);
        valuesFuerST[4][2]=findViewById(R.id.r4TextView3);
        valuesFuerST[4][3]=findViewById(R.id.r4TextView4);
        valuesFuerST[4][4]=findViewById(R.id.r4TextView5);
        valuesFuerST[4][5]=findViewById(R.id.r4TextView6);
        valuesFuerST[4][6]=findViewById(R.id.r4TextView7);


        valuesFuerST[5][0]=findViewById(R.id.r5TextView1);
        valuesFuerST[5][1]=findViewById(R.id.r5TextView2);
        valuesFuerST[5][2]=findViewById(R.id.r5TextView3);
        valuesFuerST[5][3]=findViewById(R.id.r5TextView4);
        valuesFuerST[5][4]=findViewById(R.id.r5TextView5);
        valuesFuerST[5][5]=findViewById(R.id.r5TextView6);
        valuesFuerST[5][6]=findViewById(R.id.r5TextView7);


        valuesFuerST[6][0]=findViewById(R.id.r6TextView1);
        valuesFuerST[6][1]=findViewById(R.id.r6TextView2);
        valuesFuerST[6][2]=findViewById(R.id.r6TextView3);
        valuesFuerST[6][3]=findViewById(R.id.r6TextView4);
        valuesFuerST[6][4]=findViewById(R.id.r6TextView5);
        valuesFuerST[6][5]=findViewById(R.id.r6TextView6);
        valuesFuerST[6][6]=findViewById(R.id.r6TextView7);


        valuesFuerST[7][0]=findViewById(R.id.r7TextView1);
        valuesFuerST[7][1]=findViewById(R.id.r7TextView2);
        valuesFuerST[7][2]=findViewById(R.id.r7TextView3);
        valuesFuerST[7][3]=findViewById(R.id.r7TextView4);
        valuesFuerST[7][4]=findViewById(R.id.r7TextView5);
        valuesFuerST[7][5]=findViewById(R.id.r7TextView6);
        valuesFuerST[7][6]=findViewById(R.id.r7TextView7);


        valuesFuerST[8][0]=findViewById(R.id.r8TextView1);
        valuesFuerST[8][1]=findViewById(R.id.r8TextView2);
        valuesFuerST[8][2]=findViewById(R.id.r8TextView3);
        valuesFuerST[8][3]=findViewById(R.id.r8TextView4);
        valuesFuerST[8][4]=findViewById(R.id.r8TextView5);
        valuesFuerST[8][5]=findViewById(R.id.r8TextView6);
        valuesFuerST[8][6]=findViewById(R.id.r8TextView7);

        if (nEm == true){
            for (int i = 0; i < 7; i++) {
                rh.connectWS(i,"ABFRAGE");

            }
            nEm  =false;
        }
        fillStundenplan(st.getStundenplanArrList());
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

    class handleButton implements View.OnClickListener {
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.AendernButton) {
                Intent intent = new Intent(DashboardActivity.this, EditTTActivity.class);
                startActivity(intent);
            }
            else if (id == R.id.reloadButton){
                for (int i = 0; i < 7; i++) {
                    rh.connectWS(i,"ABFRAGE");
                }

            }
        }

    }


    public void setTestOutput (String status){
        Toast.makeText(this, status,
                Toast.LENGTH_SHORT).show();
    }

    public Stundenplan getStundenplan(){
        return st;
    }

    public void fillStundenplan(ArrayList<String[]> sammelArr){

        for (int reihe = 1; reihe < 9; reihe++) {

            for (int i = 1; i < 7; i++) {
               valuesFuerST[reihe][i].setText(sammelArr.get(i)[reihe-1]);     // komplizierte umstrukturierung bei der abfrage aufgrund fehlerhafter deklaration der Variablen --> spalte vs reihe

            }//         nach unten  nach rechts        nach rechts   nach unten

        }

    }
}

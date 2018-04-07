package com.timetable.kevin.timetable_manager;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Felix Geier on 04.04.2018.
 */

public class ResponseHandler {
    DashboardActivity dbA = null;

    public ResponseHandler(String fromWhere, Activity c){
        if (fromWhere == "DBA"){
            //c.cast(dbA); //--> FIX PROBLEM DAMIT DES ALS DASHBOARDACTIVITY UNGSECHEN WERT
            dbA = ((DashboardActivity) c);
        }
    }

    public void connectWS(){
        final AsyncHttpClient client = new AsyncHttpClient();
//http://kevinsorg.bplaced.net/MySQLadmin
        client.get("http://kevinsorg.bplaced.net/MySQLadmin/index.php?tb=stunde", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                String testVar = "3";
                dbA.setTestOutput("Success");

                if (response != null) {
                    testVar = "We received some JSON, over!";
                    try {
                          JSONObject jO = response.getJSONObject(0);
                        dbA.printTestDaten(jO.getString("name"));   //--> i bin pro, iatz werd rassiert!


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    testVar=("nothing in the JSON");
                }
                dbA.makeVarDump(testVar);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //super.onFailure(statusCode, headers, responseString, throwable);
                dbA.setTestOutput("Failure");


            }
            @Override
            public void onRetry(int retryNo) {

                dbA.setTestOutput("Retrying...");
            }

        });
        //client.dispose() ??
    }
}

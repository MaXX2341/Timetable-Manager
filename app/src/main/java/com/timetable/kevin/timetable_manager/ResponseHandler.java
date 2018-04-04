package com.timetable.kevin.timetable_manager;

import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Felix Geier on 04.04.2018.
 */

public class ResponseHandler {
    DashboardActivity dbA = null;

    public ResponseHandler(String fromWhere, Class c){
        if (fromWhere == "DBA"){
            DashboardActivity cast = c.cast(); //--> FIX PROBLEM DAMIT DES ALS DASHBOARDACTIVITY UNGSECHEN WERT
            dbA = cast;
        }
    }

    public void connectWS(){
        final AsyncHttpClient client = new AsyncHttpClient();
//http://kevinsorg.bplaced.net/MySQLadmin
        client.get("http://kevinsorg.bplaced.net/MySQLadmin/index.php?tb=stunde", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                JSONArray daten;
                if (response != null) {

                    try {
                        daten = response.getJSONArray(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //"Failure"
            }
            @Override
            public void onRetry(int retryNo) {

                //"retrying..."
            }

        });
    }
}

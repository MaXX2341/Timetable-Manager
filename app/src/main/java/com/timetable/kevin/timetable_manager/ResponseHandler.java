package com.timetable.kevin.timetable_manager;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;

/**
 * Created by Felix Geier on 04.04.2018.
 */

public class ResponseHandler {
    boolean nEm = true;

    DashboardActivity dbA = null;
    EditTTActivity eTT = null;

    String fromWhere;

    public ResponseHandler(String fromWhere, Activity c){
        if (fromWhere == "DBA"){
            dbA = ((DashboardActivity) c);
            this.fromWhere = fromWhere;
        }
        else if (fromWhere == "ETT"){
            eTT = ((EditTTActivity) c);
            this.fromWhere = fromWhere;
        }

    }

    public void connectWS(final int chooser, String whatToDo){
        final AsyncHttpClient client = new AsyncHttpClient();
//http://kevinsorg.bplaced.net/MySQLadmin
        String tabelle = "";

            switch (chooser) {
                case 0:
                    tabelle = "stunde";
                    break;
                case 1:
                    tabelle = "zeit";
                    break;
                case 2:
                    tabelle = "montag";
                    break;
                case 3:
                    tabelle = "dienstag";
                    break;
                case 4:
                    tabelle = "mittwoch";
                    break;
                case 5:
                    tabelle = "donnerstag";
                    break;
                case 6:
                    tabelle = "freitag";
                    break;
                default:// dbA.setTestOutput("chooser error");
            }
            //dbA.makeVarDump(tabelle);

        if (whatToDo == "ABFRAGE"){


        client.get("http://kevinsorg.bplaced.net/MySQLadmin/index.php?tb="+tabelle+"", new JsonHttpResponseHandler() { //TODO url muss customisable sein, um immer das richtige abrufen zu können
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                String testVar = "3";
                if (nEm ==true){
                    //dbA.setTestOutput("Success");
                }

                if (response != null) {
                  //  testVar = "JSON received";
                    getValueArrFromWebservice(response,chooser); //if --> login muss auch hierher         //Daten werden vom Webserver geholt //todo wegen chooser vielleicht problem
                }
                else {
                //    testVar=("nothing in the JSON");
                }

                if (nEm ==true){
                  //  dbA.setTestOutput(testVar);
                }
                nEm  =false;

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //super.onFailure(statusCode, headers, responseString, throwable);
               // dbA.setTestOutput("Failure");

            //    dbA.printTestDaten(chooser+". fail");


            }
            @Override
            public void onRetry(int retryNo) {

           //     dbA.setTestOutput("Retrying...");
            }

        });
    }
    else if (whatToDo == "POST"){
            try {
                postValuesToWebservice();
            } catch (IOException e) {
                dbA.setTestOutput("Post fehlgeschlagen");
            }
        }





        //client.dispose() ??
    }
    public void getValueArrFromWebservice(JSONArray response,int SpaltenNR)  {
        JSONObject jO = null;
        String[]  valueArr = new String[9];

            for (int i = 0; i < 9; i++) {
                try {
                jO = response.getJSONObject(i);

                valueArr[i] = jO.getString("name");    //values werden geholt über "response" und werden dem valueArr hinzugefügt(Zellen --> untereinander)

                } catch (JSONException e) {
           //        dbA.setTestOutput("leeres objekt bei index = "+i);
                    valueArr[i] = "";
                }
            }

            if (fromWhere == "DBA"){
                dbA.getStundenplan().fillArraysWithValue(valueArr,SpaltenNR); // --> Stundenplan der in der dbA erstellt wird, wird hergeholt und darüber wird der Stundenplan befüllt
                dbA.fillStundenplan(dbA.getStundenplan().getStundenplanArrList());
                //  dbA.printTestDaten(jO.getString("name"));   //--> i bin pro, iatz werd rassiert!
            }
            else if(fromWhere == "ETT"){
                eTT.getStundenplan().fillArraysWithValue(valueArr,SpaltenNR);
                eTT.fillStundenplanEditor(eTT.getStundenplan().getStundenplanArrList());
            }




    }

    private void postValuesToWebservice() throws IOException {
        HttpPost post = new HttpPost("http://kevinsorg.bplaced.net/MySQLadmin/index.php?tb=POST");
        HttpResponse httpResponse = null;

        String[][] valuesArr = eTT.getEditTTChangedValues();

        String json = null;
        StringEntity se = null;

        HttpClient c = new HttpClient() {
            @Override
            public HttpParams getParams() {
                return null;
            }

            @Override
            public ClientConnectionManager getConnectionManager() {
                return null;
            }

            @Override
            public HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
                return null;
            }

            @Override
            public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException, ClientProtocolException {
                return null;
            }

            @Override
            public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException, ClientProtocolException {
                return null;
            }

            @Override
            public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
                return null;
            }

            @Override
            public <T> T execute(HttpUriRequest request, cz.msebera.android.httpclient.client.ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
                return null;
            }

            @Override
            public <T> T execute(HttpUriRequest request, cz.msebera.android.httpclient.client.ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
                return null;
            }

            @Override
            public <T> T execute(HttpHost target, HttpRequest request, cz.msebera.android.httpclient.client.ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
                return null;
            }

            @Override
            public <T> T execute(HttpHost target, HttpRequest request, cz.msebera.android.httpclient.client.ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
                return null;
            }
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                json = valuesArr[i][j];
                if (json != "" && json != null) {

                        se = new StringEntity(json);

                    post.setEntity(se);
                    post.setHeader("Accept", "application/json");
                    post.setHeader("Content-type", "application/json");
                    httpResponse = c.execute(post);
                }
            }
        }


    }
}

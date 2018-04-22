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
    boolean nEm = true;

    DashboardActivity dbA = null;
    public ResponseHandler(String fromWhere, Activity c){
        if (fromWhere == "DBA"){
            dbA = ((DashboardActivity) c);
        }
    }

    public void connectWS(final int chooser){
        final AsyncHttpClient client = new AsyncHttpClient();
//http://kevinsorg.bplaced.net/MySQLadmin
        String tabelle = "montag";

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
                default: dbA.setTestOutput("chooser error");
            }
            //dbA.makeVarDump(tabelle);

        client.get("http://kevinsorg.bplaced.net/MySQLadmin/index.php?tb="+tabelle+"", new JsonHttpResponseHandler() { //TODO url muss customisable sein, um immer das richtige abrufen zu können

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                String testVar = "3";
                if (nEm ==true){
                    dbA.setTestOutput("Success");
                }


                if (response != null) {
                    testVar = "JSON received";

                    getValueArrFromWebservice(response,chooser); //if --> login muss auch hierher         //Daten werden vom Webserver geholt //todo wegen chooser vielleicht problem


                }
                else {
                    testVar=("nothing in the JSON");
                }

                if (nEm ==true){
                    dbA.setTestOutput(testVar);
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

                dbA.setTestOutput("Retrying...");
            }

        });


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
             //       dbA.setTestOutput("leeres objekt bei index = "+i);
                    valueArr[i] = "";
                }
            }

            dbA.getStundenplan().fillArraysWithValue(valueArr,SpaltenNR); // --> Stundenplan der in der dbA erstellt wird, wird hergeholt und darüber wird der Stundenplan befüllt
        dbA.fillStundenplan(dbA.getStundenplan().getStundenplanArrList());
          //  dbA.printTestDaten(jO.getString("name"));   //--> i bin pro, iatz werd rassiert!



    }

}

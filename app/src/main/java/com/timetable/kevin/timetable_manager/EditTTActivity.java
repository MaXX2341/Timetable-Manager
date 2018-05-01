package com.timetable.kevin.timetable_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EditTTActivity extends AppCompatActivity {

    EditText[][] valuesFuerST = new EditText[9][7];
    private ResponseHandler rh = new ResponseHandler("ETT", this);
    Stundenplan st = new Stundenplan();
    boolean nEm = true;
    String[][] editTTChangedValues = new String[9][7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tt);
        findViewById(R.id.bestaetigenBT).setOnClickListener(new handleButton());

        valuesFuerST[1][0]=findViewById(R.id.r1EditView1);
        valuesFuerST[1][1]=findViewById(R.id.r1EditView2);
        valuesFuerST[1][2]=findViewById(R.id.r1EditView3);
        valuesFuerST[1][3]=findViewById(R.id.r1EditView4);
        valuesFuerST[1][4]=findViewById(R.id.r1EditView5);
        valuesFuerST[1][5]=findViewById(R.id.r1EditView6);
        valuesFuerST[1][6]=findViewById(R.id.r1EditView7);


        valuesFuerST[2][0]=findViewById(R.id.r2EditView1);
        valuesFuerST[2][1]=findViewById(R.id.r2EditView2);
        valuesFuerST[2][2]=findViewById(R.id.r2EditView3);
        valuesFuerST[2][3]=findViewById(R.id.r2EditView4);
        valuesFuerST[2][4]=findViewById(R.id.r2EditView5);
        valuesFuerST[2][5]=findViewById(R.id.r2EditView6);
        valuesFuerST[2][6]=findViewById(R.id.r2EditView7);


        valuesFuerST[3][0]=findViewById(R.id.r3EditView1);
        valuesFuerST[3][1]=findViewById(R.id.r3EditView2);
        valuesFuerST[3][2]=findViewById(R.id.r3EditView3);
        valuesFuerST[3][3]=findViewById(R.id.r3EditView4);
        valuesFuerST[3][4]=findViewById(R.id.r3EditView5);
        valuesFuerST[3][5]=findViewById(R.id.r3EditView6);
        valuesFuerST[3][6]=findViewById(R.id.r3EditView7);


        valuesFuerST[4][0]=findViewById(R.id.r4EditView1);
        valuesFuerST[4][1]=findViewById(R.id.r4EditView2);
        valuesFuerST[4][2]=findViewById(R.id.r4EditView3);
        valuesFuerST[4][3]=findViewById(R.id.r4EditView4);
        valuesFuerST[4][4]=findViewById(R.id.r4EditView5);
        valuesFuerST[4][5]=findViewById(R.id.r4EditView6);
        valuesFuerST[4][6]=findViewById(R.id.r4EditView7);


        valuesFuerST[5][0]=findViewById(R.id.r5EditView1);
        valuesFuerST[5][1]=findViewById(R.id.r5EditView2);
        valuesFuerST[5][2]=findViewById(R.id.r5EditView3);
        valuesFuerST[5][3]=findViewById(R.id.r5EditView4);
        valuesFuerST[5][4]=findViewById(R.id.r5EditView5);
        valuesFuerST[5][5]=findViewById(R.id.r5EditView6);
        valuesFuerST[5][6]=findViewById(R.id.r5EditView7);


        valuesFuerST[6][0]=findViewById(R.id.r6EditView1);
        valuesFuerST[6][1]=findViewById(R.id.r6EditView2);
        valuesFuerST[6][2]=findViewById(R.id.r6EditView3);
        valuesFuerST[6][3]=findViewById(R.id.r6EditView4);
        valuesFuerST[6][4]=findViewById(R.id.r6EditView5);
        valuesFuerST[6][5]=findViewById(R.id.r6EditView6);
        valuesFuerST[6][6]=findViewById(R.id.r6EditView7);


        valuesFuerST[7][0]=findViewById(R.id.r7EditView1);
        valuesFuerST[7][1]=findViewById(R.id.r7EditView2);
        valuesFuerST[7][2]=findViewById(R.id.r7EditView3);
        valuesFuerST[7][3]=findViewById(R.id.r7EditView4);
        valuesFuerST[7][4]=findViewById(R.id.r7EditView5);
        valuesFuerST[7][5]=findViewById(R.id.r7EditView6);
        valuesFuerST[7][6]=findViewById(R.id.r7EditView7);


        valuesFuerST[8][0]=findViewById(R.id.r8EditView1);
        valuesFuerST[8][1]=findViewById(R.id.r8EditView2);
        valuesFuerST[8][2]=findViewById(R.id.r8EditView3);
        valuesFuerST[8][3]=findViewById(R.id.r8EditView4);
        valuesFuerST[8][4]=findViewById(R.id.r8EditView5);
        valuesFuerST[8][5]=findViewById(R.id.r8EditView6);
        valuesFuerST[8][6]=findViewById(R.id.r8EditView7);

        if (nEm == true){
            for (int i = 0; i < 7; i++) {
                rh.connectWS(i,"ABFRAGE");
            }
            nEm = false;
        }
        fillStundenplanEditor(st.getStundenplanArrList());
    }

    class handleButton implements View.OnClickListener {
        public void onClick(View v) {
            //an den webservice senden

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    try {
                        if (valuesFuerST[i][j].getText().toString() != null) {
                            editTTChangedValues[i][j] = valuesFuerST[i][j].getText().toString();
                        }
                    }catch (Exception e){}


                }
            }

          //  setTestOutput(valuesFuerST[1][1].getText().toString());


            rh.connectWS(0,"POST");
            Intent intent = new Intent(EditTTActivity.this, DashboardActivity.class);
            startActivity(intent);
        }

    }
    public void fillStundenplanEditor(ArrayList<String[]> sammelArr){

        for (int reihe = 1; reihe < 9; reihe++) {

            for (int i = 1; i < 7; i++) {
                valuesFuerST[reihe][i].setText(sammelArr.get(i)[reihe-1]);     // komplizierte umstrukturierung bei der abfrage aufgrund fehlerhafter deklaration der Variablen --> spalte vs reihe

            }//         nach unten  nach rechts        nach rechts   nach unten

        }

    }
    public Stundenplan getStundenplan(){
        return st;
    }

    private void setTestOutput (String status){
        Toast.makeText(this, status,
                Toast.LENGTH_SHORT).show();
    }
    public String[][] getEditTTChangedValues(){
        return editTTChangedValues;
    }
}

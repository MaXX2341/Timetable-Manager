package com.timetable.kevin.timetable_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditTTActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tt);
        findViewById(R.id.bestaetigenBT).setOnClickListener(new handleButton());
    }
    class handleButton implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(EditTTActivity.this, DashboardActivity.class);
            startActivity(intent);
        }

    }

}

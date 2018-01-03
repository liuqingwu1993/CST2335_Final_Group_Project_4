package com.example.sophia.cst2335_final_group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button activityTrackingButton = findViewById(R.id.activity_tracking);
        final Intent activityTrackingIntent = new Intent(this, TrackingActivity.class);
        activityTrackingButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(activityTrackingIntent);
            }
        });

    }}


package com.example.sophia.cst2335_final_group_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class TeamMainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent1 = new Intent(TeamMainActivity.this, AutoList.class);
                    startActivityForResult(intent1, 5);
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(TeamMainActivity.this, Food_Nutrition_Tracker.class);
                    startActivityForResult(intent, 5);
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(TeamMainActivity.this, EntryList.class);
                    startActivityForResult(intent2, 5);
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_notifications1:
                    Intent intent3 = new Intent(TeamMainActivity.this, StartActivity.class);
                    startActivityForResult(intent3, 5);
                    mTextMessage.setText("Activity Tracking");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

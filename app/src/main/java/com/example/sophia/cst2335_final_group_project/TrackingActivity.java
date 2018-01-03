package com.example.sophia.cst2335_final_group_project;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class TrackingActivity extends AppCompatActivity {
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";
    private static final String ACTIVITY_NAME = "ActivityTracking";
    private SQLiteDatabase sqldb;
    private ArrayList<Map> activityList = new ArrayList();
    private Cursor c;
    private ProgressBar pBar;
    private ObjectAnimator animation;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        final Intent start = new Intent(this, TrackingActivity.class);
        switch (item.getItemId()) {
            case R.id.t_help:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle(getResources().getString(R.string.t_help_title));
                LayoutInflater inflater = this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.fragment_activity_tracking_help, null);
                ((TextView)dialogView.findViewById(R.id.t_help)).setMovementMethod(new ScrollingMovementMethod());
                builder1.setView(dialogView);
                // Add the buttons
                builder1.setPositiveButton(R.string.t_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        startActivity(start);
                    }});
                builder1.setNegativeButton(R.string.t_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }});
                // Create the AlertDialog
                AlertDialog dialog2 = builder1.create();
                dialog2.show();
                return true;
            case R.id.t_stats:
                TrackingStatisticsFragment statsFragment = new TrackingStatisticsFragment();
                statsFragment.init(this.activityList);
                addFragment(statsFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_tracking, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        pBar = (ProgressBar) findViewById(R.id.t_progressBar);
        pBar.setVisibility(View.VISIBLE);

        InitActivityTracking init = new InitActivityTracking();
        init.execute();

    }

    class InitActivityTracking extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {

            SystemClock.sleep(100);
            pBar.setProgress(20);
            TrackingDatabaseHelper dbHelper = new TrackingDatabaseHelper(TrackingActivity.this);
            sqldb = dbHelper.getWritableDatabase();
            SystemClock.sleep(200);
            pBar.setProgress(30);
            //populate activity list
            c = sqldb.rawQuery("select * from " + TrackingDatabaseHelper.TABLE_NAME,null );
            c.moveToFirst();
            while(!c.isAfterLast() ) {
                Map<String, Object> row = new HashMap<>();
                row.put(ID, c.getString(c.getColumnIndex(TrackingDatabaseHelper.ID)));
                String type = c.getString(c.getColumnIndex(TrackingDatabaseHelper.TYPE));
                String time = c.getString(c.getColumnIndex(TrackingDatabaseHelper.TIME));
                String duration = c.getString(c.getColumnIndex(TrackingDatabaseHelper.DURATION));
                String comment = c.getString(c.getColumnIndex(TrackingDatabaseHelper.COMMENT));
                row.put(TrackingDatabaseHelper.TYPE, type);
                row.put(TrackingDatabaseHelper.TIME, time);
                row.put(TrackingDatabaseHelper.DURATION, duration);
                row.put(TrackingDatabaseHelper.COMMENT, comment);
                row.put(DESCRIPTION, getResources().getString(R.string.t_start_at) + time +", " + type +
                        getResources().getString(R.string.t_for) + duration +
                        getResources().getString(R.string.t_min_note)+ comment);

                activityList.add(row);
                c.moveToNext();
            }
            SystemClock.sleep(500);
            pBar.setProgress(80);
            final Intent intent = new Intent(TrackingActivity.this, TrackingAddActivity.class);
            findViewById(R.id.t_newActivity).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(intent);
                }
            });
            SystemClock.sleep(100);
            pBar.setProgress(10);
            return null;
        }

        protected void onProgressUpdate(Integer ...values){
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            pBar.setVisibility(View.VISIBLE );
            TrackingListViewFragment listViewFragment = new TrackingListViewFragment();
            listViewFragment.init(activityList);

            addFragment(listViewFragment);
        }
    }

    private void addFragment(Fragment fragment) {

        FragmentManager fragmentManager =getFragmentManager();
        //remove previous fragment
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.t_listview_Frame, fragment).addToBackStack(null).commit();
    }


}
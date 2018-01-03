package com.example.sophia.cst2335_final_group_project;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.app.AlertDialog;

import java.util.List;

public class Food_Nutrition_Tracker extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Nutrition_History.OnFragmentSendValue {

    private ProgressBar progressB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__nutrition__tracker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressB = (ProgressBar)findViewById(R.id.progressBar);
        progressB.setVisibility(View.VISIBLE);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,
                new Nutrition_History()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.food__nutrition__tracker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_help) {

            android.support.v7.app.AlertDialog.Builder b2 = new android.support.v7.app.AlertDialog.Builder(this);
            LayoutInflater inf = this.getLayoutInflater();
            final View myView = inf.inflate(R.layout.diaglog,null);
            b2.setView(myView);

            b2.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id){

                }
            });

            android.support.v7.app.AlertDialog myDialog = b2.create();
            myDialog.show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_history_nutrition) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,
                    new Nutrition_History()).commit();
        } else if (id == R.id.nav_today_nutrition) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,
                    new Nutrition_Today()).commit();
        } else if (id == R.id.nav_about){
            Toast.makeText(this,R.string.version_info, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_avg){
            fragmentManager.beginTransaction().replace(R.id.content_frame,
                    new Nutrition_Summary()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSentText(Food_Nutrition_Model delModel){

        Nutrition_Edit edit = new Nutrition_Edit();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content_frame, edit);
        transaction.commit();
        edit.getData(delModel);
    }

}

package com.example.sophia.cst2335_final_group_project;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryList extends AppCompatActivity {
    ThermostatDataBaseAdapter thermostatDataBaseAdapter;
    RecyclerView recyclerView;
    EntryAdapter mAdapter;
    int i=0;
    List<HashMap<String ,String>> autoMapList=new ArrayList<>();

    List<EntryModel> entryModelArrayList =new ArrayList<>();
    public static String searchName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_list_t);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        mAdapter = new EntryAdapter(entryModelArrayList, EntryList.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        thermostatDataBaseAdapter =new ThermostatDataBaseAdapter(this);
        thermostatDataBaseAdapter.open();
        Intent intent=getIntent();
        autoMapList= thermostatDataBaseAdapter.GetAllEntries();

        //  Log.d("REG_TOKEN","check");

        for (int i=0;i<autoMapList.size();i++){

//            Toast.makeText(this, ""+autoMapList.get(i).toString(), Toast.LENGTH_SHORT).show();
            Log.d("REG_TOKEN",autoMapList.get(i).toString());
            Map<String ,String> singleEntry=autoMapList.get(i);
//
            EntryModel entryModel = new EntryModel(singleEntry.get("Days"),singleEntry.get("Time"),singleEntry.get("Temperature")
                    ,Integer.parseInt(singleEntry.get("ID")));
            entryModelArrayList.add(entryModel);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_t, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==R.id.activity_help)
        {
            new MaterialDialog.Builder(this)
                    .title("Thermostate")
                    .content(R.string.contantT)
                    .positiveText("Ok")
                    .show();

        }
        if (id==R.id.action_add)
        {
            startActivity(new Intent(getApplicationContext(),AddEntry.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



}

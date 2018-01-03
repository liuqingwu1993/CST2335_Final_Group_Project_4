package com.example.sophia.cst2335_final_group_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.afollestad.materialdialogs.MaterialDialog;


public class AutoList extends AppCompatActivity {
    AutoDataBaseAdapter autoDataBaseAdapter;
    RecyclerView recyclerView;
    RoutineAdpater mAdapter;
    AutoModel autoModel;
    Button btn_routine;
    int i=0;
    List<HashMap<String ,String>> autoMapList=new ArrayList<>();

    List<AutoModel> autoModelArrayList =new ArrayList<>();
    public static String searchName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_list);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        btn_routine= (Button) findViewById(R.id.add_routine);
        mAdapter = new RoutineAdpater(autoModelArrayList, AutoList.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        autoDataBaseAdapter =new AutoDataBaseAdapter(this);
        autoDataBaseAdapter.open();
        Intent intent=getIntent();
        autoMapList=autoDataBaseAdapter.GetAllEntries();



        for (int i=0;i<autoMapList.size();i++){

//            Toast.makeText(this, ""+autoMapList.get(i).toString(), Toast.LENGTH_SHORT).show();
            Log.d("REG_TOKEN",autoMapList.get(i).toString());
            Map<String ,String> singleEntry=autoMapList.get(i);

            AutoModel autoModel = new AutoModel(Integer.parseInt(singleEntry.get("ID")),
                    singleEntry.get("Litres"),singleEntry.get("PRICE"),singleEntry.get("KILO_METERS"),singleEntry.get("CREATED_AT"));
            autoModelArrayList.add(autoModel);
            mAdapter.notifyDataSetChanged();
        }

        btn_routine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddAuto.class);

                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Toast.makeText(this, "setting click", Toast.LENGTH_SHORT).show();
            new MaterialDialog.Builder(this)
                    .title("AutoMobile")
                    .content(R.string.contant)
                    .positiveText("Ok")
                    .show();
            return true;
        }
        if (id==R.id.action_report)
        {
            startActivity(new Intent(getApplicationContext(),DetailActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }



}

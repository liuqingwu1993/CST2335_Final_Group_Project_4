package com.example.sophia.cst2335_final_group_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class AddAuto extends AppCompatActivity {
    EditText ed_liters,ed_price,ed_kilometer;
    Button btn_submit;
    AutoDataBaseAdapter autoDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        autoDataBaseAdapter =new AutoDataBaseAdapter(this);
        autoDataBaseAdapter.open();

        ed_liters= (EditText) findViewById(R.id.auto_liters);
        ed_price= (EditText) findViewById(R.id.auto_price);
        ed_kilometer= (EditText) findViewById(R.id.auto_Kmiters);
        btn_submit=(Button)findViewById(R.id.submit_auto);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ed_liters.getText().toString().equals("") &&
                        ed_price.getText().toString().equals("") && ed_kilometer.getText().toString().equals("")  )
                {
                    Toast.makeText(AddAuto.this, "Please Fill All Field", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    autoDataBaseAdapter.InsertEntry(ed_liters.getText().toString(),
                            ed_price.getText().toString(),ed_kilometer.getText().toString(),getDateTime());
                    Intent intent=new Intent(getApplicationContext(),AutoList.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}

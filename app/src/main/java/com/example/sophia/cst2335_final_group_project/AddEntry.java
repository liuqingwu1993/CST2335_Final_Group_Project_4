package com.example.sophia.cst2335_final_group_project;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddEntry extends AppCompatActivity {
    EditText ed_temperature;
    Button btn_submit,show_button;
    ThermostatDataBaseAdapter thermostatDataBaseAdapter;
    String SelectedAdapterItem,SelectTime;
    TextView tv_viewTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine_t);

        thermostatDataBaseAdapter =new ThermostatDataBaseAdapter(this);
        thermostatDataBaseAdapter.open();

        // Spinner element
        final String[] currencytype = {
                "Saturday", "Sunday", "Monday","Tuesday","Wednesday","Thursday","Friday"};
        Spinner   price_Spinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<String> adaptercurrency = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, currencytype);
        price_Spinner.setAdapter(adaptercurrency);

        price_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SelectedAdapterItem=adapterView.getItemAtPosition(i).toString();
                //  Toast.makeText(AddEntry.this, ""+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tv_viewTime= (TextView) findViewById(R.id.showtime);
        show_button= (Button) findViewById(R.id.pickTime);
        ed_temperature= (EditText) findViewById(R.id.auto_liters);
        show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEntry.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tv_viewTime.setText( selectedHour + ":" + selectedMinute);
                        SelectTime= selectedHour + ":" + selectedMinute;
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        btn_submit=(Button)findViewById(R.id.submit_auto);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ed_temperature.getText().toString().equals("") || tv_viewTime.getText().toString().equals("") )
                {
                    Toast.makeText(AddEntry.this, "Please Fill All Field", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    thermostatDataBaseAdapter.InsertEntry(SelectedAdapterItem,SelectTime,ed_temperature.getText().toString());
                    Intent intent=new Intent(getApplicationContext(),EntryList.class);
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

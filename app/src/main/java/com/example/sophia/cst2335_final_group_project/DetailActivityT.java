package com.example.sophia.cst2335_final_group_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.thunder413.datetimeutils.DateTimeUnits;
import com.github.thunder413.datetimeutils.DateTimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DetailActivityT extends AppCompatActivity {
    List<HashMap<String ,String>> autoMapList=new ArrayList<>();
    ThermostatDataBaseAdapter thermostatDataBaseAdapter;
    float totalgas=0,avrageGas=0,totalprice=0,AvragePrice;
    int count=0;
    TextView tv_avrage_prive,tv_Total_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_t);
        tv_avrage_prive= (TextView) findViewById(R.id.avragePrice);
        tv_Total_price= (TextView) findViewById(R.id.totalGas);
        thermostatDataBaseAdapter =new ThermostatDataBaseAdapter(this);
        thermostatDataBaseAdapter.open();

        autoMapList= thermostatDataBaseAdapter.GetAllEntries();

        for (int i=0;i<autoMapList.size();i++){

//            Toast.makeText(this, ""+autoMapList.get(i).toString(), Toast.LENGTH_SHORT).show();
            Log.d("REG_TOKEN",autoMapList.get(i).toString());
            Map<String ,String> singleEntry=autoMapList.get(i);
            long days=CalculateDate(getDateTime(),singleEntry.get("CREATED_AT"));
            EntryModel entryModel = new EntryModel(singleEntry.get("Days"),singleEntry.get("Time"),
                    singleEntry.get("Temperature"),Integer.parseInt(singleEntry.get("ID")));

            if (days<31)
            {
//                totalgas=totalgas+Float.parseFloat(singleEntry.get("Litres"));
//                totalprice=totalprice+Float.parseFloat(singleEntry.get("PRICE"));
//                count++;
            }
        }

        AvragePrice=totalprice/totalgas;
        tv_avrage_prive.setText("Average Price of Gas Last Month is \n \t\t"+AvragePrice );
        tv_Total_price.setText("Total Price of Gas Last Month is \n \t\t" + totalprice);
    }


    public long CalculateDate(String currentdatge,String database)
    {
        DateTimeUtils obj = new DateTimeUtils();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long  diff = 0;
        try {
            Date date1 = simpleDateFormat.parse(database);
            Date date2 = simpleDateFormat.parse(currentdatge);

            diff =obj.getDateDiff(date2, date1, DateTimeUnits.MILLISECONDS);
            Log.d("REG_TOKEN","startDate : " + date1);
            Log.d("REG_TOKEN","endDate : "+ date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  printDifference(diff);
//1 minute = 60 seconds
//1 hour = 60 x 60 = 3600
//1 day = 3600 x 24 = 86400


    }
    public long  printDifference(long different) {
        //milliseconds
        // long different = endDate.getTime() - startDate.getTime();

        //   System.out.println("startDate : " + startDate);
        // System.out.println("endDate : "+ endDate);
        Log.d("REG_TOKEN","different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        Log.d("REG_TOKEN", "days " +elapsedDays +"  hours "+elapsedHours);
        return  elapsedDays;
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}

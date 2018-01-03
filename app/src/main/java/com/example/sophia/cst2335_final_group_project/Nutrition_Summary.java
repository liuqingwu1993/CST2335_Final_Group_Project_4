package com.example.sophia.cst2335_final_group_project;


import android.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Nutrition_Summary extends Fragment {

    View myView;
    private NutritionHelper dbHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.nutrition_summary, container, false);

        dbHelper  = new  NutritionHelper(getActivity());

        List<Food_Nutrition_Model> nList = dbHelper.getAlFoodNutritionsObject();
        List<String> dateList = new ArrayList<String>();

        List<String> finialResult = new ArrayList<String>();;

        for(int i=0; i<nList.size(); i++)
        {
            dateList.add(nList.get(i).eat_date);
        }
        Set<String> hs = new HashSet<>();
        hs.addAll(dateList);
        dateList.clear();
        dateList.addAll(hs);

        Collections.sort(dateList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        for(int i=0; i<dateList.size(); i++)
        {
            List<Food_Nutrition_Model> tmpList = dbHelper.getFoodNutritionModelByDate(dateList.get(i));
            int totalCal = 0;
            for(int j=0 ; j<tmpList.size(); j++){
                totalCal = totalCal+ tmpList.get(j).calories;
            }
            double avg = totalCal/tmpList.size();
            String msg = dateList.get(i) + " average calories: " + avg;
            finialResult.add(msg);
        }

        ListView mListView;
        mListView = myView.findViewById(R.id.nutritionListSum);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1,
                finialResult.toArray(new String[finialResult.size()]));
        mListView.setAdapter(adapter);



        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DATE);

        String today =  mYear + "/" + mMonth + "/" + mDay;
        List<Food_Nutrition_Model> lastList;
        String lastDate;

        String test = dateList.get(dateList.size()-1);

        if (today.equals(test)){

            try
            {
                lastList = dbHelper.getFoodNutritionModelByDate(dateList.get(dateList.size()-2));
                lastDate = dateList.get(dateList.size()-2);
            }catch (Exception ex){
                lastList = dbHelper.getFoodNutritionModelByDate(dateList.get(dateList.size()-1));
                lastDate = dateList.get(dateList.size()-1);
            }
        }
        else
        {
            lastList = dbHelper.getFoodNutritionModelByDate(dateList.get(dateList.size()-1));
            lastDate = dateList.get(dateList.size()-1);
        }
        int totalCal = 0;
        for(int j=0 ; j<lastList.size(); j++){
            totalCal = totalCal+ lastList.get(j).calories;
        }
        String msg2 = lastDate + ": " + totalCal;
        TextView viewTotalCal;
        viewTotalCal = myView.findViewById(R.id.totalCalValue);
        viewTotalCal.setText(msg2);


        return myView;
    }


}



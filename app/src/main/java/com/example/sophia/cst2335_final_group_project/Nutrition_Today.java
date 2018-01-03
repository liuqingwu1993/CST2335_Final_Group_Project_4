package com.example.sophia.cst2335_final_group_project;

import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import java.util.Calendar;


public class Nutrition_Today extends Fragment {

    View myView;
    EditText fn;
    EditText cal;
    EditText fat;
    EditText car;
    EditText pro;
    EditText ed;
    EditText et;
    Button btAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.nutrition_today, container, false);

        fn = (EditText)myView.findViewById(R.id.editFoodName);
        cal = (EditText)myView.findViewById(R.id.editCalories);
        fat = (EditText)myView.findViewById(R.id.editFat);
        car = (EditText)myView.findViewById(R.id.editCarbohydrate);
        pro = (EditText)myView.findViewById(R.id.editProtein);
        ed = (EditText)myView.findViewById(R.id.editDate);
        et = (EditText)myView.findViewById(R.id.editTime);
        btAdd = (Button) myView.findViewById(R.id.buttonSubmit);


        Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DATE);


        et.setText(mHour +":"+ mMinute);
        ed.setText( mYear + "/" + mMonth + "/" + mDay);

        final NutritionHelper db  = new  NutritionHelper(getActivity());

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Food_Nutrition_Model myModel = new Food_Nutrition_Model();

                myModel.food_name = fn.getText().toString();

                myModel.calories = Integer.parseInt(cal.getText().toString());

                myModel.fat = Integer.parseInt(fat.getText().toString());
                myModel.carbohydrate = Integer.parseInt(car.getText().toString());
                myModel.protein = Integer.parseInt(pro.getText().toString());

                myModel.eat_date = ed.getText().toString();
                myModel.eat_time = et.getText().toString();

                db.addNutrition(myModel);

                Nutrition_History his = new Nutrition_History();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.content_frame, his);
                transaction.commit();
            }
        });



        return myView;
    }

}

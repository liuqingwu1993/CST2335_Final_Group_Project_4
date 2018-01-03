package com.example.sophia.cst2335_final_group_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;


public class Nutrition_Edit extends Fragment {


    EditText fn;
    EditText cal;
    EditText fat;
    EditText car;
    EditText pro;
    EditText ed;
    EditText et;
    Button btAdd;
    View myView;

    public Food_Nutrition_Model editModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.nutrition_edit, container, false);

        fn = (EditText)myView.findViewById(R.id.editFoodNameE);
        cal = (EditText)myView.findViewById(R.id.editCaloriesE);
        fat = (EditText)myView.findViewById(R.id.editFatE);
        car = (EditText)myView.findViewById(R.id.editCarbohydrateE);
        pro = (EditText)myView.findViewById(R.id.editProteinE);
        ed = (EditText)myView.findViewById(R.id.editDateE);
        et = (EditText)myView.findViewById(R.id.editTimeE);
        btAdd = (Button) myView.findViewById(R.id.buttonUpdate);

        fn.setText(editModel.food_name.toString());
        cal.setText(Integer.toString(editModel.calories));
        fat.setText(Integer.toString(editModel.fat));
        car.setText(Integer.toString(editModel.carbohydrate));
        pro.setText(Integer.toString(editModel.protein));
        ed.setText(editModel.eat_date.toString());
        et.setText(editModel.eat_time.toString());

        final NutritionHelper db  = new  NutritionHelper(getActivity());

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Food_Nutrition_Model myModel = new Food_Nutrition_Model();
                myModel.id = editModel.id;
                myModel.food_name = fn.getText().toString();

                myModel.calories = Integer.parseInt(cal.getText().toString());

                myModel.fat = Integer.parseInt(fat.getText().toString());
                myModel.carbohydrate = Integer.parseInt(car.getText().toString());
                myModel.protein = Integer.parseInt(pro.getText().toString());

                myModel.eat_date = ed.getText().toString();
                myModel.eat_time = et.getText().toString();
                db.update(myModel);

                Nutrition_History his = new Nutrition_History();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.content_frame, his);
                transaction.commit();
            }
        });
        return myView;
    }

    public void getData(Food_Nutrition_Model delModel){
        editModel = delModel;

    }
}

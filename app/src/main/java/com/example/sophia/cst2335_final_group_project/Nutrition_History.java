package com.example.sophia.cst2335_final_group_project;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class Nutrition_History extends Fragment {

    View myView;
    private NutritionHelper dbHelper;
    private SimpleCursorAdapter dataAdapter;
    OnFragmentSendValue mSendText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.nutrition_history, container, false);

        dbHelper  = new  NutritionHelper(getActivity());


        displayListView();

        return myView;
    }

    private void displayListView() {

        Cursor cursor = dbHelper.getAlFoodNutritions();

        String[] columns = new String[] {
                NutritionHelper.KEY_FOOD_NAME,
                NutritionHelper.KEY_DATE,
                NutritionHelper.KEY_TIME,
                NutritionHelper.KEY_CALORIES
        };

        int[] to = new int[] {
                R.id.s_food_name,
                R.id.s_date,
                R.id.s_eat_time,
                R.id.s_cal
        };

        dataAdapter = new SimpleCursorAdapter(
                myView.getContext(),
                R.layout.single_row_item,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) myView.findViewById(R.id.nutritionList);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                final String nId =
                        cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                Snackbar snackbar = Snackbar
                        .make( getActivity().findViewById(android.R.id.content),"Select Item ID: " + nId, Snackbar.LENGTH_LONG);
                snackbar.show();

                final int myId = Integer.parseInt(nId);

                Food_Nutrition_Model detailModel = dbHelper.getFoodNutritionModel(myId);
                String msg = "Food Name: " + detailModel.food_name + "\n"
                        + "Food Calories: " + detailModel.calories + "\n"
                        + "Food Fat: " + detailModel.fat + "\n"
                        + "Food Carbohydrate: " + detailModel.carbohydrate + "\n"
                        + "Food Protein" + detailModel.protein + "\n"
                        + "Had it on : " + detailModel.eat_date + " " + detailModel.eat_time + "\n"
                        + "ID: " + detailModel.id;


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(msg)
                        .setTitle("Detail");
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Food_Nutrition_Model delModel = dbHelper.getFoodNutritionModel(myId);

                        dbHelper.deleteFoodNutrition(delModel);

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(Nutrition_History.this).attach(Nutrition_History.this).commit();
                    }
                });
                builder.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Food_Nutrition_Model delModel = dbHelper.getFoodNutritionModel(myId);
                        mSendText.onSentText(delModel);
                    }
                });
                builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }


    public interface OnFragmentSendValue{
        public void onSentText(Food_Nutrition_Model delModel);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mSendText = (OnFragmentSendValue)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}



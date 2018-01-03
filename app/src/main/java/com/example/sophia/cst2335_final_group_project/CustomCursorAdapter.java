package com.example.sophia.cst2335_final_group_project;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter{

    public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.single_row_item, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // here we are setting our data
        // that means, take the data from the cursor and put it in views

        TextView textViewEatTime= (TextView) view.findViewById(R.id.s_eat_time);
        textViewEatTime.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));

        TextView textViewFoodName = (TextView) view.findViewById(R.id.s_food_name);
        textViewFoodName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
    }

}

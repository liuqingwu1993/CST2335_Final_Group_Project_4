package com.example.sophia.cst2335_final_group_project;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Adeel Hamza on 2/14/2017.
 */

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.MyViewHolder> {
    private List<EntryModel> entrylist;
    ArrayList<Integer> idList=new ArrayList<>();
    private EntryModel entryModel;
    Bitmap profileImage;
    private EntryAdapter adapter;

  private Context mContext;
    public ThermostatDataBaseAdapter thermostateAdapter;
    public EntryAdapter(List<EntryModel> entryModels, Context context) {
        this.entrylist = entryModels;
        this.mContext=context;
        this.adapter = this;
        thermostateAdapter=new ThermostatDataBaseAdapter(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_routine_t, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        entryModel = entrylist.get(position);
        holder.tv_days.setText(entryModel.getDays());
        holder.tv_temperature.setText(entryModel.getTemperature());
        holder.tv_time.setText(entryModel.getTime());
       // holder.tv_auto_times.setText(entryModel.getTimeStamp());
        idList.add(entryModel.getId());

    }

    @Override
    public int getItemCount() {
        return entrylist.size();
    }

    public void addToProduct(EntryModel entryModel) {
        entrylist.add(entryModel);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_days, tv_time, tv_temperature;


        public MyViewHolder(final View itemView) {
            super(itemView);
            tv_days = (TextView) itemView.findViewById(R.id.auto_single_litres);
            tv_time = (TextView) itemView.findViewById(R.id.auto_single_price);
            tv_temperature = (TextView) itemView.findViewById(R.id.auto_single_kilometer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    CharSequence colors[] = new CharSequence[] {"Edit ", "Delete"};

                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);
                    builder.setItems(colors, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // the user clicked on colors[which]

                            switch (which)
                            {
                                case 0:
                                    // Toast.makeText(mContext, "Add Seller Click", Toast.LENGTH_SHORT).show();
                                    showDialog();

                                    break;
                                case 1:
                                    // Toast.makeText(mContext, "Edit Click", Toast.LENGTH_SHORT).show();
                                    int value= thermostateAdapter.DeleteEntry(idList.get(getAdapterPosition()));
                                    Toast.makeText(mContext,"Delete Value Successfully.. " , Toast.LENGTH_SHORT).show();
                                    ((Activity)mContext).recreate();
                                    break;
                                default:
                                    Toast.makeText(mContext, "Wrong Click", Toast.LENGTH_SHORT).show();

                                    break;
                            }
                        }
                    });
                    builder.show();

                }
            });
        }

        public void showDialog(){

            thermostateAdapter.open();
            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            LinearLayout layout = new LinearLayout(mContext);
            layout.setOrientation(LinearLayout.VERTICAL);
            final EditText days = new EditText(mContext);
            final EditText time = new EditText(mContext);
            final EditText temperature = new EditText(mContext);
            alert.setMessage("Here you change value if you want");
            alert.setTitle("Update Value");
            days.setText(tv_days.getText().toString());
            time.setText(tv_time.getText().toString());
            temperature.setText(tv_temperature.getText().toString());
            layout.addView(days);
            layout.addView(time);
            layout.addView(temperature);
            alert.setView(layout);
            alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //What ever you want to do with the value
                    Editable YouEditTextValue = days.getText();
                    if (days.getText().toString().equalsIgnoreCase("")){
                        Toast.makeText(mContext, "Wrong Url", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                                thermostateAdapter.updateEntry(idList.get(getAdapterPosition()),days.getText().toString(),
                                        time.getText().toString(),temperature.getText().toString());
//                         Toast.makeText(mContext, "value saved", Toast.LENGTH_SHORT).show();

                        ((Activity)mContext).recreate();
                    }

                }
            });

            alert.setNegativeButton("Save As New", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    thermostateAdapter.InsertEntry(days.getText().toString(),
                            time.getText().toString(),temperature.getText().toString());
                    ((Activity)mContext).recreate();
                    // what ever you want to do with No option.
                }
            });

            alert.show();
        }
    }



}

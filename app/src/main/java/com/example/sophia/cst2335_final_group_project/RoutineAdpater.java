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

public class RoutineAdpater extends RecyclerView.Adapter<RoutineAdpater.MyViewHolder> {
    private List<AutoModel> routineList;
    ArrayList<Integer> idList=new ArrayList<>();
    private AutoModel autoModel;
    Bitmap profileImage;
    private  RoutineAdpater adapter;

  private Context mContext;
    public AutoDataBaseAdapter loginbaseAdapter;
    public RoutineAdpater(List<AutoModel> autoModels, Context context) {
        this.routineList = autoModels;
        this.mContext=context;
        this.adapter = this;
        loginbaseAdapter=new AutoDataBaseAdapter(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_routine, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        autoModel = routineList.get(position);
        holder.tv_auto_litres.setText(autoModel.getAuto_litres());
        holder.tv_auto_kilometers.setText(autoModel.getAuto_kilometer());
        holder.tv_auto_price.setText(autoModel.getAuto_price());
        holder.tv_auto_times.setText(autoModel.getTimeStamp());
        idList.add(autoModel.getId());

    }

    @Override
    public int getItemCount() {
        return routineList.size();
    }

    public void addToProduct(AutoModel autoModel) {
        routineList.add(autoModel);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_auto_litres, tv_auto_price, tv_auto_kilometers,tv_auto_times;


        public MyViewHolder(final View itemView) {
            super(itemView);
            tv_auto_litres = (TextView) itemView.findViewById(R.id.auto_single_litres);
            tv_auto_price = (TextView) itemView.findViewById(R.id.auto_single_price);
            tv_auto_kilometers = (TextView) itemView.findViewById(R.id.auto_single_kilometer);
            tv_auto_times = (TextView) itemView.findViewById(R.id.auto_single_time);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
//                    Toast.makeText(mContext, "long click ", Toast.LENGTH_SHORT).show();
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
                                  int value= loginbaseAdapter.DeleteEntry(idList.get(getAdapterPosition()));
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

                    return false;
                }
            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(final View v) {
//
//
//                }
//            });
        }

        public void showDialog(){

            loginbaseAdapter.open();
            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            LinearLayout layout = new LinearLayout(mContext);
            layout.setOrientation(LinearLayout.VERTICAL);
            final EditText ed_liter = new EditText(mContext);
            final EditText ed_price_dialog = new EditText(mContext);
            final EditText ed_kilometer_dialog = new EditText(mContext);
            alert.setMessage("Here you change value if you want");
            alert.setTitle("Update Value");
            ed_liter.setText(tv_auto_litres.getText().toString());
            ed_price_dialog.setText(tv_auto_price.getText().toString());
            ed_kilometer_dialog.setText(tv_auto_kilometers.getText().toString());
            layout.addView(ed_liter);
            layout.addView(ed_price_dialog);
            layout.addView(ed_kilometer_dialog);
            alert.setView(layout);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //What ever you want to do with the value
                    Editable YouEditTextValue = ed_liter.getText();
                    if (ed_liter.getText().toString().equalsIgnoreCase("")){
                        Toast.makeText(mContext, "Wrong Url", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                                loginbaseAdapter.updateEntry(idList.get(getAdapterPosition()),ed_liter.getText().toString(),
                                        ed_price_dialog.getText().toString(),ed_kilometer_dialog.getText().toString());
//                         Toast.makeText(mContext, "value saved", Toast.LENGTH_SHORT).show();

                        ((Activity)mContext).recreate();
                    }

                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // what ever you want to do with No option.
                }
            });

            alert.show();
        }
    }



}

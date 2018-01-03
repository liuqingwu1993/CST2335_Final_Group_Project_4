package com.example.sophia.cst2335_final_group_project;

/**
 * Created by Peixin Ren on 1/24/2017.
 */

public class AutoModel {
    public AutoModel() {
    }

    private String auto_litres, auto_price,auto_kilometer,timeStamp;
     int id;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAuto_litres() {

        return auto_litres;
    }

    public void setAuto_litres(String auto_litres) {
        this.auto_litres = auto_litres;
    }

    public String getAuto_price() {
        return auto_price;
    }

    public void setAuto_price(String auto_price) {
        this.auto_price = auto_price;
    }

    public String getAuto_kilometer() {
        return auto_kilometer;
    }

    public void setAuto_kilometer(String auto_kilometer) {
        this.auto_kilometer = auto_kilometer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    AutoModel(int id, String auto_litres, String auto_price, String auto_kilometer, String timeStamp) {
       this.id=id;
        this.auto_litres = auto_litres;
        this.auto_price = auto_price;
        this.auto_kilometer = auto_kilometer;
        this.timeStamp=timeStamp;

    }
}
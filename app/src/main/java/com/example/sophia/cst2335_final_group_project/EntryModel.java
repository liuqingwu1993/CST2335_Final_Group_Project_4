package com.example.sophia.cst2335_final_group_project;

/**
 * Created by Adeel Hamza on 1/24/2017.
 */

public class EntryModel {
    public EntryModel() {
    }

    private String days, time,temperature;
     int id;

    public EntryModel(String days, String time, String temperature, int id) {
        this.days = days;
        this.time = time;
        this.temperature = temperature;
        this.id = id;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
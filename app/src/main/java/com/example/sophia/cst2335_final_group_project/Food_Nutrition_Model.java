package com.example.sophia.cst2335_final_group_project;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Food_Nutrition_Model {

    public int id;
    public String food_name;
    public int calories;
    public int fat;
    public int carbohydrate;
    public int protein;
    public String eat_date;
    public String eat_time;


    public Food_Nutrition_Model(){

        food_name = null;
        calories = 0;
        fat = 0;
        carbohydrate = 0;
        protein = 0;
        eat_date = null;
        eat_time = null;
    }

    public Food_Nutrition_Model(int new_id,
                                String new_food_name,
                                int new_calories,
                                int new_fat,
                                int new_carbohydrate,
                                int new_protein,
                                String new_eat_date,
                                String new_eat_time
                                ){
        id = new_id;
        food_name = new_food_name;
        calories = new_calories;
        fat = new_fat;
        carbohydrate = new_carbohydrate;
        protein = new_protein;
        eat_date = new_eat_date;
        eat_time = new_eat_time;
    }

    @Override
    public String toString() {
        return this.eat_date + " " + this.eat_time + " Calories: " + this.calories + "ID: " + this.id;
    }

}

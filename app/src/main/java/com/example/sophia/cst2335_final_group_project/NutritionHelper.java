package com.example.sophia.cst2335_final_group_project;


import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NutritionHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NAME="nutrition.db";
    static  final String TABLE_NAME = "NUTRITION";
    public static final String KEY_ID = "_id";
    public static final String KEY_FOOD_NAME = "FOOD_NAME";
    public static final String KEY_CALORIES = "CALORIES";
    public static final String KEY_FAT = "FAT";
    public static final String KEY_CARBOHYDRATE = "CARBOHYDRATE";
    public static final String KEY_PROTEIN = "PROTEIN";
    public static final String KEY_DATE = "DATE";
    public static final String KEY_TIME = "TIME";

    private SQLiteDatabase database;

    public static String[] MESSAGE_FIELDS = new String[] {
            KEY_ID,
            KEY_FOOD_NAME,
            KEY_CALORIES,
            KEY_FAT,
            KEY_CARBOHYDRATE,
            KEY_PROTEIN,
            KEY_DATE,
            KEY_TIME
    };

    private static final String CREATE_DATABASE= "CREATE TABLE "
            + TABLE_NAME
            + "( "
            + KEY_ID
            + " integer primary key autoincrement, "
            + KEY_FOOD_NAME
            + " VARCHAR(250), "
            + KEY_CALORIES
            + " integer, "
            + KEY_FAT
            + " integer, "
            + KEY_CARBOHYDRATE
            + " integer, "
            + KEY_PROTEIN
            + " integer, "
            + KEY_DATE
            + " VARCHAR(50), "
            + KEY_TIME
            + " VARCHAR(50));";

    public NutritionHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNutrition(Food_Nutrition_Model myModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FOOD_NAME, myModel.food_name);
        values.put(KEY_CALORIES, myModel.calories);
        values.put(KEY_CARBOHYDRATE, myModel.carbohydrate);
        values.put(KEY_FAT, myModel.fat);
        values.put(KEY_PROTEIN, myModel.protein);
        values.put(KEY_DATE, myModel.eat_date);
        values.put(KEY_TIME, myModel.eat_time);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Food_Nutrition_Model getFoodNutritionModel(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, MESSAGE_FIELDS, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Food_Nutrition_Model fn = new Food_Nutrition_Model(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)),
                Integer.parseInt(cursor.getString(5)),
                cursor.getString(6),
                cursor.getString(7));
        // return contact
        return fn;
    }

    public List<Food_Nutrition_Model> getFoodNutritionModelByDate(String date) {
        List<Food_Nutrition_Model> contactList = new ArrayList<Food_Nutrition_Model>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE DATE = \"" + date + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Food_Nutrition_Model fn = new Food_Nutrition_Model();
                fn.id = Integer.parseInt(cursor.getString(0));
                fn.food_name =  cursor.getString(1);
                fn.calories = Integer.parseInt(cursor.getString(2));
                fn.fat = Integer.parseInt(cursor.getString(3));
                fn.carbohydrate = Integer.parseInt(cursor.getString(4));
                fn.protein = Integer.parseInt(cursor.getString(5));
                fn.eat_date = cursor.getString(6);
                fn.eat_time = cursor.getString(7);
                // Adding contact to list
                contactList.add(fn);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public Cursor  getAlFoodNutritions() {
        List<Food_Nutrition_Model> contactList = new ArrayList<Food_Nutrition_Model>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY + " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Food_Nutrition_Model fn = new Food_Nutrition_Model();
                fn.id = Integer.parseInt(cursor.getString(0));
                fn.food_name =  cursor.getString(1);
                fn.calories = Integer.parseInt(cursor.getString(2));
                fn.fat = Integer.parseInt(cursor.getString(3));
                fn.carbohydrate = Integer.parseInt(cursor.getString(4));
                fn.protein = Integer.parseInt(cursor.getString(5));
                fn.eat_date = cursor.getString(6);
                fn.eat_time = cursor.getString(7);
                // Adding contact to list
                contactList.add(fn);
            } while (cursor.moveToNext());
        }
        return cursor;
    }

    public List<Food_Nutrition_Model>  getAlFoodNutritionsObject() {
        List<Food_Nutrition_Model> contactList = new ArrayList<Food_Nutrition_Model>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY + " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Food_Nutrition_Model fn = new Food_Nutrition_Model();
                fn.id = Integer.parseInt(cursor.getString(0));
                fn.food_name =  cursor.getString(1);
                fn.calories = Integer.parseInt(cursor.getString(2));
                fn.fat = Integer.parseInt(cursor.getString(3));
                fn.carbohydrate = Integer.parseInt(cursor.getString(4));
                fn.protein = Integer.parseInt(cursor.getString(5));
                fn.eat_date = cursor.getString(6);
                fn.eat_time = cursor.getString(7);
                // Adding contact to list
                contactList.add(fn);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public void update(Food_Nutrition_Model myModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FOOD_NAME, myModel.food_name);
        values.put(KEY_CALORIES, myModel.calories);
        values.put(KEY_CARBOHYDRATE, myModel.carbohydrate);
        values.put(KEY_FAT, myModel.fat);
        values.put(KEY_PROTEIN, myModel.protein);
        values.put(KEY_DATE, myModel.eat_date);
        values.put(KEY_TIME, myModel.eat_time);

        db.update(TABLE_NAME, values, KEY_ID + "= ?", new String[] { String.valueOf(myModel.id) });
        db.close();
    }

    public void deleteFoodNutrition(Food_Nutrition_Model myModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(myModel.id) });
        db.close();
    }

}

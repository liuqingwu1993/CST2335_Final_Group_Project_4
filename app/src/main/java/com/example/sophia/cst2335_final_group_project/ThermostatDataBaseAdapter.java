package com.example.sophia.cst2335_final_group_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ThermostatDataBaseAdapter
{
		private static final String DATABASE_NAME = "thermostat.db";
		private static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
	static final String DATABASE_Thermo="create table "+"Entry_List"+
			"( "+"ID"+" integer primary key autoincrement,"+ "Days  text,"+ "Time text,"+"Temperature text); ";

	// Variable to hold the database instance
		public SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelperT dbHelper;


	public ThermostatDataBaseAdapter(Context _context)
		{
			context = _context;
			dbHelper = new DataBaseHelperT(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public ThermostatDataBaseAdapter open() throws SQLException
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db.close();
		}

		public SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

	public void InsertEntry(String days, String time, String temperature)
	{
		ContentValues newValues = new ContentValues();
		newValues.put("Days ",days);
		newValues.put("Time",time);
		newValues.put("Temperature",temperature);

		db.insert("Entry_List",null,newValues);
	}

	public int countRow()
	{
		int i=0;
		String table="Entry_List";
		Cursor cursor = db.rawQuery("select * from "+table,null);
		if (cursor .moveToFirst()) {
			String ColomName="ID";
			while (!cursor.isAfterLast()) {
				String value = cursor.getString(cursor.getColumnIndex(ColomName));
						i++;
				cursor.moveToNext();

				}

			}
		return i;
	}



		public int DeleteEntry(int id)
		{
			//String id=String.valueOf(ID);
			;
			open();
		    String where=" ID = ";
			// Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return db.delete("Entry_List", where+id, null);
		}

		public void  updateEntry(int id, String litter, String price, String kilometer)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("Time", price);
			updatedValues.put("Temperature",kilometer);
			updatedValues.put("Days",litter);
			String where="ID  = ";
		    db.update("Entry_List",updatedValues, where+id ,null);
			Toast.makeText(context, "value updated", Toast.LENGTH_SHORT).show();
		}

	public ArrayList<HashMap<String,String>> GetAllEntries() {
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from Entry_List   ", new String[]{});
		ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();
		// looping through all rows and adding to list
		//Toast.makeText(context, "count is  "+cursor.getCount(), Toast.LENGTH_SHORT).show();
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < cursor.getColumnCount(); i++) {
					map.put(cursor.getColumnName(i), cursor.getString(i));
					//Toast.makeText(context, "name is "+cursor.getString(i), Toast.LENGTH_SHORT).show();
				}

				maplist.add(map);
			} while (cursor.moveToNext());
		}
		//db.close();
		// return contact list
		return maplist;
	}

}


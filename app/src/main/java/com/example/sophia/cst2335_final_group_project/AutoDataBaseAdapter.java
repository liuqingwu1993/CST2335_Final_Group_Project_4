package com.example.sophia.cst2335_final_group_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AutoDataBaseAdapter
{
		private static final String DATABASE_NAME = "login.db";
		private static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
	static final String DATABASE_ROUTINE="create table "+"AUTO_LIST"+
			"( "+"ID"+" integer primary key autoincrement,"+ "Litres  text,"+ "PRICE text,"+"KILO_METERS text,"+
			"CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP); ";

	// Variable to hold the database instance
		public SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelper dbHelper;


	public AutoDataBaseAdapter(Context _context)
		{
			context = _context;
			dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public AutoDataBaseAdapter open() throws SQLException
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

	public void InsertEntry(String liters, String price, String kilometer, String dateAndtime)
	{
		ContentValues newValues = new ContentValues();
		newValues.put("Litres ",liters);
		newValues.put("PRICE",price);
		newValues.put("KILO_METERS",kilometer);
		newValues.put("CREATED_AT",dateAndtime);

		db.insert("AUTO_LIST",null,newValues);
	}

	public int countRow()
	{
		int i=0;
		String table="AUTO_LIST";
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

//	public ArrayList<String> GetKiloMeterFromAuto(String Name){
//		db = dbHelper.getReadableDatabase();
//		ArrayList<String> Time=new ArrayList<>();
//		String column="KILO_METERS";
//		Cursor cursor1=db.rawQuery("SELECT KILO_METERS FROM AUTO_LIST WHERE Litres =?",new String[]{Name} );
//		if (cursor1 !=null && cursor1.moveToFirst()){
//			//userName=cursor.getString(cursor.getColumnIndex(column));
//			while (!cursor1.isAfterLast()) {
//				Time.add(cursor1.getString(cursor1
//						.getColumnIndex(column)));
////				userName = userName+cursor.getString(cursor
////						.getColumnIndex(column))+"\n";
//
//				cursor1.moveToNext();
//			}
//		}
//	return Time;}
//
//	public ArrayList<String> GetPriceFromAuto(String Name){
//		db = dbHelper.getReadableDatabase();
//		ArrayList<String> Time=new ArrayList<>();
//		String userName = "";
//		String column="PRICE";
//		Cursor cursor=db.rawQuery("SELECT PRICE FROM AUTO_LIST WHERE Litres =?",new String[]{Name} );
//		if (cursor !=null && cursor.moveToFirst()){
//			//userName=cursor.getString(cursor.getColumnIndex(column));
//			while (!cursor.isAfterLast()) {
//				Time.add(cursor.getString(cursor
//						.getColumnIndex(column)));
////				userName = userName+cursor.getString(cursor
////						.getColumnIndex(column))+"\n";
//
//				cursor.moveToNext();
//			}
//		}
//		return Time;}
//
//
//	public ArrayList<Integer> GetIdFromAuto(String Name){
//		db = dbHelper.getReadableDatabase();
//		ArrayList<Integer> Time=new ArrayList<>();
//		String userName = "";
//		String column="ID";
//		Cursor cursor=db.rawQuery("SELECT ID FROM AUTO_LIST WHERE Litres =?",new String[]{Name} );
//		if (cursor !=null && cursor.moveToFirst()){
//			//userName=cursor.getString(cursor.getColumnIndex(column));
//			while (!cursor.isAfterLast()) {
//				Time.add(cursor.getInt(cursor
//						.getColumnIndex(column)));
////				userName = userName+cursor.getString(cursor
////						.getColumnIndex(column))+"\n";
//
//				cursor.moveToNext();
//			}
//		}
//		return Time;}

	public String GetPriceFromAuto(int id) {
		String table = "AUTO_LIST";
		List<String> seekList = null;
		String column="PRICE";
		String value = null;
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select PRICE from AUTO_LIST WHERE ID="+id, null);
		if (null != cursor && cursor.moveToFirst()) {
			value = cursor.getString(cursor.getColumnIndex(column));
		}
		return value ;
	}

		public int DeleteEntry(int id)
		{
			//String id=String.valueOf(ID);
			;
			open();
		    String where=" ID = ";
			// Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return db.delete("AUTO_LIST", where+id, null);
		}

		public void  updateEntry(int id, String litter, String price, String kilometer)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("PRICE", price);
			updatedValues.put("KILO_METERS",kilometer);
			updatedValues.put("Litres",litter);
			String where="ID  = ";
		    db.update("AUTO_LIST",updatedValues, where+id ,null);
			Toast.makeText(context, "value updated", Toast.LENGTH_SHORT).show();
		}

	public ArrayList<HashMap<String,String>> GetAllEntries() {
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from AUTO_LIST   ", new String[]{});
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


package com.rockerz.storageexample;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlitedb extends SQLiteOpenHelper {
	public static final String BLOG_MESSAGE = "messages";
	public static final String MESSAGE = "message";
	public static final String DATE="date";
	 

	  private static final String DATABASE_NAME = "message.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE =
              "CREATE TABLE " + BLOG_MESSAGE + " (" +
              MESSAGE + " TEXT, " +
              DATE + " TEXT);";
	  
	  //private static final String DATABASE_CREATE ="create table messages(message text)";
	public Sqlitedb(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		System.out.println(DATABASE_CREATE);
		}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS messages");
		this.onCreate(db);
	}
	public void addMessage(String message){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
	    values.put(MESSAGE, message); 
	    values.put(DATE, getDateTime());
	    db.insert(BLOG_MESSAGE, null, values);
	   // System.out.println("inserted");
	    db.close(); // Closing database connection
	}
	public List<String> getMessages(){
		List<String> test=new ArrayList<String>();
		
	    String selectQuery = "SELECT  * FROM " + BLOG_MESSAGE;
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	        	test.add(cursor.getString(1));
	            System.out.println("DATE"+cursor.getString(1));
	        } while (cursor.moveToNext());
	    }
	     return test;
	}
	public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
}

}

package com.example.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabase extends SQLiteOpenHelper{
	
	

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
 
    // Contacts table name
    public static final String TABLE_CONTACTS = "contacts";
 
    // Contacts Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PH_NO = "phone_number";

   // public static SQLiteDatabase db;
    
    
    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d("MyDatabase", "onCreate called");
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        
	}
	
	public boolean isDBCreated(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c= db.rawQuery("select * from "+TABLE_CONTACTS, null);
		if(c!=null){
			return true;
		}
		return false;
	}
	
	public SQLiteDatabase getDb(){
		return getWritableDatabase();
	}
	
	void addContact(String name, String phNum) {
		SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Contact Name
        values.put(KEY_PH_NO, phNum); // Contact Phone
 
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }
	
	void UpdateContact(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_PH_NO, "000000000"); // Contact Phone
 
        db.update(TABLE_CONTACTS, values, "name='"+name+"'", null);
        db.close(); // Closing database connection
    }
	
	void deleteContact(String name) {
		SQLiteDatabase db = this.getWritableDatabase();
 
        db.delete(TABLE_CONTACTS, "name='"+name+"'", null);
        db.close(); // Closing database connection
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//
	}

}

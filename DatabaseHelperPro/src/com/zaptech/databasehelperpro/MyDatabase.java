package com.zaptech.databasehelperpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

	String tem;
	SQLiteDatabase sDb;
	ContentValues values;
	public static final String DATABASE_NAME = "studdatabase.db";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "studTB";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_NAME = "studName";
	public static final String COLUMN_RNO = "studRno";

	public MyDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID
				+ " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_RNO
				+ " TEXT" + ")";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public SQLiteDatabase getDB() {
		sDb = this.getWritableDatabase();
		return sDb;
	}

	public ContentValues getContent() {
		values = new ContentValues();
		return values;
	}

	public void insertData(String name, int rno) {
		sDb = getDB();// Getting Writable Database.
		values = getContent();// Getting Content Values.
		values.put(COLUMN_NAME, name);
		values.put(COLUMN_RNO, rno);
		sDb.insert(TABLE_NAME, null, values);
		sDb.close();
	}

	public void updateData(String rno) {
		sDb = getDB();
		values = getContent();
		values.put(COLUMN_NAME, "Jaimin");
		sDb.update(TABLE_NAME, values, "COLUMN_RNO=" + rno, null);
		sDb.close();
	}

	public void delete(String rno) {
		sDb = getDB();
		sDb.delete(TABLE_NAME, "COLUMN_RNO=" + rno, null);
		sDb.close();
	}

}

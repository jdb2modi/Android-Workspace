package com.zaptech.databasepractice;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "DBNewone.db";
	public static final String TB_NAME = "TableRegistration";
	public static final String COL_ID = "ID";
	public static final String COL_USERNAME = "USERNAME";
	public static final String COL_PASSWORD = "PASSWORD";
	public static final int VERSION = 1;
	SQLiteDatabase sqlite;
	ContentValues values;
	mModel mm = new mModel();
	Cursor mCursor;
	ArrayList<mModel> al = new ArrayList<mModel>();

	public DBHelper(Context context) {
		super(context, DB_NAME, null, VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TB_NAME + "(" + COL_USERNAME
				+ " VARCHAR PRIMARY KEY," + COL_PASSWORD + " VARCHAR)";

		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public SQLiteDatabase getDB() {
		sqlite = getWritableDatabase();
		return sqlite;
	}

	public ContentValues getContentValues() {
		values = new ContentValues();
		return values;
	}

	public void register(String username, String password) {
		sqlite = getDB();
		values = getContentValues();
		values.put(COL_USERNAME, username);
		values.put(COL_PASSWORD, password);
		sqlite.insert(TB_NAME, null, values);
	}

	public void update(String username, String password) {
		sqlite = getDB();
		values = getContentValues();
		values.put(COL_PASSWORD, password);
		sqlite.update(TB_NAME, values, COL_USERNAME + "=?",
				new String[] { username });
	}

	public ArrayList<mModel> display() {
		String strDisplay = "SELECT * FROM " + TB_NAME;
		mCursor = getDB().rawQuery(strDisplay, null);
		if (mCursor.moveToFirst()) {
			do {
				mModel mm = new mModel();
				String strUsername = mCursor.getString(mCursor
						.getColumnIndex(COL_USERNAME));
				String strPassword = mCursor.getString(mCursor
						.getColumnIndex(COL_PASSWORD));
				mm.setStrUsername(strUsername);
				mm.setStrPassword(strPassword);
				al.add(mm);
			} while (mCursor.moveToNext());
		}
		return al;
	}

	public ArrayList<mModel> search(String username) {

		String strSearch = "SELECT * FROM " + TB_NAME + " WHERE "
				+ COL_USERNAME + "=?";
		mCursor = getDB().rawQuery(strSearch, new String[] { username });
		if (mCursor.moveToFirst()) {
			do {
				mModel mm = new mModel();
				String strUsername = mCursor.getString(mCursor
						.getColumnIndex(COL_USERNAME));
				String strPassword = mCursor.getString(mCursor
						.getColumnIndex(COL_PASSWORD));
				mm.setStrUsername(strUsername);
				mm.setStrPassword(strPassword);
				al.add(mm);
			} while (mCursor.moveToNext());
		}
		return al;
	}

	public void delete(String username, String password) {
		sqlite = getDB();
		sqlite.delete(TB_NAME, COL_USERNAME + "=?", new String[] { username });

	}

	public void deleteAll() {
		sqlite = getDB();
		sqlite.delete(TB_NAME, null, null);
	}
}

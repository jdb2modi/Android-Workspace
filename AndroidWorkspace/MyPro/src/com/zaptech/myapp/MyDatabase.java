package com.zaptech.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabase extends SQLiteOpenHelper {
	MainActivity ma = new MainActivity();
	SQLiteDatabase db;
	// Database name
	public static final String DATABASE_NAME = "mydb2.db";
	// Database version
	public static final int VERSION_NO = 1;
	// Database Table
	public static final String TABLE_STUDDATA = "tbStudData";
	// Columns
	public static final String COLUMN_NAME = "Name";
	public static final String COLUMN_LASTNAME = "Lastname";
	public static final String COLUMN_LAGKWN = "Lagknown";
	public static final String COLUMN_EMAIL = "Email";
	public static final String COLUMN_GENDER = "Gender";
	public static final String COLUMN_CITY = "City";
	public static final String COLUMN_PASSWORD = "Password";
	public static final String COLUMN_CONTACT = "Contact";

	public MyDatabase(Context context) {
		super(context, DATABASE_NAME, null, VERSION_NO);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_TABLE = "CREATE TABLE " + TABLE_STUDDATA
				+ "(id integer primary key," + COLUMN_NAME + " varchar,"
				+ COLUMN_LASTNAME + " varchar," + COLUMN_EMAIL + " varchar,"
				+ COLUMN_CITY + " varchar," + COLUMN_PASSWORD + " varchar,"
				+ COLUMN_CONTACT + " varchar);";

		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " +TABLE_STUDDATA);
		onCreate(db);
	}

	public SQLiteDatabase getDB() {
		db = getWritableDatabase();
		return db;
	}

	public void insertData(String name, String lastname, String email,
			String city, String password, String contact) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, name);
		values.put(COLUMN_LASTNAME, lastname);
		values.put(COLUMN_EMAIL, email);
		values.put(COLUMN_CITY, city);
		values.put(COLUMN_PASSWORD, password);
		values.put(COLUMN_CONTACT, contact);
		db = getWritableDatabase();
		db.insert(TABLE_STUDDATA, null, values);
		db.close();

	}

	public void deleteData(String email) {
		db = getWritableDatabase();
		db.delete(TABLE_STUDDATA, "Email=?", new String[] { email });
	}

	public void updateData(String name, String lastname, String email,
			String city, String password, String contact) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, name);
		values.put(COLUMN_LASTNAME, lastname);
		values.put(COLUMN_CITY, city);
		values.put(COLUMN_PASSWORD, password);
		values.put(COLUMN_CONTACT, contact);
		values.put(COLUMN_EMAIL, email);
		db = getWritableDatabase();
		db.update(TABLE_STUDDATA, values, "Email=?", new String[] { email });

	}

}
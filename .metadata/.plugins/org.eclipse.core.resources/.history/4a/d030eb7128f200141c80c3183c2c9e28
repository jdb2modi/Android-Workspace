package com.zaptech.genjson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract.DeletedContacts;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DBNAME = "jsondb.db";
	public static final String TB_JOSN = "jsontb";
	public static final String COL_JSON = "JsonText";
	public static final int DBVERSION = 1;
	SQLiteDatabase sqlite;
	ContentValues values;

	public DBHelper(Context context) {
		super(context, DBNAME, null, DBVERSION);
		context.deleteDatabase(DBNAME);
		getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TB_JSON = "CREATE TABLE " + TB_JOSN + "(" + COL_JSON
				+ " VARCHAR);";
		db.execSQL(CREATE_TB_JSON);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public SQLiteDatabase getDB() {
		sqlite = getWritableDatabase();
		return sqlite;
	}

	public void insertJson(String strJson) {
		sqlite = getDB();
		values = new ContentValues();
		values.put(COL_JSON, strJson);
		sqlite.insert(TB_JOSN, null, values);
	}

	public String getJson() {
		sqlite = getDB();
		String strJson = "";
		String sql = "SELECT * from " + TB_JOSN;
		Cursor cursor = sqlite.rawQuery(sql, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					strJson += cursor
							.getString(cursor.getColumnIndex(COL_JSON));
				} while (cursor.moveToNext());
			}
		}
		return strJson;
	}
}

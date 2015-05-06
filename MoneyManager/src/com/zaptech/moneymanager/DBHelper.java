package com.zaptech.moneymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB = "jmoney.db";

	public static final String TB = "tbMoney";
	public static final String TBINCOMEHISTORY = "tbIncomeHistory";
	public static final String TBEXPENCEHISTORY = "tbExpenceHistory";

	public static final String COL_TITLE = "Title";
	public static final String COL_INCOME = "Income";
	public static final String COL_EXPENCE = "Expence";
	public static final String COL_BALANCE = "Balance";
	public static final String COL_DESCRIPTION = "Description";
	SQLiteDatabase db;
	ContentValues values;

	public DBHelper(Context context) {
		super(context, DB, null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE_TB = "CREATE TABLE " + TB + "(" + COL_INCOME
				+ " INTEGER," + COL_EXPENCE + " INTEGER," + COL_BALANCE
				+ " INTEGER);";
		String CREATE_TABLE_INCOME_HISTORY = "CREATE TABLE " + TBINCOMEHISTORY
				+ "(" + COL_TITLE + " VARCHAR," + COL_INCOME + " INTEGER,"
				+ COL_DESCRIPTION + " VARCHAR);";
		String CREATE_TABLE_EXPENCE_HISTORY = "CREATE TABLE "
				+ TBEXPENCEHISTORY + "(" + COL_TITLE + " VARCHAR,"
				+ COL_EXPENCE + " INTEGER," + COL_DESCRIPTION + " VARCHAR);";
		db.execSQL(CREATE_TABLE_TB);
		db.execSQL(CREATE_TABLE_INCOME_HISTORY);
		db.execSQL(CREATE_TABLE_EXPENCE_HISTORY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public SQLiteDatabase getDB() {
		db = getWritableDatabase();
		return db;
	}

	public void insertIncomeHistory(String title, int income, String description) {
		db = getDB();
		values = new ContentValues();
		values.put(COL_TITLE, title);
		values.put(COL_INCOME, income);
		values.put(COL_DESCRIPTION, description);
		db.insert(TBINCOMEHISTORY, null, values);

	}

	public void insertExpenceHistory(String title, int expence,
			String description) {
		db = getDB();
		values = new ContentValues();
		values.put(COL_TITLE, title);
		values.put(COL_EXPENCE, expence);
		values.put(COL_DESCRIPTION, description);
		db.insert(TBEXPENCEHISTORY, null, values);

	}

	public void clearIncomeHistory() {
		db = getDB();
		db.delete(TBINCOMEHISTORY, null, null);
	}

	public void clearExpenceHistory() {
		db = getDB();
		db.delete(TBEXPENCEHISTORY, null, null);
	}

	public void clearAllHistory() {
		db = getDB();
		db.delete(TB, null, null);
		db.delete(TBEXPENCEHISTORY, null, null);
	}
}

package com.ifactory.myexpenditure;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DBNAME = "myexpenditure.db";
	public static final String TBEXPENCE = "tbExpense";
	public static final String TBPASSWORD = "tbPassword";

	public static final String COL_EXPENSEID = "ExpenseId";
	public static final String COL_EXPENSECATEGORY = "ExpenseCategory";
	public static final String COL_EXPENSEDATE = "ExpenceDate";
	public static final String COL_EXPENSEMODE = "ExpenceMode";
	public static final String COL_CHEQUENO = "ChequeNo";
	public static final String COL_TRANSACTIONID = "TransactionId";
	public static final String COL_EXPENSEAMOUNT = "ExpenceAmount";
	public static final String COL_DESCRIPTION = "Description";

	public static final String COL_PASSWORD = "Password";

	SQLiteDatabase mDatabase;
	ContentValues mContent;
	Cursor cursor;
	ArrayList<ExpenceModel> arrayListExpence = new ArrayList<ExpenceModel>();

	public DBHelper(Context context) {
		super(context, DBNAME, null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TBEXPENCE + " ("
				+ COL_EXPENSEID + " INTEGER PRIMARY KEY," + COL_EXPENSECATEGORY
				+ " VARCHAR," + COL_EXPENSEDATE + " DATE," + COL_EXPENSEMODE
				+ " VARCHAR," + COL_CHEQUENO + " VARCHAR," + COL_TRANSACTIONID
				+ " VARHCAR," + COL_EXPENSEAMOUNT + " INTEGER,"
				+ COL_DESCRIPTION + " VARCHAR);";
		String CREATE_PASSTABLE = "CREATE TABLE " + TBPASSWORD + "( "
				+ COL_PASSWORD + " VARCHAR PRIMARY KEY);";
		db.execSQL(CREATE_TABLE);
		db.execSQL(CREATE_PASSTABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public SQLiteDatabase getDB() {
		mDatabase = this.getWritableDatabase();
		return mDatabase;
	}

	public ContentValues getContentValues() {
		mContent = new ContentValues();
		return mContent;
	}

	public void insertExpence(String strCategory, String strDate,
			String strMode, String strChequeNo, String strTransactionId,
			int amount, String strDescription) {
		mDatabase = getDB();
		mContent = getContentValues();
		mContent.put(COL_EXPENSECATEGORY, strCategory);
		mContent.put(COL_EXPENSEDATE, strDate);
		mContent.put(COL_EXPENSEMODE, strMode);
		mContent.put(COL_CHEQUENO, strChequeNo);
		mContent.put(COL_TRANSACTIONID, strTransactionId);
		mContent.put(COL_EXPENSEAMOUNT, amount);
		mContent.put(COL_DESCRIPTION, strDescription);
		mDatabase.insert(TBEXPENCE, null, mContent);
		mDatabase.close();
	}

	public void updatePassword(String Password) {
		mDatabase = getDB();
		mContent = getContentValues();
		mContent.put(COL_PASSWORD, Password);
		mDatabase.update(TBPASSWORD, mContent, null, null);

		mDatabase.close();
	}

	public void insertPassword() {
		mDatabase = getDB();
		mContent = getContentValues();
		mContent.put(COL_PASSWORD, "12345");
		mDatabase.insert(TBPASSWORD, null, mContent);
		mDatabase.close();
	}

	public String checkPassword() {
		mDatabase = getDB();
		String pass = "";
		mContent = getContentValues();
		String sql = "select * from " + TBPASSWORD;
		Cursor cursor = mDatabase.rawQuery(sql, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					pass = cursor
							.getString(cursor.getColumnIndex(COL_PASSWORD));
				} while (cursor.moveToNext());
			}
		}

		mDatabase.close();
		return pass;
	}

	public ArrayList<ExpenceModel> displayHistory() {
		mDatabase = getDB();
		String strFetchExpence = "SELECT * FROM " + TBEXPENCE;

		cursor = mDatabase.rawQuery(strFetchExpence, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {

					String strExpenceId, strCategory, strDate, strMode, strChequeNo, strTransactionId, strAmount, strDescription;
					strExpenceId = String.valueOf(cursor.getInt(cursor
							.getColumnIndex(COL_EXPENSECATEGORY)));
					strDate = cursor.getString(cursor
							.getColumnIndex(COL_EXPENSEDATE));
					strMode = cursor.getString(cursor
							.getColumnIndex(COL_EXPENSEMODE));
					strChequeNo = cursor.getString(cursor
							.getColumnIndex(COL_CHEQUENO));
					strTransactionId = cursor.getString(cursor
							.getColumnIndex(COL_TRANSACTIONID));
					strAmount = cursor.getString(cursor
							.getColumnIndex(COL_EXPENSEAMOUNT));
					strDescription = cursor.getString(cursor
							.getColumnIndex(COL_DESCRIPTION));

					// /CREATING OBJECT for Model class
					ExpenceModel em = new ExpenceModel();
					em.setExpenseId(Integer.parseInt(strExpenceId));
					em.setExpenseDate(strDate);
					em.setExpenseMode(strMode);
					em.setChequeNo(strChequeNo);
					em.setTransactionId(strTransactionId);
					em.setExpenseAmount(Integer.parseInt(strAmount));
					em.setDescription(strDescription);
					arrayListExpence.add(em);
				} while (cursor.moveToNext());
			}
		}
		mDatabase.close();
		return arrayListExpence;
	}

	public ArrayList<ExpenceModel> displaySpecificHistory() {
		mDatabase = getDB();
		String strFetchExpence = "SELECT * FROM " + TBEXPENCE + " where "
				+ COL_EXPENSEDATE + " between '12/6/2015' AND '14/6/2015'";

		cursor = mDatabase.rawQuery(strFetchExpence, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {

					String strExpenceId, strCategory, strDate, strMode, strChequeNo, strTransactionId, strAmount, strDescription;
					strExpenceId = String.valueOf(cursor.getInt(cursor
							.getColumnIndex(COL_EXPENSECATEGORY)));
					strDate = cursor.getString(cursor
							.getColumnIndex(COL_EXPENSEDATE));
					strMode = cursor.getString(cursor
							.getColumnIndex(COL_EXPENSEMODE));
					strChequeNo = cursor.getString(cursor
							.getColumnIndex(COL_CHEQUENO));
					strTransactionId = cursor.getString(cursor
							.getColumnIndex(COL_TRANSACTIONID));
					strAmount = cursor.getString(cursor
							.getColumnIndex(COL_EXPENSEAMOUNT));
					strDescription = cursor.getString(cursor
							.getColumnIndex(COL_DESCRIPTION));

					// /CREATING OBJECT for Model class
					ExpenceModel em = new ExpenceModel();
					em.setExpenseId(Integer.parseInt(strExpenceId));
					em.setExpenseDate(strDate);
					em.setExpenseMode(strMode);
					em.setChequeNo(strChequeNo);
					em.setTransactionId(strTransactionId);
					em.setExpenseAmount(Integer.parseInt(strAmount));
					em.setDescription(strDescription);
					arrayListExpence.add(em);
				} while (cursor.moveToNext());
			}
		}
		mDatabase.close();
		return arrayListExpence;
	}

	public void deleteHistory() {
		mDatabase = getDB();
		mDatabase.delete(TBEXPENCE, null, null);
		mDatabase.close();
	}
}

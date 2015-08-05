package com.zaptech.myexpenditure2;

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

	public static final String DBNAME = "myexp.db";
	public static final String TBEXPENCE = "tbExpense";
	public static final String TBPASSWORD = "tbPassword";
	public static final String TBBANKDETAILS = "tbBankDetails";

	public static final String COL_EXPENSEID = "ExpenseId";
	public static final String COL_EXPENSECATEGORY = "ExpenseCategory";
	public static final String COL_EXPENSEDATE = "ExpenceDate";
	public static final String COL_EXPENSEMODE = "ExpenceMode";
	public static final String COL_CHEQUENO = "ChequeNo";
	public static final String COL_TRANSACTIONID = "TransactionId";
	public static final String COL_EXPENSEAMOUNT = "ExpenceAmount";
	public static final String COL_DESCRIPTION = "Description";

	public static final String COL_ACCOUNTNUMBER = "AccountNumber";
	public static final String COL_BANKNAME = "BankName";
	public static final String COL_CURRENTBALANCE = "CurrentBalance";

	public static final String COL_PASSWORD = "Password";

	SQLiteDatabase mDatabase;
	ContentValues mContent;
	Cursor cursor;
	ArrayList<ExpenceModel> arrayListExpence = new ArrayList<ExpenceModel>();
	ArrayList<ModelBankDetails> arrayListBankDetails = new ArrayList<ModelBankDetails>();
	ArrayList<String> arrayListBankNames = new ArrayList<String>();

	public DBHelper(Context context) {
		super(context, DBNAME, null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String TABLE_EXPENCE = "CREATE TABLE " + TBEXPENCE + " ("
				+ COL_EXPENSEID + " INTEGER PRIMARY KEY," + COL_EXPENSECATEGORY
				+ " VARCHAR," + COL_EXPENSEDATE + " TEXT," + COL_EXPENSEMODE
				+ " VARCHAR," + COL_CHEQUENO + " VARCHAR," + COL_TRANSACTIONID
				+ " VARHCAR," + COL_EXPENSEAMOUNT + " INTEGER,"
				+ COL_DESCRIPTION + " VARCHAR);";
		String CREATE_PASSTABLE = "CREATE TABLE " + TBPASSWORD + "( "
				+ COL_PASSWORD + " VARCHAR PRIMARY KEY);";
		String TABLE_BANKDETAILS = "CREATE TABLE " + TBBANKDETAILS + "( "
				+ COL_ACCOUNTNUMBER + " VARCHAR PRIMARY KEY," + COL_BANKNAME
				+ " VARCHAR," + COL_CURRENTBALANCE + " VARCHAR);";
		db.execSQL(TABLE_EXPENCE);
		db.execSQL(CREATE_PASSTABLE);
		db.execSQL(TABLE_BANKDETAILS);
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
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>indatabase>>>>>>>>>>>>>"
				+ strCategory);
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
		mDatabase.releaseMemory();
	}

	public void insertPassword() {
		mDatabase = getDB();
		mContent = getContentValues();
		mContent.put(COL_PASSWORD, "12345");
		mDatabase.insert(TBPASSWORD, null, mContent);
		mDatabase.close();
	}

	public int checkRow() {
		mDatabase = getDB();
		String pass = "";
		mContent = getContentValues();
		String sql = "select * from " + TBPASSWORD;
		Cursor cursor = mDatabase.rawQuery(sql, null);
		return cursor.getCount();
	}

	public String getPassword() {
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
					strCategory = cursor.getString(cursor
							.getColumnIndex(COL_EXPENSECATEGORY));
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
					em.setExpenseCategory(strCategory);
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

	public ArrayList<ExpenceModel> displaySpecificHistory(String strSDate,
			String strEDate) {
		mDatabase = getDB();
		String strFetchExpence = "SELECT * FROM " + TBEXPENCE + " where "
				+ COL_EXPENSEDATE + " >= " + strSDate + " AND "
				+ COL_EXPENSEDATE + " <= " + strEDate;
		System.err.println(">>>>>>>>>>>>>>>SFEEXXXX" + strFetchExpence);
		System.err.println(">>>>>>>>>>>>>>>EDATE" + strEDate);

		cursor = mDatabase.rawQuery(strFetchExpence, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {

					// SELECT * FROM tbExpense where ExpenceDate between
					// '8/7/2015' AND '11/7/2015'

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

	public void deleteExpenceHistory() {
		mDatabase = getDB();
		mDatabase.delete(TBEXPENCE, null, null);
		mDatabase.close();
	}

	public void addBankDetails(String strAccountNumber, String strBankName,
			String strCurrentBalance) {

		mDatabase = getDB();
		mContent = getContentValues();
		mContent.put(COL_ACCOUNTNUMBER, strAccountNumber);
		mContent.put(COL_BANKNAME, strBankName);
		mContent.put(COL_CURRENTBALANCE, strCurrentBalance);
		mDatabase.insert(TBBANKDETAILS, null, mContent);
		mDatabase.close();
		mDatabase.releaseMemory();

	}

	public ArrayList<ModelBankDetails> showBankDetails() {
		mDatabase = getDB();

		String strGetBankDetails = "SELECT * FROM " + TBBANKDETAILS;
		Cursor cursor = mDatabase.rawQuery(strGetBankDetails, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					String strAccountNumber, strBankName, strCurrentBalance;
					strAccountNumber = cursor.getString(cursor
							.getColumnIndex(COL_ACCOUNTNUMBER));
					strBankName = cursor.getString(cursor
							.getColumnIndex(COL_BANKNAME));
					strCurrentBalance = cursor.getString(cursor
							.getColumnIndex(COL_CURRENTBALANCE));
					ModelBankDetails modelBankDetails = new ModelBankDetails();
					modelBankDetails.setAccountNumber(strAccountNumber);
					modelBankDetails.setBankName(strBankName);
					modelBankDetails.setCurrentBalance(strCurrentBalance);
					arrayListBankDetails.add(modelBankDetails);
				} while (cursor.moveToNext());
			}

		}
		return arrayListBankDetails;
	}

	public void deletebankHistory() {
		mDatabase = getDB();
		mDatabase.delete(TBBANKDETAILS, null, null);
		mDatabase.close();
	}

	public void removeSpecificBank(String strBankName) {
		mDatabase = getDB();
		mDatabase.delete(TBBANKDETAILS, COL_BANKNAME + " = ?",
				new String[] { strBankName });
		mDatabase.close();
	}

	public void updateBankDetails(String accountNo, String bankName,
			String currentBalance) {
		mDatabase = getDB();
		mContent = getContentValues();
		mContent.put(COL_ACCOUNTNUMBER, accountNo);
		mContent.put(COL_BANKNAME, bankName);
		mContent.put(COL_CURRENTBALANCE, currentBalance);

		mDatabase.update(TBBANKDETAILS, mContent, COL_ACCOUNTNUMBER + " = "
				+ accountNo, null);

		mDatabase.close();
		mDatabase.releaseMemory();
	}

	public ArrayList<String> getBankDetailsToUpdate(String accountNumber) {

		ArrayList<String> arrayListBankDetails = new ArrayList<String>();
		String strGetDetails = "SELECT * FROM " + TBBANKDETAILS + " WHERE "
				+ COL_ACCOUNTNUMBER + " = " + accountNumber;
		mDatabase = getDB();
		Cursor cursor = mDatabase.rawQuery(strGetDetails, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					String strAccountNumber = cursor.getString(cursor
							.getColumnIndex(COL_ACCOUNTNUMBER));
					String strBankName = cursor.getString(cursor
							.getColumnIndex(COL_BANKNAME));
					String strCurrentBalance = cursor.getString(cursor
							.getColumnIndex(COL_CURRENTBALANCE));

					arrayListBankDetails.add(strAccountNumber);
					arrayListBankDetails.add(strBankName);
					arrayListBankDetails.add(strCurrentBalance);
				} while (cursor.moveToNext());
			}

		}

		return arrayListBankDetails;
	}

	public ArrayList<String> getBankNames() {

		String strGetDetails = "SELECT * FROM " + TBBANKDETAILS;
		mDatabase = getDB();
		Cursor cursor = mDatabase.rawQuery(strGetDetails, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					String strBankName = cursor.getString(cursor
							.getColumnIndex(COL_BANKNAME));
					System.err
							.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
									+ strBankName);
					arrayListBankNames.add(strBankName);

				} while (cursor.moveToNext());
			}

		}

		return arrayListBankNames;
	}
}

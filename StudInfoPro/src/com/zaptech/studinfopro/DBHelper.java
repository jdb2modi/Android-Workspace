package com.zaptech.studinfopro;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "stdclgData.db";
	public static final int DB_VERSION = 1;

	public static final String TB_STUDENT = "tbStudent";
	public static final String TB_COLLEGE = "tbCollege";

	public static final String COL_STUDID = "StudentId";
	public static final String COL_STUDNAME = "StudentName";
	public static final String COL_STUDRNO = "StudentRno";

	public static final String COL_CLGID = "CollegeId";
	public static final String COL_CLGNAME = "CollegeName";
	public static final String COL_ClGUNIVERSITY = "CollegeUniversity";

	ArrayList<CollegeModel> collegeList = new ArrayList<CollegeModel>();
	ArrayList<StudentModel> studentList = new ArrayList<StudentModel>();

	SQLiteDatabase sDB;
	ContentValues values;

	public DBHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.getWritableDatabase();
		// context.deleteDatabase("stdclg.db");

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TB_CLG = "CREATE TABLE " + TB_COLLEGE + "(" + COL_CLGID
				+ " INTEGER PRIMARY KEY," + COL_CLGNAME + " VARCHAR,"
				+ COL_ClGUNIVERSITY + " varchar)";

		String CREATE_TB_SUTD = "CREATE TABLE " + TB_STUDENT + "(" + COL_STUDID
				+ " INTEGER PRIMARY KEY," + COL_STUDNAME + " VARCHAR,"
				+ COL_STUDRNO + " INTEGER," + COL_CLGID + " REFERENCES "
				+ TB_COLLEGE + "(" + COL_CLGID + "))";

		db.execSQL(CREATE_TB_CLG);
		db.execSQL(CREATE_TB_SUTD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public SQLiteDatabase getDB() {
		sDB = getWritableDatabase();
		return sDB;
	}

	public void insertCollegeData(String clgName, String clgUniversity) {
		sDB = getDB();
		values = new ContentValues();
		values.put(COL_CLGNAME, clgName);
		values.put(COL_ClGUNIVERSITY, clgUniversity);
		sDB.insert(TB_COLLEGE, null, values);
	}

	public void updateCollegeData(int clgId, String clgName,
			String clgUniversity) {
		sDB = getWritableDatabase();
		values = new ContentValues();
		values.put(COL_CLGNAME, clgName);
		values.put(COL_ClGUNIVERSITY, clgUniversity);
		sDB.update(TB_COLLEGE, values, "CollegeId=" + clgId, null);
	}

	public void deleteCollegeData(String clgName) {
		sDB = getDB();
		sDB.delete(TB_COLLEGE, "CollegeName='" + clgName + "'", null);
	}

	public void deleteAllCollegeData() {
		sDB = getDB();
		sDB.delete(TB_COLLEGE, null, null);
	}

	// Function that returns List of College Data
	public ArrayList<CollegeModel> displayCollegeData() {
		collegeList.clear();
		String sqlGetCollegeData = "SELECT * FROM " + TB_COLLEGE;
		Cursor cursorCollege = getDB().rawQuery(sqlGetCollegeData, null);
		/* if (cursorCollege != null) { */
		if (cursorCollege.moveToFirst()) {
			do {
				CollegeModel clgModel = new CollegeModel();
				clgModel.setClgName(cursorCollege.getString(cursorCollege
						.getColumnIndex(COL_CLGNAME)));
				clgModel.setClgUniversity(cursorCollege.getString(cursorCollege
						.getColumnIndex(COL_ClGUNIVERSITY)));
				clgModel.setClgId(cursorCollege.getInt(cursorCollege
						.getColumnIndex(COL_CLGID)));
				collegeList.add(clgModel);

			} while (cursorCollege.moveToNext());
		}
		return collegeList;
	}

	// Functions to Search Colleges
	public ArrayList<CollegeModel> searchCollegeData(String clgName) {
		collegeList.clear();
		String sqlGetCollegeData = "SELECT * FROM " + TB_COLLEGE
				+ " WHERE CollegeName=?";
		Cursor cursorCollege = this.getDB().rawQuery(sqlGetCollegeData,
				new String[] { clgName });
		/* if (cursorCollege != null) { */
		if (cursorCollege.moveToFirst()) {
			do {
				CollegeModel clgModel = new CollegeModel();
				clgModel.setClgName(cursorCollege.getString(cursorCollege
						.getColumnIndex(COL_CLGNAME)));
				clgModel.setClgUniversity(cursorCollege.getString(cursorCollege
						.getColumnIndex(COL_ClGUNIVERSITY)));
				clgModel.setClgId(Integer.parseInt(cursorCollege.getString(cursorCollege
						.getColumnIndex(COL_CLGID))));
				collegeList.add(clgModel);
			} while (cursorCollege.moveToNext());
		}
		return collegeList;
	}

	// //Student///////////
	public void insertStudentData(String stdName, int stdRno, int clgId) {
		sDB = getDB();
		values = new ContentValues();
		values.put(COL_STUDNAME, stdName);
		values.put(COL_STUDRNO, stdRno);
		values.put(COL_CLGID, clgId);
		sDB.insert(TB_STUDENT, null, values);
	}

	public void updateStudentData(int stdId, String stdName, int stdRno) {
		sDB = getWritableDatabase();
		values = new ContentValues();
		values.put(COL_STUDNAME, stdName);
		values.put(COL_STUDRNO, stdRno);
		sDB.update(TB_STUDENT, values, "StudentId=" + stdId, null);
	}

	public void deleteStudentData(String stdName) {
		sDB = getDB();
		sDB.delete(TB_STUDENT, "StudentName=?", new String[] { stdName });
	}

	public void deleteAllStudentData() {
		sDB = getDB();
		sDB.delete(TB_STUDENT, null, null);
	}

	// Functions to Search Students
	public ArrayList<StudentModel> searchStudentData(String stdName) {
		studentList.clear();
		// String sql = "SELECT Password FROM registration WHERE Email_Id=?";

		// Cursor c = this.getDb().rawQuery(sql, new String[] { userName });
		String sqlGetStudentData = "SELECT * FROM " + TB_STUDENT
				+ " WHERE StudentName=?";
		Cursor cursorStudent = this.getDB().rawQuery(sqlGetStudentData,
				new String[] { stdName });

		if (cursorStudent.moveToFirst()) {
			do {
				StudentModel stdModel = new StudentModel();
				stdModel.setStdName(cursorStudent.getString(cursorStudent
						.getColumnIndex(COL_STUDNAME)));
				stdModel.setRno(Integer.parseInt(cursorStudent
						.getString(cursorStudent.getColumnIndex(COL_STUDRNO))));
				studentList.add(stdModel);
			} while (cursorStudent.moveToNext());
		}
		return studentList;
	}

	// Function that returns List of Student Data
	public ArrayList<StudentModel> displayStudentData() {
		studentList.clear();
		String sqlGetStudentData = "SELECT * FROM " + TB_STUDENT;
		Cursor cursorStudent = getDB().rawQuery(sqlGetStudentData, null);

		if (cursorStudent.moveToFirst()) {
			do {
				StudentModel stdModel = new StudentModel();
				stdModel.setStdName(cursorStudent.getString(cursorStudent
						.getColumnIndex(COL_STUDNAME)));
				stdModel.setRno(Integer.parseInt(cursorStudent
						.getString(cursorStudent.getColumnIndex(COL_STUDRNO))));
				stdModel.setStudId(cursorStudent.getInt(cursorStudent
						.getColumnIndex(COL_STUDID)));
				studentList.add(stdModel);
			} while (cursorStudent.moveToNext());
		}
		return studentList;
	}

}

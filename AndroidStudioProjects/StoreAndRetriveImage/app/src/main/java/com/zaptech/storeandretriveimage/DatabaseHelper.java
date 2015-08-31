package com.zaptech.storeandretriveimage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

/**
 * Created by jaimin on 14/8/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String IMAGE_TABLE = "imagetable";
    public static final String COL_IMAGE = "Image";
    public static final String DB_IMAGE = "ImageDatabase";
    public static final int DB_VERSION = 1;
    SQLiteDatabase sqlite = null;


    public DatabaseHelper(Context context) {
        super(context, DB_IMAGE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String strCreateTable = "CREATE TABLE " + IMAGE_TABLE + "( " + COL_IMAGE + " BLOB);";
        db.execSQL(strCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getDB() {
        sqlite = this.getWritableDatabase();
        return sqlite;
    }

    public void insertImage(Byte img) {
        sqlite = getDB();
        ContentValues cv = new ContentValues();
        cv.put(COL_IMAGE, img);
        sqlite.insert(IMAGE_TABLE, null, cv);
    }

    public void retriveImage() {

    }
}

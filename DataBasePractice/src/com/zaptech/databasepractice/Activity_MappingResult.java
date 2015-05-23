package com.zaptech.databasepractice;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_MappingResult extends Activity {
	ImageView imageview1;
	TextView textview1, textview2, textview3, textview4, textview5;
	Intent intent;
	DBHelper myDB;
	Cursor mCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_info_layout);
		init();

	}

	public void init() {
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview1 = (TextView) findViewById(R.id.textInfoUsername);
		textview2 = (TextView) findViewById(R.id.textInfoPassword1);
		textview3 = (TextView) findViewById(R.id.textInfoFirstname);
		textview4 = (TextView) findViewById(R.id.textInfoLastname);
		textview5 = (TextView) findViewById(R.id.textInfoAge);

		myDB = new DBHelper(Activity_MappingResult.this);
		
		
		intent = getIntent();
		String strUsername = intent.getStringExtra("USERNAMEMAP");
		
		String strMap = "SELECT * FROM " + myDB.TB_INFO + " where "
				+ myDB.COL_USERNAME + " =?";
		mCursor = myDB.getDB().rawQuery(strMap, new String[] { strUsername });
		if (mCursor.moveToFirst()) {
			do {
				String getUsername = mCursor.getString(mCursor
						.getColumnIndex(myDB.COL_USERNAME));
				String getPassword = mCursor.getString(mCursor
						.getColumnIndex(myDB.COL_PASSWORD));
				String getFirstname = mCursor.getString(mCursor
						.getColumnIndex(myDB.COL_FIRSTNAME));
				String getLastname = mCursor.getString(mCursor
						.getColumnIndex(myDB.COL_LASTNAME));
				String getAge = mCursor.getString(mCursor
						.getColumnIndex(myDB.COL_AGE));

				textview1.setText(getUsername.toString());
			//	textview2.setText(getPassword.toString());
				textview3.setText(getFirstname.toString());
				textview4.setText(getLastname.toString());
				textview5.setText(String.valueOf(getAge.toString()));

			} while (mCursor.moveToNext());
		}

	}
}

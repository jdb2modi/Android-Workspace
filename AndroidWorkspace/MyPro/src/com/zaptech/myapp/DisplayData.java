package com.zaptech.myapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayData extends Activity {
	MyDatabase mdb;
	TextView tvDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displaydata);
		init();
		displayData();
	}

	public void init() {
		Intent i = getIntent();
		mdb = new MyDatabase(this);
		tvDisplay = (TextView) findViewById(R.id.tvDisplay);
	}

	public void displayData() {
		Cursor c = mdb.getDB().rawQuery(
				"select * from " + MyDatabase.TABLE_STUDDATA, null);
		if (c.moveToFirst()) {
			do {
				String strName = c.getString(c
						.getColumnIndex(MyDatabase.COLUMN_NAME));
				String strLastName = c.getString(c
						.getColumnIndex(MyDatabase.COLUMN_LASTNAME));
				String strEmail = c.getString(c
						.getColumnIndex(MyDatabase.COLUMN_EMAIL));
				String strPassword = c.getString(c
						.getColumnIndex(MyDatabase.COLUMN_PASSWORD));
				String strContact = c.getString(c
						.getColumnIndex(MyDatabase.COLUMN_CONTACT));

				tvDisplay.append("\n\nName : " + strName + "\n\nLast Name : "
						+ strLastName + "\n\nEmail : " + strEmail
						+ "\n\nPassword : " + strPassword + "\n\nConatct : "
						+ strContact);
			} while (c.moveToNext());
		}
	}
}

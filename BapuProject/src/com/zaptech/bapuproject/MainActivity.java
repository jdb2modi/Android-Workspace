package com.zaptech.bapuproject;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentResolver cr = getContentResolver();
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);
		if (cur.getCount() > 0) {
			while (cur.moveToNext()) {
				String id = cur.getString(cur
						.getColumnIndex(ContactsContract.Contacts._ID));
				String name = cur
						.getString(cur
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				//String number=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.));
				ContentResolver bd = getContentResolver();
				Cursor bdc = bd.query(
						android.provider.ContactsContract.Data.CONTENT_URI,
						new String[] { Event.DATA },
						android.provider.ContactsContract.Data.CONTACT_ID
								+ " = " + id + " AND " + Data.MIMETYPE + " = '"
								+ Event.CONTENT_ITEM_TYPE + "' AND "
								+ Event.TYPE + " = " + Event.TYPE_BIRTHDAY,
						null,
						android.provider.ContactsContract.Data.DISPLAY_NAME);
				
				//Log.i("Date is...",Event.TYPE_BIRTHDAY);
				if (bdc.getCount() > 0) {
					while (bdc.moveToNext()) {
						String birthday = bdc.getString(0);
						// now "id" is the user's unique ID, "name" is his full
						// name and "birthday" is the date and time of his birth
						
						Log.i(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>birthday", birthday);
						Log.i(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>birthday", name);
						Log.i(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>birthday", id);
					}
					
				}
			}
		}
		cur.close();

	}

}

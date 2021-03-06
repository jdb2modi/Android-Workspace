package com.zaptech.studinfopro;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateCollegeActivity extends Activity implements OnClickListener {
	EditText edClgName, edClgUniversity;
	Button btnUpdateNow, btnCancel;
	DBHelper dbHelper;
	Spinner spinClgId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_college);
		init();
		loadSpinData();
		// setEditText();
	}

	public void init() {
		edClgName = (EditText) findViewById(R.id.edCollegeNameToUpdate);
		edClgUniversity = (EditText) findViewById(R.id.edCollegeUniversityToUpdate);
		btnUpdateNow = (Button) findViewById(R.id.btnUpdateCollegeNow);
		btnCancel = (Button) findViewById(R.id.btnUpdateCancel);
		dbHelper = new DBHelper(this);
		spinClgId = (Spinner) findViewById(R.id.spinCollegeNameToUpdate);

		// Adding Listeners
		btnUpdateNow.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnUpdateCollegeNow:
			dbHelper.updateCollegeData(Integer.parseInt(spinClgId
					.getSelectedItem().toString()), edClgName.getText()
					.toString(), edClgUniversity.getText().toString());
			Toast.makeText(UpdateCollegeActivity.this,
					getResources().getString(R.string.toastUpdateSuccessful),
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.btnUpdateCancel:
			clear();
			break;
		default:
			break;
		}

	}

	public void loadSpinData() {
		String sqlLoadData = "SELECT * FROM tbCollege";
		ArrayList<String> loadClgIds = new ArrayList<String>();

		Cursor selectCursor = dbHelper.getDB().rawQuery(sqlLoadData, null);

		if (selectCursor != null) {
			if (selectCursor.moveToFirst()) {
				do {
					loadClgIds.add(selectCursor.getString(selectCursor
							.getColumnIndex("CollegeId")));

				} while (selectCursor.moveToNext());
			}
		}
		ArrayAdapter<String> adpt = new ArrayAdapter<String>(
				UpdateCollegeActivity.this,
				android.R.layout.simple_dropdown_item_1line, loadClgIds);
		spinClgId.setAdapter(adpt);
	}

	public void setEditText() {
		spinClgId.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String sqlLoadData = "SELECT * FROM tbCollege where CollegeId="
						+ spinClgId.getSelectedItem().toString();
				Cursor selectCursor = dbHelper.getDB().rawQuery(sqlLoadData,
						null);

				edClgName.setText(selectCursor.getString(selectCursor
						.getColumnIndex(DBHelper.COL_CLGNAME)));
				edClgUniversity.setText(selectCursor.getString(selectCursor
						.getColumnIndex(DBHelper.COL_ClGUNIVERSITY)));

				Log.d("CLGNAME===========================>", selectCursor
						.getString(selectCursor
								.getColumnIndex(DBHelper.COL_CLGNAME)));
				Log.d("CLGUNIVERSITY========================>", selectCursor
						.getString(selectCursor
								.getColumnIndex(DBHelper.COL_ClGUNIVERSITY)));

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	public void clear() {
		edClgName.setText("");
		edClgUniversity.setText("");
	}
}

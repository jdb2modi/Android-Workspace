package com.zaptech.studinfopro;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
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

public class AddStudentActivity extends Activity implements OnClickListener {
	Button btnRegisterStudentNow, btnCancel;
	EditText edStudentName, edStudentRno;
	Spinner spinCollegeName;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_student);
		init();
		loadDataForCollegeName();
	}// END OF onCreate()

	public void init() {
		dbHelper = new DBHelper(this);

		btnRegisterStudentNow = (Button) findViewById(R.id.btnRegisterStudentNow);
		btnCancel = (Button) findViewById(R.id.btnCancelStudent);
		edStudentName = (EditText) findViewById(R.id.edStudentName);
		edStudentRno = (EditText) findViewById(R.id.edStudentRno);
		spinCollegeName = (Spinner) findViewById(R.id.spinCollegeName);

		// Adding Listeners;
		btnRegisterStudentNow.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		spinCollegeName.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}// END OF init()

	public void clear() {
		edStudentName.setText("");
		edStudentRno.setText("");
	}// END OF clear()

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRegisterStudentNow:
			dbHelper.insertStudentData(edStudentName.getText().toString(),
					Integer.parseInt(edStudentRno.getText().toString()),
					Integer.parseInt(spinCollegeName.getSelectedItem()
							.toString()));
			Toast.makeText(AddStudentActivity.this,
					getResources().getString(R.string.strStudentInserted),
					Toast.LENGTH_SHORT).show();
			clear();
			break;
		case R.id.btnCancelStudent:
			clear();
			break;
		default:
			break;
		}// END OF switch
	}// END OF onClick()

	public void loadDataForCollegeName() {
		String sqlLoadData = "SELECT CollegeId FROM tbCollege";
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
				AddStudentActivity.this,
				android.R.layout.simple_dropdown_item_1line, loadClgIds);
		spinCollegeName.setAdapter(adpt);
	}// END OF loadDataForCollegeName()
}// END OF Class

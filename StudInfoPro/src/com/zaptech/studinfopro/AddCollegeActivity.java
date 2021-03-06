package com.zaptech.studinfopro;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCollegeActivity extends Activity implements OnClickListener {
	Button btnAddCollegeNow, btnCancel;
	EditText edCollegeName, edCollegeUniversity;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_college);
		init();

	}

	public void init() {
		dbHelper = new DBHelper(this);

		btnAddCollegeNow = (Button) findViewById(R.id.btnAddCollegeNow);
		btnCancel = (Button) findViewById(R.id.btnCancelCollege);
		edCollegeName = (EditText) findViewById(R.id.edCollegeName);
		edCollegeUniversity = (EditText) findViewById(R.id.edUniversityName);

		// Adding Listeners;
		btnAddCollegeNow.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
	}

	public void clear() {
		edCollegeName.setText("");
		edCollegeUniversity.setText("");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAddCollegeNow:
			String strClgName,
			strClgUniversity;
			strClgName = edCollegeName.getText().toString();
			strClgUniversity = edCollegeUniversity.getText().toString();
			if (strClgName.trim().length() < 1
					&& strClgUniversity.trim().length() < 1) {
				edCollegeName.setError("Please fill the College name");
				edCollegeUniversity.setError("Please fill the University name");
			} else if (strClgName.trim().length() < 1) {
				edCollegeName.setError("Please fill the College name");
			} else if (strClgUniversity.trim().length() < 1) {
				edCollegeUniversity.setError("Please fill the University name");
			} else {
				dbHelper.insertCollegeData(edCollegeName.getText().toString(),
						edCollegeUniversity.getText().toString());
				Toast.makeText(AddCollegeActivity.this,
						getResources().getString(R.string.strCollegeInserted),
						Toast.LENGTH_SHORT).show();
				clear();
			}

			break;
		case R.id.btnCancelCollege:
			clear();
			break;

		default:
			break;
		}
	}
}

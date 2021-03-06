package com.zaptech.studinfopro;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteCollegeActivity extends Activity implements OnClickListener {
	Spinner spinDeleteCollege;
	Button btnDeleteCollege;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_college);
		init();
		loadDataForCollegeName();
	}

	public void init() {
		spinDeleteCollege = (Spinner) findViewById(R.id.spinCollegeNameToDelete);
		btnDeleteCollege = (Button) findViewById(R.id.btnDeleteCollege);
		btnDeleteCollege.setOnClickListener(this);
		dbHelper = new DBHelper(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnDeleteCollege:
			dbHelper.deleteCollegeData(spinDeleteCollege.getSelectedItem()
					.toString());
			Toast.makeText(DeleteCollegeActivity.this,
					getResources().getString(R.string.toastDeleteSuccessful),
					Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

	}

	public void loadDataForCollegeName() {
		String sqlLoadData = "SELECT " + DBHelper.COL_CLGNAME + " FROM "
				+ DBHelper.TB_COLLEGE;
		ArrayList<String> loadClgNames = new ArrayList<String>();

		Cursor selectCursor = dbHelper.getDB().rawQuery(sqlLoadData, null);

		if (selectCursor != null) {
			if (selectCursor.moveToFirst()) {
				do {
					loadClgNames.add(selectCursor.getString(selectCursor
							.getColumnIndex(DBHelper.COL_CLGNAME)));
				} while (selectCursor.moveToNext());
			}
		}
		ArrayAdapter<String> adpt = new ArrayAdapter<String>(
				DeleteCollegeActivity.this,
				android.R.layout.simple_dropdown_item_1line, loadClgNames);
		spinDeleteCollege.setAdapter(adpt);
	}// END OF loadDataForCollegeName()

}

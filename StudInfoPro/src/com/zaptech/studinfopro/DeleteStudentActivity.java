package com.zaptech.studinfopro;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteStudentActivity extends Activity implements OnClickListener {
	Spinner spinDeleteStudent;
	Button btnDeleteStudent;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_student);
		init();
		loadDataForStudentName();
	}

	public void init() {
		spinDeleteStudent = (Spinner) findViewById(R.id.spinStudentNameToDelete);
		btnDeleteStudent = (Button) findViewById(R.id.btnDeleteStudent);
		btnDeleteStudent.setOnClickListener(this);
		dbHelper = new DBHelper(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnDeleteStudent:
			dbHelper.deleteStudentData(spinDeleteStudent.getSelectedItem()
					.toString());
			Toast.makeText(DeleteStudentActivity.this,
					getResources().getString(R.string.toastDeleteSuccessful),
					Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

	public void loadDataForStudentName() {
		String sql = "SELECT StudentName FROM " + DBHelper.TB_STUDENT;
		ArrayList<String> studList = new ArrayList<String>();
		Cursor c = dbHelper.getDB().rawQuery(sql, null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					studList.add(c.getString(c
							.getColumnIndex(DBHelper.COL_STUDNAME)));
				} while (c.moveToNext());
			}
		}
		ArrayAdapter<String> adpt = new ArrayAdapter<>(
				DeleteStudentActivity.this,
				android.R.layout.simple_dropdown_item_1line, studList);
		spinDeleteStudent.setAdapter(adpt);
	}// END OF loadDataForStudentName()

}

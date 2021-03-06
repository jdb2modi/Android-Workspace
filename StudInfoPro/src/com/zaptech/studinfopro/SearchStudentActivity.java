package com.zaptech.studinfopro;

import java.util.ArrayList;

import com.zaptech.studinfopro.DisplayCollegeActivity.CollegeAdapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchStudentActivity extends Activity implements OnClickListener {
	Spinner spinStudName;
	ListView listSearchStudents;
	Button btnSearchStudentNow;
	DBHelper dbHelper;
	ProgressDialog pd;
	LayoutInflater inflator;
	SearchStudentAsyn mAdapter;

	ArrayList<StudentModel> searchStudList = new ArrayList<StudentModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_student);
		init();
		loadStudentData();
	}

	public void init() {
		spinStudName = (Spinner) findViewById(R.id.spinnerStudentsearch);
		listSearchStudents = (ListView) findViewById(R.id.listSearchStudent);
		btnSearchStudentNow = (Button) findViewById(R.id.btnSearchStudentNow);
		dbHelper = new DBHelper(this);
		pd = new ProgressDialog(SearchStudentActivity.this);
		btnSearchStudentNow.setOnClickListener(this);
		listSearchStudents.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Toast.makeText(SearchStudentActivity.this, "Clicked",
						Toast.LENGTH_SHORT).show();

			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSearchStudentNow:
			new SearchStudentAsyn().execute();
			break;

		default:
			break;
		}
	}

	// TO LOAD Student Names into Spinner...
	public void loadStudentData() {
		String sql = "SELECT StudentName FROM " + dbHelper.TB_STUDENT;
		Cursor cursorLoadStudentData = dbHelper.getDB().rawQuery(sql, null);
		ArrayList<String> studentList = new ArrayList<String>();

		if (cursorLoadStudentData != null) {
			if (cursorLoadStudentData.moveToFirst()) {
				do {
					studentList.add(cursorLoadStudentData
							.getString(cursorLoadStudentData
									.getColumnIndex(DBHelper.COL_STUDNAME)));
				} while (cursorLoadStudentData.moveToNext());
			}
		}
		ArrayAdapter<String> adpt = new ArrayAdapter<>(
				SearchStudentActivity.this,
				android.R.layout.simple_dropdown_item_1line, studentList);
		spinStudName.setAdapter(adpt);

	}

	public class searchStudentAdapter extends BaseAdapter {
		Context mContext;

		public searchStudentAdapter(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {
			return dbHelper.studentList.size();

		}

		@Override
		public Object getItem(int position) {
			return dbHelper.studentList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (inflator == null) {
				inflator = (LayoutInflater) mContext
						.getSystemService(LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflator.inflate(R.layout.custom_stud_layout,
						null);
			}
			TextView StudName = (TextView) convertView
					.findViewById(R.id.tvStudentName);
			TextView StudRno = (TextView) convertView
					.findViewById(R.id.tvStudentRno);
			TextView StudId = (TextView) convertView
					.findViewById(R.id.tvStudentId);
			StudName.setText("Name : "
					+ dbHelper.studentList.get(position).getStdName());
			StudRno.setText("Roll Number : "
					+ String.valueOf(dbHelper.studentList.get(position)
							.getRno()));
			StudId.setText("Student Id : "
					+ String.valueOf(dbHelper.studentList.get(position)
							.getStudId()));

			return convertView;
		}

	}

	class SearchStudentAsyn extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			pd.setTitle("Loaging..");
			pd.setMessage("Please wait");
			pd.setCancelable(false);
			pd.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			dbHelper.searchStudentData(spinStudName.getSelectedItem()
					.toString());
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			if (pd.isShowing()) {
				pd.dismiss();
			}
			listSearchStudents.setAdapter(new searchStudentAdapter(
					SearchStudentActivity.this));

			Toast.makeText(
					SearchStudentActivity.this,
					getResources().getString(R.string.toastTotalRecordsFound)
							+ String.valueOf(listSearchStudents.getCount()),
					Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}

	}// END OF AsyClass

}

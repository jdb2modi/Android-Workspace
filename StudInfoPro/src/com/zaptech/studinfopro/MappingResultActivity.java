package com.zaptech.studinfopro;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MappingResultActivity extends Activity {
	ListView mapListView;
	DBHelper dbHelper;
	String strClgId, strClgName, strClgUniversity;
	ArrayList<StudentModel> clgStudList;
	LayoutInflater inflater;
	MyMappingAdapter mAdapter;
	ProgressDialog pd;

	TextView tvClgName, tvUniversity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapping_result);

		// Getting the CollegeId through Intent
		Intent i = getIntent();

		// Setting the CollegeId into a String Variable called temp
		strClgId = i.getStringExtra("keyCollegeId");
		strClgName = i.getStringExtra("keyCollegeName");
		strClgUniversity = i.getStringExtra("keyUniversity");
		Toast.makeText(MappingResultActivity.this, "CollegeId :  " + strClgId,
				Toast.LENGTH_SHORT).show();
		init();

		new AsynchronusMapping().execute();
	}

	// Initialize the Variables and Objects
	public void init() {
		mapListView = (ListView) findViewById(R.id.listMappingresult);
		clgStudList = new ArrayList<StudentModel>();
		dbHelper = new DBHelper(this);
		mAdapter = new MyMappingAdapter(this);
		pd = new ProgressDialog(this);

		tvClgName = (TextView) findViewById(R.id.tvMappingClgName);
		tvUniversity = (TextView) findViewById(R.id.tvMappingUniversity);
		tvClgName.setText(strClgName);
		tvUniversity.setText(strClgUniversity);
		

	}

	// To get the Student Data in to ArrayList named 'clgStudList'
	public void getStudentData() {
		String sqlGetStudentData = "SELECT * FROM " + dbHelper.TB_STUDENT
				+ " WHERE CollegeId=" + strClgId;
		Cursor cursorCollege = dbHelper.getDB().rawQuery(sqlGetStudentData,
				null);
		if (cursorCollege.moveToFirst()) {
			do {
				StudentModel stdModel = new StudentModel();
				stdModel.setRno(cursorCollege.getInt(cursorCollege
						.getColumnIndex(dbHelper.COL_STUDRNO)));
				stdModel.setStdName(cursorCollege.getString(cursorCollege
						.getColumnIndex(dbHelper.COL_STUDNAME)));
				stdModel.setStudId(cursorCollege.getInt(cursorCollege
						.getColumnIndex(dbHelper.COL_STUDID)));
				clgStudList.add(stdModel);
			} while (cursorCollege.moveToNext());

		}
	}

	// CustomAdapter Class to fetch data from clgStudList(ArrayList)
	class MyMappingAdapter extends BaseAdapter {

		Context mContext;

		public MyMappingAdapter(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {

			return clgStudList.size();
		}

		@Override
		public Object getItem(int position) {

			return clgStudList.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (inflater == null) {
				inflater = (LayoutInflater) mContext
						.getSystemService(LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.custom_stud_layout,
						null);
			}
			TextView stdRno = (TextView) convertView
					.findViewById(R.id.tvStudentRno);
			TextView stdName = (TextView) convertView
					.findViewById(R.id.tvStudentName);
			TextView stdId = (TextView) convertView
					.findViewById(R.id.tvStudentId);

			stdRno.setText("Roll Number : "
					+ String.valueOf(clgStudList.get(position).getRno()));
			stdName.setText("Name : " + clgStudList.get(position).getStdName());
			stdId.setText("Student Id : "
					+ String.valueOf(clgStudList.get(position).getStudId()));
			return convertView;
		}

	}

	class AsynchronusMapping extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			pd.setTitle("Loading...");
			pd.setMessage("Please Wait, Loading your Content..");
			pd.setCancelable(false);
			pd.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			getStudentData();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (pd.isShowing()) {
				pd.dismiss();
			}
			mapListView.setAdapter(mAdapter);
			getRecordCount();
			super.onPostExecute(result);
		}
	}

	// Function to count the fetched data for Student
	public void getRecordCount() {
		if (mapListView.getCount() <= 0) {
			Dialog d = new Dialog(this);
			d.setTitle("DATA NOT FOUND");
			d.setCancelable(true);
			d.show();
			Toast.makeText(MappingResultActivity.this, "No Records Found..!",
					Toast.LENGTH_LONG).show();

		} else {
			Toast.makeText(MappingResultActivity.this,
					"Total Records Found : " + mapListView.getCount(),
					Toast.LENGTH_LONG).show();
		}
	}
}

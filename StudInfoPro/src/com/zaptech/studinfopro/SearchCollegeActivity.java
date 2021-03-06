package com.zaptech.studinfopro;

import java.util.ArrayList;

import com.zaptech.studinfopro.SearchStudentActivity.searchStudentAdapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchCollegeActivity extends Activity implements OnClickListener {
	Spinner spinCollegeName;
	ListView listSearchColleges;
	Button btnSearchCollegeNow;
	DBHelper dbHelper;
	ProgressDialog pd;
	LayoutInflater inflator;
	SearchCollegeAsyn mAdapter;
	ArrayList<String> listCollegeNames = new ArrayList<String>();
	ArrayAdapter<String> adpt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_college);
		init();
		loadCollegeData();
	}

	public void init() {
		spinCollegeName = (Spinner) findViewById(R.id.spinnerCollegesearch);
		listSearchColleges = (ListView) findViewById(R.id.listSearchCollege);
		btnSearchCollegeNow = (Button) findViewById(R.id.btnSearchCollegeNow);
		dbHelper = new DBHelper(this);
		pd = new ProgressDialog(SearchCollegeActivity.this);
		btnSearchCollegeNow.setOnClickListener(this);
		listSearchColleges.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Toast.makeText(SearchCollegeActivity.this, "Clicked",
						Toast.LENGTH_SHORT).show();

			}
		});

	}

	// To Display data into Spinner
	public void loadCollegeData() {
		String strSql = "SELECT CollegeName FROM " + DBHelper.TB_COLLEGE;

		Cursor cursorLoadCollegeData = dbHelper.getDB().rawQuery(strSql, null);
		if (cursorLoadCollegeData != null) {
			if (cursorLoadCollegeData.moveToFirst()) {
				do {
					listCollegeNames.add(cursorLoadCollegeData
							.getString(cursorLoadCollegeData
									.getColumnIndex(DBHelper.COL_CLGNAME)));
				} while (cursorLoadCollegeData.moveToNext());
			}
		}
		adpt = new ArrayAdapter<>(SearchCollegeActivity.this,
				android.R.layout.simple_dropdown_item_1line, listCollegeNames);
		spinCollegeName.setAdapter(adpt);
		// listSearchColleges.setAdapter(adpt);
	}

	class searchCollegeAdapter extends BaseAdapter {
		Context mContext;

		public searchCollegeAdapter(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {

			return dbHelper.collegeList.size();
		}

		@Override
		public Object getItem(int position) {
			return dbHelper.collegeList.get(position);

		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View converView, ViewGroup parent) {
			if (inflator == null) {
				inflator = (LayoutInflater) mContext
						.getSystemService(LAYOUT_INFLATER_SERVICE);
			}
			if (converView == null) {
				converView = inflator.inflate(R.layout.custom_clg_layout, null);
			}
			TextView tvClgName = (TextView) converView
					.findViewById(R.id.tvCollegeName);
			TextView tvUniverSityName = (TextView) converView
					.findViewById(R.id.tvCollegeUniversity);
			TextView tvClgId = (TextView) converView
					.findViewById(R.id.tvCollegeId);
			tvClgName.setText("College Name : "+dbHelper.collegeList.get(position).getClgName());
			tvUniverSityName.setText("University Name : "+dbHelper.collegeList.get(position)
					.getClgUniversity());
			tvClgId.setText("College Id : "+dbHelper.collegeList.get(position)
					.getClgId());
			return converView;
		}

	}// END OF SearchCollege Adapter

	class SearchCollegeAsyn extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			pd.setTitle("Loading");
			pd.setMessage("Please Wait...");
			pd.setCancelable(false);
			pd.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			dbHelper.searchCollegeData(spinCollegeName.getSelectedItem()
					.toString());
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			if (pd.isShowing()) {
				pd.dismiss();
			}
			listSearchColleges.setAdapter(new searchCollegeAdapter(
					SearchCollegeActivity.this));
			// listSearchColleges.setAdapter(adpt);
			Toast.makeText(SearchCollegeActivity.this,
					"Total Records Found : " + listSearchColleges.getCount(),
					Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSearchCollegeNow:
			new SearchCollegeAsyn().execute();
			break;

		default:
			break;
		}

	}
}

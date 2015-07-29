package com.zaptech.studinfopro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayCollegeActivity extends Activity {
	ListView listviewCollege;
	DBHelper dbHelper;
	LayoutInflater infalter;
	ProgressDialog pd;
	CollegeAdapter clgAdapter;
	ScrollView scroll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_college);
		init();
		new AsyCollege().execute();

	}

	public void init() {
		scroll = (ScrollView) findViewById(R.id.scroll1);
		listviewCollege = (ListView) findViewById(R.id.listCollege);
		dbHelper = new DBHelper(this);
		clgAdapter = new CollegeAdapter(this);
		pd = new ProgressDialog(DisplayCollegeActivity.this);

		listviewCollege.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				/*
				 * AlertDialog.Builder alertCollegeData = new
				 * AlertDialog.Builder( DisplayCollegeActivity.this);
				 * alertCollegeData.setTitle("College Information");
				 * alertCollegeData.setMessage("College Name : " +
				 * dbHelper.collegeList.get(position).getClgName() +
				 * "\n\nCollege University : " +
				 * dbHelper.collegeList.get(position).getClgUniversity() +
				 * "\n\nCollege Id : " +
				 * dbHelper.collegeList.get(position).getClgId());
				 * alertCollegeData.show();
				 */
				scroll.setFocusable(false);
				Intent i = new Intent(DisplayCollegeActivity.this,
						MappingResultActivity.class);
				i.putExtra("keyCollegeId", String.valueOf(dbHelper.collegeList
						.get(position).getClgId()));
				i.putExtra("keyCollegeName", String
						.valueOf(dbHelper.collegeList.get(position)
								.getClgName()));
				i.putExtra("keyUniversity", String.valueOf(dbHelper.collegeList
						.get(position).getClgUniversity()));
				startActivity(i);

			}
		});

		listviewCollege.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				//v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}
		});
	}

	class CollegeAdapter extends BaseAdapter {
		Context mContext;

		public CollegeAdapter(Context context) {
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

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (infalter == null) {
				infalter = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = infalter
						.inflate(R.layout.custom_clg_layout, null);
			}
			TextView name = (TextView) convertView
					.findViewById(R.id.tvCollegeName);
			TextView university = (TextView) convertView
					.findViewById(R.id.tvCollegeUniversity);
			TextView clgId = (TextView) convertView
					.findViewById(R.id.tvCollegeId);

			Button btnSpecial = (Button) convertView.findViewById(R.id.button1);
			btnSpecial.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Clicked", 2500)
							.show();
				}
			});
			name.setText("College Name : "
					+ dbHelper.collegeList.get(position).getClgName());
			university.setText("University : "
					+ dbHelper.collegeList.get(position).getClgUniversity());
			clgId.setText(String.valueOf("College Id : "
					+ dbHelper.collegeList.get(position).getClgId()));

			return convertView;
		}

	}// END OF Class 'StudentAdapter'

	class AsyCollege extends AsyncTask<Void, Void, Void> {

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
			dbHelper.displayCollegeData();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			if (pd.isShowing()) {
				pd.dismiss();
			}
			listviewCollege.setAdapter(new CollegeAdapter(
					DisplayCollegeActivity.this));
			Toast.makeText(
					DisplayCollegeActivity.this,
					getResources().getString(R.string.toastTotalRecordsFound)
							+ String.valueOf(listviewCollege.getCount()),
					Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}

	}// END OF AsyClass

}

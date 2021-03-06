package com.zaptech.studinfopro;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayStudentActivity extends Activity implements
		OnItemClickListener {
	ListView listviewStudent;
	DBHelper dbHelper;
	LayoutInflater infalterStud;
	ProgressDialog pd;
	StudentAdapter stdAdapter;
	View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_student);
		init();
		// Intent iGet = getIntent();
		new AsyStudent().execute();

	}

	public void init() {
		listviewStudent = (ListView) findViewById(R.id.listStudent);
		listviewStudent.setOnItemClickListener(this);
		dbHelper = new DBHelper(this);
		stdAdapter = new StudentAdapter(this);
		pd = new ProgressDialog(DisplayStudentActivity.this);

		listviewStudent.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				AlertDialog.Builder alertStudData = new AlertDialog.Builder(
						DisplayStudentActivity.this);
				alertStudData.setTitle("Student Information");
				alertStudData.setMessage("Student Name : "
						+ dbHelper.studentList.get(position).getStdName()
						+ "\n\nStudent Roll Number : "
						+ dbHelper.studentList.get(position).getRno()
						+ "\n\nStudent Id : "
						+ dbHelper.studentList.get(position).getStudId());

				alertStudData.show();
			}
		});

	}

	class StudentAdapter extends BaseAdapter {
		Context mContext;

		public StudentAdapter(Context context) {
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

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (infalterStud == null) {
				infalterStud = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = infalterStud.inflate(R.layout.custom_stud_layout,
						null);
			}
			TextView stdRno = (TextView) convertView
					.findViewById(R.id.tvStudentRno);
			TextView stdName = (TextView) convertView
					.findViewById(R.id.tvStudentName);
			TextView stdId = (TextView) convertView
					.findViewById(R.id.tvStudentId);

			stdRno.setText(String.valueOf("Roll Number : "+dbHelper.studentList.get(position)
					.getRno()));
			stdName.setText("Name : "+dbHelper.studentList.get(position).getStdName());
			stdId.setText(String.valueOf("Student Id : " +dbHelper.studentList.get(position)
					.getStudId()));

			return convertView;
		}

	}// END OF Class 'StudentAdapter'

	class AsyStudent extends AsyncTask<Void, Void, Void> {

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
			dbHelper.displayStudentData();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			if (pd.isShowing()) {
				pd.dismiss();
			}
			listviewStudent.setAdapter(new StudentAdapter(
					DisplayStudentActivity.this));
			Toast.makeText(
					DisplayStudentActivity.this,
					getResources().getString(R.string.toastTotalRecordsFound)
							+ String.valueOf(listviewStudent.getCount()),
					Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}

	}// END OF AsyClass

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Toast.makeText(DisplayStudentActivity.this, "You Clicked a Student",
				Toast.LENGTH_LONG).show();

	}

}

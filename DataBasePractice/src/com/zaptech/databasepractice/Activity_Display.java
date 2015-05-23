package com.zaptech.databasepractice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Display extends Activity {
	ListView listData;
	DBHelper myDB;
	LayoutInflater inflator;
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__display);
		init();
		new Asy().execute();
	}

	public void init() {
		listData = (ListView) findViewById(R.id.listData);
		myDB = new DBHelper(Activity_Display.this);
		pd = new ProgressDialog(Activity_Display.this);
		listData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(Activity_Display.this, "Clicked",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	class CustomAdapter extends BaseAdapter {
		Context context;

		public CustomAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {

			return myDB.al.size();
		}

		@Override
		public Object getItem(int position) {

			return myDB.al.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (inflator == null) {
				inflator = (LayoutInflater) context
						.getSystemService(LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflator.inflate(R.layout.customlayout, null);
			}
			TextView tvUsername = (TextView) convertView
					.findViewById(R.id.tvUsername);
			TextView tvPassword = (TextView) convertView
					.findViewById(R.id.tvPassword);
			tvUsername.setText(myDB.al.get(position).getStrUsername());
			tvPassword.setText(myDB.al.get(position).getStrPassword());
			return convertView;
		}

	}

	class Asy extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			pd.setTitle(R.string.progressDialogTitleLoading);
			pd.setMessage(String.valueOf(R.string.progressDialogMessageLoading));
			pd.setCancelable(false);
			// pd.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			myDB.display();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (pd.isShowing()) {
				pd.dismiss();
			}
			listData.setAdapter(new CustomAdapter(Activity_Display.this));
			Toast.makeText(Activity_Display.this,
					R.string.toastDisplay + listData.getCount(),
					Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}
	}

}

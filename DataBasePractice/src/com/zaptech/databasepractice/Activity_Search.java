package com.zaptech.databasepractice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class Activity_Search extends Activity {
	ListView listSearchData;
	DBHelper myDB;
	LayoutInflater inflator;
	ProgressDialog pd;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__search);
		init();
		intent = getIntent();
		new Asy().execute();
	}

	public void init() {
		listSearchData = (ListView) findViewById(R.id.listSearchData);
		myDB = new DBHelper(Activity_Search.this);
		pd = new ProgressDialog(Activity_Search.this);
		listSearchData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent(Activity_Search.this,
						Activity_MappingResult.class);
				intent.putExtra("USERNAMEMAP", myDB.al.get(position)
						.getStrUsername().toString());
				Toast.makeText(Activity_Search.this,
						myDB.al.get(position).getStrUsername().toString(),
						Toast.LENGTH_LONG).show();
				startActivity(intent);
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

			// if (inflator == null) {
			inflator = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// }

			// if (convertView == null) {
			convertView = inflator.inflate(R.layout.customlayout, null);
			// }
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
			myDB.search(intent.getStringExtra("USERNAME"));
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (pd.isShowing()) {
				pd.dismiss();
			}
			listSearchData.setAdapter(new CustomAdapter(Activity_Search.this));
			Toast.makeText(Activity_Search.this,
					R.string.toastDisplay + listSearchData.getCount(),
					Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}
	}
}

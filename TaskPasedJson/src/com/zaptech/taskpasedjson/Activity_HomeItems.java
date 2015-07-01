package com.zaptech.taskpasedjson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_HomeItems extends Activity implements OnClickListener {
	ListView listHomeItems;
	DBHelper dbHelper;
	LayoutInflater inflater;
	ProgressDialog mProgress;
	Intent intent;
	Button btn_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_items);
		init();
		new DisplayDataAsync().execute();
		listHomeItems.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent(Activity_HomeItems.this,
						Activity_HomeItemsDetail.class);
				intent.putExtra("TITLE",
						dbHelper.arrayListHomeItems.get(position)
								.getHomeItem_title());
				startActivity(intent);
			}
		});
	}

	public void onBackPressed() {

		super.onBackPressed();
		finish();

	}

	public void init() {
		listHomeItems = (ListView) findViewById(R.id.list_HomeItems);
		dbHelper = new DBHelper(Activity_HomeItems.this);
		mProgress = new ProgressDialog(this);
		btn_back = (Button) findViewById(R.id.btn_BackFromHomeItems_Items);
		btn_back.setOnClickListener(this);
	}

	class DisplayData extends BaseAdapter {
		Context context;

		public DisplayData(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {

			return dbHelper.arrayListHomeItems.size();
		}

		@Override
		public Object getItem(int position) {

			return dbHelper.arrayListHomeItems.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (inflater == null) {
				inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.custom_homeitems, null);
			}
			TextView txt_HomeItemId = (TextView) convertView
					.findViewById(R.id.txt_HomeItemTitle);

			txt_HomeItemId.setText(dbHelper.arrayListHomeItems.get(position)
					.getHomeItem_title());

			return convertView;
		}

	}

	class DisplayDataAsync extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			mProgress.setTitle("Content Loader");
			mProgress.setMessage("Please wait, Loading Contents...");
			mProgress.setCancelable(false);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			dbHelper.displayHomeItems();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
			listHomeItems.setAdapter(new DisplayData(Activity_HomeItems.this));
			Toast.makeText(Activity_HomeItems.this,
					"Total Record Found : " + listHomeItems.getCount(),
					Toast.LENGTH_SHORT).show();

			super.onPostExecute(result);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_BackFromHomeItems_Items:
			finish();
			Intent intent = new Intent(Activity_HomeItems.this,
					HomeActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}

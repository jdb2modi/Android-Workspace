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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Activity_NewsItems extends Activity implements OnClickListener {
	Button btn_backFromNewsItem;
	ListView listview_NewsItem;
	DBHelper dbHelper;
	ProgressDialog mProgress;
	Intent intent;
	LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsitems);
		init();
		new DisplayNewsItemsAsync().execute();
	}

	public void init() {
		dbHelper = new DBHelper(this);
		mProgress = new ProgressDialog(this);
		btn_backFromNewsItem = (Button) findViewById(R.id.btn_BackFromNewsItems);
		btn_backFromNewsItem.setOnClickListener(this);
		listview_NewsItem = (ListView) findViewById(R.id.listview_NewsItem);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_BackFromNewsItems:
			finish();
			intent = new Intent(Activity_NewsItems.this, HomeActivity.class);
			startActivity(intent);

			break;

		default:
			break;
		}

	}

	class DisplayNewsItems extends BaseAdapter {
		Context mContext;

		DisplayNewsItems(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {

			return dbHelper.arrayListNewsItems.size();
		}

		@Override
		public Object getItem(int position) {

			return dbHelper.arrayListNewsItems.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (inflater == null) {
				inflater = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.custom_newsitems, null);
			}
			TextView txt_NewsId, txt_NewsTabText;
			txt_NewsId = (TextView) convertView
					.findViewById(R.id.txt_NewsItemId);
			txt_NewsTabText = (TextView) convertView
					.findViewById(R.id.txt_NewsItemTabText);
			txt_NewsId.setText(dbHelper.arrayListNewsItems.get(position)
					.getId());
			txt_NewsTabText.setText(dbHelper.arrayListNewsItems.get(position)
					.getTabText());
			return convertView;
		}

	}

	class DisplayNewsItemsAsync extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			mProgress.setTitle("News Loader");
			mProgress.setMessage("Please Wait, Loading News...");
			mProgress.setCancelable(false);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			dbHelper.displayNewsItems();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
			listview_NewsItem.setAdapter(new DisplayNewsItems(
					Activity_NewsItems.this));
			super.onPostExecute(result);
		}

	}
}

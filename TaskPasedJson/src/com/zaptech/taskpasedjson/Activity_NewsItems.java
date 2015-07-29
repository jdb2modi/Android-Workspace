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

import com.zaptech.taskpasedjson.database.DBHelper;

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

		listview_NewsItem.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int intId = dbHelper.arrayListNewsItems.get(position).getId();
				finish();
				Intent intent = new Intent(Activity_NewsItems.this,
						Activity_NewsItem_Items.class);
				intent.putExtra("NEWSID", intId);
				startActivity(intent);
				
			}
		});

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
			inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

			ViewHolder holder;

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.custom_newsitems,
						parent, false);
				holder = new ViewHolder();
				holder.txt_NewsId = (TextView) convertView
						.findViewById(R.id.txt_NewsItemId);
				holder.txt_NewsTabText = (TextView) convertView
						.findViewById(R.id.txt_NewsItemTabText);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.txt_NewsId.setText(""
					+ dbHelper.arrayListNewsItems.get(position).getId());
			holder.txt_NewsTabText.setText(""
					+ dbHelper.arrayListNewsItems.get(position).getTabText());
			return convertView;
		}

	}

	static class ViewHolder {
		TextView txt_NewsId;
		TextView txt_NewsTabText;
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

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
import com.zaptech.taskpasedjson.models.Model_Headline;
import com.zaptech.taskpasedjson.models.Model_NewsItem_Items;

public class Activity_NewsItem_Items extends Activity implements
		OnClickListener {
	DBHelper dbHelper;
	ListView listView_NewsItem_Items;
	LayoutInflater inflater;
	ProgressDialog mProgress;
	int temp;
	Button btn_back;
	Model_NewsItem_Items model_NewsItems_Item;
	Model_Headline model_Headline;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsitem_items);
		init();
		Intent i = getIntent();
		temp = i.getIntExtra("NEWSID", 0);
		new DisplayHeadLineAsync().execute();
		listView_NewsItem_Items
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						int intId = dbHelper.arrayListNewsItems_Items.get(
								position).getNewsItems_ItemId();
						finish();
						Intent intent = new Intent(
								Activity_NewsItem_Items.this,
								Activity_NewsItemDetail.class);
						intent.putExtra("HeadLineId", intId);
						startActivity(intent);
					}
				});
	}

	public void init() {
		dbHelper = new DBHelper(Activity_NewsItem_Items.this);
		listView_NewsItem_Items = (ListView) findViewById(R.id.listview_NewsItems_Items);
		mProgress = new ProgressDialog(this);
		btn_back = (Button) findViewById(R.id.btn_BackFromNewsItems_Items);
		btn_back.setOnClickListener(this);
		model_NewsItems_Item = new Model_NewsItem_Items();
		model_Headline = new Model_Headline();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_BackFromNewsItems_Items:
			finish();
			Intent intent = new Intent(Activity_NewsItem_Items.this,
					Activity_NewsItems.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

	class DisplayHeadLines extends BaseAdapter {
		Context context;

		DisplayHeadLines(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {

			return dbHelper.arrayListNewsItems_Items.size();
		}

		@Override
		public Object getItem(int position) {

			return dbHelper.arrayListNewsItems_Items.get(position);
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
				convertView = inflater.inflate(R.layout.customnewsitems_items,
						null);
			}
			/*
			 * TextView txt_HeadLine = (TextView) convertView
			 * .findViewById(R.id.txt_HeadLineToDisplay);
			 */
			TextView txt_HeadLine = (TextView) convertView
					.findViewById(R.id.txt_HeadLineToDisplay);

			txt_HeadLine.setText(dbHelper.arrayListNewsItems_Items
					.get(position).getModel_headline().getTheString());

			return convertView;
		}
	}

	class DisplayHeadLineAsync extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			mProgress.setTitle("Headline Loader");
			mProgress.setMessage("Please wait, Loading Headlines...");
			mProgress.setCancelable(false);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			dbHelper.getItems(temp);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
			listView_NewsItem_Items.setAdapter(new DisplayHeadLines(
					Activity_NewsItem_Items.this));
			super.onPostExecute(result);
		}
	}
	/*
	 * public void getHeadLineData() { String str = "SELECT * FROM " +
	 * dbHelper.TB_NewsItems_Items + " where " + dbHelper.COL_NEWSITEM_NewsId +
	 * "=" + temp; dbHelper.sqlite=dbHelper.getWritableDatabase(); Cursor cursor
	 * = dbHelper.sqlite.rawQuery(str, null); if (cursor != null) { if
	 * (cursor.moveToFirst()) { do { Toast.makeText( getApplicationContext(),
	 * "IDS " + cursor.getString(cursor
	 * .getColumnIndex(dbHelper.COL_NEWSITEM_ItemId)),
	 * Toast.LENGTH_LONG).show();
	 * 
	 * //CHECKING HEADLINE TABLE... String str2 = "SELECT * FROM " +
	 * dbHelper.TB_HeadLine + " where " + "newsItem_ItemId" + "=" +
	 * cursor.getString(cursor .getColumnIndex(dbHelper.COL_NEWSITEM_ItemId));
	 * Cursor cursor2 = dbHelper.sqlite.rawQuery(str2, null); if(cursor2!=null){
	 * if(cursor2.moveToFirst()){ do{ Toast.makeText( getApplicationContext(),
	 * "IDS SUBBB " + cursor2.getString(cursor2
	 * .getColumnIndex("newsItem_ItemId")), Toast.LENGTH_LONG).show();
	 * }while(cursor2.moveToNext()); } }
	 * 
	 * } while (cursor.moveToNext()); } } }
	 */
}

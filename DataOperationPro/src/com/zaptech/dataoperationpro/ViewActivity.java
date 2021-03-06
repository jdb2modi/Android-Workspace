package com.zaptech.dataoperationpro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends Activity implements OnClickListener {
	MyDatabase mdb;
	EditText edAge;
	Button btnSearch, btnGoBack;
	TextView tvSearch;
	ListView listSearch;

	LayoutInflater inflater;
	MyCustomAdapter2 customAdapter;
	ProgressDialog pd;

	MyModel mm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_view);

		// CALLING METHODS
		init();
		listSearch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:+9408776491"));
				startActivity(intent);

			}
		});
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();

	}

	// METHOD TO INITIALIZE VARIABLES AND ADDING LISTENERS TO IT
	public void init() {

		mdb = new MyDatabase(this);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		btnGoBack = (Button) findViewById(R.id.btnGoBackFromSearching);
		edAge = (EditText) findViewById(R.id.edSearchAge);
		tvSearch = (TextView) findViewById(R.id.tvViewData);
		listSearch = (ListView) findViewById(R.id.listSearch);
		mm = new MyModel();

		btnGoBack.setOnClickListener(this);
		btnSearch.setOnClickListener(this);
		pd = new ProgressDialog(ViewActivity.this);
	}

	/*
	 * public void searchData() { list.clear();
	 * 
	 * String sqlSearch = "SELECT * FROM " + MyDatabase.TABLE_TEMP +
	 * " where Age='" + edAge.getText().toString() + "'"; Cursor c =
	 * mdb.getDB().rawQuery(sqlSearch, null);
	 * 
	 * if (c.moveToFirst()) { do { MyModel model = new MyModel();
	 * 
	 * model.setStrName(c.getString(c .getColumnIndex(MyDatabase.COL_NAME)));
	 * model.setAge(Integer.parseInt(c.getString(c
	 * .getColumnIndex(MyDatabase.COL_AGE)))); list.add(model); } while
	 * (c.moveToNext()); } listSearch.setAdapter(customAdapter);
	 * 
	 * }
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSearch:
			new DisplaySearchData().execute();
			break;
		case R.id.btnGoBackFromSearching:
			this.finish();

			break;

		default:
			break;
		}

	}

	// A BASE ADAPTER CLASS
	public class MyCustomAdapter2 extends BaseAdapter {
		Context mc;

		public MyCustomAdapter2(Context mc) {
			this.mc = mc;

		}

		@Override
		public int getCount() {
			return mdb.dataList.size();

		}

		@Override
		public Object getItem(int position) {

			return mdb.dataList.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (inflater == null)
				inflater = (LayoutInflater) mc
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (convertView == null)
				convertView = inflater.inflate(R.layout.list_item_raw, null);

			TextView name = (TextView) convertView.findViewById(R.id.tvName);
			TextView age = (TextView) convertView.findViewById(R.id.tvAge);

			name.setText(mdb.dataList.get(position).getStrName());
			age.setText(String.valueOf(mdb.dataList.get(position).getAge()));

			return convertView;
		}

	}

	// FUNCTION TO SET ADAPTER TO LISTVIEW
	public void setAdapterToListView() {
		// mm.setAge(Integer.parseInt(edAge.getText().toString()));
		mdb.searchData(Integer.parseInt(edAge.getText().toString()));
	}

	// ASYNCHRONUS TASK TO DISPLAY SEARCH RESULT
	public class DisplaySearchData extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			pd.setTitle("Loading........");
			pd.setCancelable(false);
			pd.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// mm : Object of the MyModel Class
			// mm.setAge(Integer.parseInt(edAge.getText().toString()));
			// mdb.searchData(mm.getAge());
			setAdapterToListView();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (pd.isShowing()) {
				pd.dismiss();

			}

			listSearch.setAdapter(new MyCustomAdapter2(ViewActivity.this));
			if (listSearch.getCount() == 0) {
				Toast.makeText(ViewActivity.this,
						getResources().getString(R.string.txtToastNoDataFound),
						Toast.LENGTH_LONG).show();
			} else {

				Toast.makeText(
						ViewActivity.this,
						getResources().getString(R.string.txtToastDataFound)
								+ "" + String.valueOf(listSearch.getCount()),
						2500).show();
			}
			super.onPostExecute(result);
		}
	}
}

package com.zaptech.dataoperationpro;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewAllActivity extends Activity implements OnClickListener {
	ListView datalistView;
	Button btnGoBack;
	MyDatabase mdb;
	ProgressDialog pd;


	public static LayoutInflater inflater;
	MyCustomAdapter customAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_all);

		Intent iGet = getIntent();

		// Method Calls
		init();
		// getData();

		new DisplayData().execute();// Executing Asyncronus class

		// WHEN LISTVIEW ITEM WILL BE CLICKED
		datalistView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:+9408776491"));
				startActivity(intent);
				/*
				 * Toast.makeText(ViewAllActivity.this, "Clicked",
				 * Toast.LENGTH_SHORT).show();
				 */
			}
		});

	}

	// Initializing variables...
	public void init() {

		mdb = new MyDatabase(getApplicationContext());
		customAdapter = new MyCustomAdapter(this);
		datalistView = (ListView) findViewById(R.id.listViewAll);
		btnGoBack = (Button) findViewById(R.id.btnGoBackFromViewAll);
		pd = new ProgressDialog(ViewAllActivity.this);

		// Adding Listener
		btnGoBack.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGoBackFromViewAll:

			this.finish();
			break;

		default:
			break;
		}
	}

	// To get or transfer the data into ArrayList Object

	// Custom Adapter Class to Display Data into ListView
	public class MyCustomAdapter extends BaseAdapter {

		Context mcontextNew;

		public MyCustomAdapter(Context context) {
			this.mcontextNew = context;
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// Checking Inflator..
			if (inflater == null)
				inflater = (LayoutInflater) mcontextNew
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// Inflating Custom View...
			if (convertView == null)
				convertView = inflater.inflate(R.layout.list_item_raw, null);

			// Getting Reference of the Variables...
			TextView name = (TextView) convertView.findViewById(R.id.tvName);
			TextView age = (TextView) convertView.findViewById(R.id.tvAge);

			// Setting Values for the Variables of the Model Class...
			name.setText(mdb.dataList.get(position).getStrName());
			age.setText(String.valueOf(mdb.dataList.get(position).getAge()));

			return convertView;

		}

	}

	// A FUNCTION TO SET AN ADAPTER TO THE LISTVIEW
	public void addAdapter() {

		datalistView.setAdapter(new MyCustomAdapter(ViewAllActivity.this));
		Toast.makeText(
				ViewAllActivity.this,
				getResources().getString(R.string.txtToastDataFound)
						+ String.valueOf(datalistView.getCount()),
				Toast.LENGTH_LONG).show();

	}

	public class DisplayData extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			pd.setTitle("Loading........");
			pd.setCancelable(false);
			pd.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			mdb.displayData();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (pd.isShowing()) {
				pd.dismiss();

			}
			// CALLING THE FUNCTION TO SET ADAPTER TO LISTVIEW
			addAdapter();
			super.onPostExecute(result);
		}
	}
}

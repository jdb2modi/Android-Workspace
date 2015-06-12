package com.ifactory.myexpenditure;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_ViewHistory extends Activity implements OnClickListener {

	LayoutInflater mInflater;
	ProgressDialog mProgressDialog;
	DBHelper dbHelper;
	ListView listExpenceHistory;
	Intent intent;
	String strHistory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_history);
		init();
		dbHelper = new DBHelper(Activity_ViewHistory.this);
		dbHelper.getWritableDatabase();
		intent = getIntent();
		strHistory = intent.getStringExtra("HISTORY");
		new ExpenceAsync().execute();

		Toast.makeText(Activity_ViewHistory.this, strHistory,
				Toast.LENGTH_SHORT).show();
	}

	public void init() {
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setTitle("Loading..");
		mProgressDialog.setMessage("Loading Expence History...");
		mProgressDialog.setCancelable(false);
		listExpenceHistory = (ListView) findViewById(R.id.listHistory);
	}

	class DisplayHistoryAdpt extends BaseAdapter {
		Context context;

		public DisplayHistoryAdpt(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {

			return dbHelper.arrayListExpence.size();
		}

		@Override
		public Object getItem(int position) {

			return dbHelper.arrayListExpence.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (mInflater == null) {
				mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.expence_history_model,
						null);

			}
			TextView txt_ExpenseCategoryD = (TextView) convertView
					.findViewById(R.id.txt_ExpenceCategoryH);
			TextView txt_ExpenseDateD = (TextView) convertView
					.findViewById(R.id.txt_ExpenceDateH);
			TextView txt_ExpenseModeD = (TextView) convertView
					.findViewById(R.id.txt_ExpenceModeH);
			TextView txt_ChequeNoD = (TextView) convertView
					.findViewById(R.id.txt_ChequeNoH);
			TextView txt_TransactionIdD = (TextView) convertView
					.findViewById(R.id.txt_TransactionIdH);
			TextView txt_ExpenseAmountD = (TextView) convertView
					.findViewById(R.id.txt_ExpenceAmountH);
			TextView txt_DescriptionD = (TextView) convertView
					.findViewById(R.id.txt_ExpenceDescriptionH);

			txt_ExpenseCategoryD.setText(dbHelper.arrayListExpence
					.get(position).getExpenseCategory());
			txt_ExpenseDateD.setText(dbHelper.arrayListExpence.get(position)
					.getExpenseDate());
			txt_ExpenseModeD.setText(dbHelper.arrayListExpence.get(position)
					.getExpenseMode());
			txt_ChequeNoD.setText(dbHelper.arrayListExpence.get(position)
					.getChequeNo());
			txt_TransactionIdD.setText(dbHelper.arrayListExpence.get(position)
					.getTransactionId());
			txt_ExpenseAmountD.setText(String.valueOf(dbHelper.arrayListExpence
					.get(position).getExpenseAmount()));
			txt_DescriptionD.setText(dbHelper.arrayListExpence.get(position)
					.getDescription());
			return convertView;
		}
	}

	class ExpenceAsync extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			mProgressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			if (strHistory.equals("ALL")) {
				dbHelper.displayHistory();
			} else if (strHistory.equals("SPECIFIC")) {
				dbHelper.displaySpecificHistory();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
			listExpenceHistory.setAdapter(new DisplayHistoryAdpt(
					Activity_ViewHistory.this));
			super.onPostExecute(result);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		default:
			break;
		}

	}
}

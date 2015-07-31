package com.zaptech.myexpenditure;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.Toast;

public class Activity_ShowBankDetails extends Activity implements
		OnClickListener {
	private Button mBtn_exit;
	private Button mBtn_back;
	private DBHelper dbHelper;
	private ListView listBankDetails;
	private LayoutInflater inflater;
	private ArrayList<ModelBankDetails> arrayListBankDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showbankdetails);
		init();
		setTypeface();
		new DisplayBankDetailsAsync().execute();
	}

	public void init() {
		arrayListBankDetails = new ArrayList<ModelBankDetails>();
		dbHelper = new DBHelper(Activity_ShowBankDetails.this);
		listBankDetails = (ListView) findViewById(R.id.listBankDetails);
		mBtn_exit = (Button) findViewById(R.id.btn_exitFromShowBankDetails);
		mBtn_exit.setOnClickListener(this);
		mBtn_back = (Button) findViewById(R.id.btn_backFromShowBankDetails);
		mBtn_back.setOnClickListener(this);
	}

	public class DisplayBankDetails extends BaseAdapter {
		Context context;

		public DisplayBankDetails(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {

			return dbHelper.arrayListBankDetails.size();
		}

		@Override
		public Object getItem(int position) {

			return dbHelper.arrayListBankDetails.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (inflater == null) {
				inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.custom_bankdetails,
						null);
			}
			TextView txt_accountNumber = (TextView) convertView
					.findViewById(R.id.txt_accountNumber);
			TextView txt_bankName = (TextView) convertView
					.findViewById(R.id.txt_bankName);
			TextView txt_currentBalance = (TextView) convertView
					.findViewById(R.id.txt_currentBalance);
			txt_accountNumber.setText(dbHelper.arrayListBankDetails
					.get(position).getAccountNumber().toString());
			txt_bankName.setText(dbHelper.arrayListBankDetails.get(position)
					.getBankName().toString());
			txt_currentBalance.setText(dbHelper.arrayListBankDetails
					.get(position).getCurrentBalance().toString());
			return convertView;
		}

	}

	public class DisplayBankDetailsAsync extends AsyncTask<Void, Void, Void> {
		private ProgressDialog mProgress = new ProgressDialog(
				Activity_ShowBankDetails.this);

		@Override
		protected void onPreExecute() {
			mProgress.setTitle("Loading...");
			mProgress.setMessage("Please wait, Loading Bank Details...");
			mProgress.setCancelable(false);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			dbHelper.showBankDetails();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
			listBankDetails.setAdapter(new DisplayBankDetails(
					Activity_ShowBankDetails.this));
			if (listBankDetails.getCount() <= 0) {
				Toast.makeText(Activity_ShowBankDetails.this,
						"No Records Found.!!", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(Activity_ShowBankDetails.this,
						"Total Records Found : " + listBankDetails.getCount(),
						Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_exitFromShowBankDetails:
			finish();
			break;
		case R.id.btn_backFromShowBankDetails:
			finish();
			Intent intent = new Intent(Activity_ShowBankDetails.this,
					Activity_ManageBanking.class);
			overridePendingTransition(R.anim.in_from_left_activity,
					R.anim.out_to_right_activity);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getAssets(),
				"fonts/Tahoma.ttf");

		mBtn_exit.setTypeface(tyFace);
		mBtn_back.setTypeface(tyFace);

	}
}

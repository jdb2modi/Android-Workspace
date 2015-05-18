package com.zaptech.moneymanager;

import com.zaptech.moneymanager.ExpenceHistory.ExpenceHistoryAdapter;
import com.zaptech.moneymanager.ExpenceHistory.ExpenceLoad;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class IncomeHistory extends Activity implements OnClickListener {
	ImageButton imgBtnHome, imgBtnBack, imgBtnClose;
	ListView listIncomeHistory;
	DBHelper dbHelper;
	LayoutInflater inflator;
	IncomeHistoryAdapter ihAdapter;
	ProgressDialog pd;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.income_history);
		init();
		new IncomeLoad().execute();
	}

	public void init() {
		imgBtnBack = (ImageButton) findViewById(R.id.imageButtonBackOnIncomeHistory);
		imgBtnBack.setOnClickListener(this);
		imgBtnClose = (ImageButton) findViewById(R.id.imageButtonCloseOnIncomeHistory);
		imgBtnClose.setOnClickListener(this);
		imgBtnHome = (ImageButton) findViewById(R.id.imageButtonHomeOnIncomeHistory);
		imgBtnHome.setOnClickListener(this);
		listIncomeHistory = (ListView) findViewById(R.id.listIncomeHistory);

		dbHelper = new DBHelper(this);
		ihAdapter = new IncomeHistoryAdapter(IncomeHistory.this);
		pd = new ProgressDialog(this);
	}

	public void exitConfirmation() {
		AlertDialog.Builder ab = new AlertDialog.Builder(IncomeHistory.this);
		ab.setTitle(getString(R.string.alertExitTitle));
		ab.setMessage(getString(R.string.alertExitMessege));
		ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				IncomeHistory.this.finish();
			}
		});
		ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		ab.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButtonCloseOnIncomeHistory:
			exitConfirmation();
			break;
		case R.id.imageButtonBackOnIncomeHistory:
			this.finish();
			Intent i = new Intent(IncomeHistory.this, History.class);
			startActivity(i);
			break;
		case R.id.imageButtonHomeOnIncomeHistory:
			this.finish();
			intent = new Intent(IncomeHistory.this, HomeActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	class IncomeHistoryAdapter extends BaseAdapter {
		Context mContext;

		public IncomeHistoryAdapter(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {

			return dbHelper.iHistoryModelList.size();
		}

		@Override
		public Object getItem(int position) {

			return dbHelper.iHistoryModelList.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView,
				ViewGroup parentGroup) {
			if (inflator == null) {
				inflator = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflator.inflate(R.layout.income_history_layout,
						null);
			}
			TextView tvTitle = (TextView) convertView
					.findViewById(R.id.tvModelIncomeTitleValue);
			TextView tvAmount = (TextView) convertView
					.findViewById(R.id.tvModelIncomeAmountValue);
			TextView tvDescription = (TextView) convertView
					.findViewById(R.id.tvModelIncomeDescriptionValue);

			tvTitle.setText(dbHelper.iHistoryModelList.get(position).getTitle());
			tvAmount.setText(String.valueOf(dbHelper.iHistoryModelList.get(
					position).getAmount()));
			tvDescription.setText(dbHelper.iHistoryModelList.get(position)
					.getDescription());
			return convertView;
		}

	}

	public void displayToast() {
		if (listIncomeHistory.getCount() <= 0) {
			Toast.makeText(IncomeHistory.this, "History Not Available",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(IncomeHistory.this,
					"Total Records Found : " + listIncomeHistory.getCount(),
					Toast.LENGTH_SHORT).show();
		}
	}

	class IncomeLoad extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			pd.setTitle("Loading");
			pd.setMessage("Please Wait...");
			pd.setCancelable(false);
			pd.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			dbHelper.displayIncomeHistory();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (pd.isShowing()) {
				pd.dismiss();
			}
			listIncomeHistory.setAdapter(new IncomeHistoryAdapter(
					IncomeHistory.this));

			displayToast();

			super.onPostExecute(result);
		}
	}

}
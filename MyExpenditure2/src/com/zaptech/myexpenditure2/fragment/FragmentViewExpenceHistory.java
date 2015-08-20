package com.zaptech.myexpenditure2.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zaptech.myexpenditure2.R;

public class FragmentViewExpenceHistory extends Fragment {
	private ListView mlistExpenceHistory;
	private LayoutInflater mInflater;
	private ProgressDialog mProgressDialog;
	private com.zaptech.myexpenditure2.database.DBHelper mdbHelper;
	private String strHistory, strSDate, strEDate;

	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_view_expencehistory,
				container, false);
		init(rootView);
		Bundle bundle = this.getArguments();
		strHistory = bundle.getString("HISTORY");
		strSDate = bundle.getString("startdate");
		strEDate = bundle.getString("enddate");
		System.err
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>startdate:"
						+ strSDate);
		System.err
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>enddate:"
						+ strEDate);
		new ExpenceAsync().execute();
		return rootView;
	}

	public void init(View rootView) {
		mlistExpenceHistory = (ListView) rootView
				.findViewById(R.id.listHistory);
		mdbHelper = new com.zaptech.myexpenditure2.database.DBHelper(
				getActivity().getApplicationContext());
		mdbHelper.getWritableDatabase();
		mProgressDialog = new ProgressDialog(getActivity());
		mProgressDialog.setTitle("Loading..");
		mProgressDialog.setMessage("Loading Expence History...");
		mProgressDialog.setCancelable(false);

	}

	class DisplayHistoryAdpt extends BaseAdapter {
		Context context;

		public DisplayHistoryAdpt(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {

			return mdbHelper.arrayListExpence.size();
		}

		@Override
		public Object getItem(int position) {

			return mdbHelper.arrayListExpence.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (mInflater == null) {
				mInflater = (LayoutInflater) getActivity().getSystemService(
						getActivity().LAYOUT_INFLATER_SERVICE);
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

			txt_ExpenseCategoryD.setText(mdbHelper.arrayListExpence.get(
					position).getExpenseCategory());
			txt_ExpenseDateD.setText(mdbHelper.arrayListExpence.get(position)
					.getExpenseDate());
			txt_ExpenseModeD.setText(mdbHelper.arrayListExpence.get(position)
					.getExpenseMode());
			txt_ChequeNoD.setText(mdbHelper.arrayListExpence.get(position)
					.getChequeNo());
			txt_TransactionIdD.setText(mdbHelper.arrayListExpence.get(position)
					.getTransactionId());
			txt_ExpenseAmountD.setText(String
					.valueOf(mdbHelper.arrayListExpence.get(position)
							.getExpenseAmount()));
			txt_DescriptionD.setText(mdbHelper.arrayListExpence.get(position)
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
				mdbHelper.displayHistory();
			} else if (strHistory.equals("SPECIFIC")) {
				mdbHelper.displaySpecificHistory(strSDate, strEDate);
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
			mlistExpenceHistory.setAdapter(new DisplayHistoryAdpt(getActivity()
					.getApplicationContext()));
			if (mlistExpenceHistory.getCount() <= 0) {
				Toast.makeText(getActivity().getApplicationContext(),
						"No Records Found.!!", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(
						getActivity().getApplicationContext(),
						"Total Records Found : "
								+ mlistExpenceHistory.getCount(),
						Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}

	}

}

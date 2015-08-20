package com.zaptech.myexpenditure2.fragment;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zaptech.myexpenditure2.R;
import com.zaptech.myexpenditure2.database.DBHelper;
import com.zaptech.myexpenditure2.model.ModelBankDetails;

public class FragmentBankHistory extends Fragment implements OnClickListener {
	private ListView mlistBankDetails;
	private DBHelper dbHelper;
	private ArrayList<ModelBankDetails> arrayListBankDetails;
	private LayoutInflater inflater;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_bankhistory, container,
				false);
		init(rootView);
		new DisplayBankDetailsAsync().execute();
		return rootView;
	}

	public void init(View rootView) {
		arrayListBankDetails = new ArrayList<ModelBankDetails>();
		dbHelper = new DBHelper(getActivity());
		mlistBankDetails = (ListView) rootView
				.findViewById(R.id.listBankDetails);

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
				inflater = (LayoutInflater) getActivity().getSystemService(
						getActivity().LAYOUT_INFLATER_SERVICE);
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
		private ProgressDialog mProgress = new ProgressDialog(getActivity());

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
			mlistBankDetails.setAdapter(new DisplayBankDetails(getActivity()));
			if (mlistBankDetails.getCount() <= 0) {
				Toast.makeText(getActivity(), "No Records Found.!!",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getActivity(),
						"Total Records Found : " + mlistBankDetails.getCount(),
						Toast.LENGTH_SHORT).show();
			}
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

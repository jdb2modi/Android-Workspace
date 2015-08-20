package com.zaptech.myexpenditure2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zaptech.myexpenditure2.R;
import com.zaptech.myexpenditure2.database.DBHelper;

public class FragmentAddBankDetails extends Fragment {

	private Button mbtnAddBank;
	private Button mbtnAddBankCancel;
	private Button mbtnAddBalance;
	private Button mbtnAddBalanceCancel;
	private Button mbtnShowBankDetails;
	private EditText medAccountNumber;
	private EditText medBankName;
	private EditText medCurrentBalance;
	private EditText medAccountNumberAddBalance;
	private EditText medAddBalance;
	private DBHelper dbHelper;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_addbankdetails,
				container, false);
		init(rootView);
		onClick();
		return rootView;
	}

	public void init(View rootView) {
		dbHelper = new DBHelper(getActivity());
		mbtnAddBank = (Button) rootView
				.findViewById(R.id.btn_addBankDetailsNow);

		mbtnAddBankCancel = (Button) rootView
				.findViewById(R.id.btn_cancelBankDetailsNow);
		mbtnAddBalance = (Button) rootView.findViewById(R.id.btn_addBalanceNow);
		mbtnAddBalanceCancel = (Button) rootView
				.findViewById(R.id.btn_cancelAddBalanceNow);
		mbtnShowBankDetails = (Button) rootView
				.findViewById(R.id.btn_showBankDetails);

		medAccountNumber = (EditText) rootView
				.findViewById(R.id.ed_accountNumber);
		medBankName = (EditText) rootView.findViewById(R.id.ed_bankName);
		medCurrentBalance = (EditText) rootView
				.findViewById(R.id.ed_currentBalance);
		medAccountNumberAddBalance = (EditText) rootView
				.findViewById(R.id.ed_accountNumberAddBalance);
		medAddBalance = (EditText) rootView.findViewById(R.id.ed_addBalance);
	}

	public void onClick() {

		mbtnAddBank.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mValidate();
			}
		});
		mbtnAddBankCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mbtnAddBalance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mbtnAddBalanceCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mbtnShowBankDetails.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

	private void mValidate() {

		if (medAccountNumber.getText().toString().trim().length() == 0) {
			medAccountNumber.setFocusable(true);
			Toast.makeText(getActivity(), "Please, Enter Account number",
					Toast.LENGTH_SHORT).show();
		} else if (medBankName.getText().toString().trim().length() == 0) {
			medBankName.setFocusable(true);
			Toast.makeText(getActivity(), "Please, Enter Bank name",
					Toast.LENGTH_SHORT).show();
		} else if (medCurrentBalance.getText().toString().trim().length() == 0) {
			medCurrentBalance.setFocusable(true);
			Toast.makeText(getActivity(), "Please, Enter Current balance",
					Toast.LENGTH_SHORT).show();
		} else {
			dbHelper.addBankDetails(medAccountNumber.getText().toString(),
					medBankName.getText().toString(), medCurrentBalance
							.getText().toString());
			Toast.makeText(getActivity(), "Bank details added successfully",
					Toast.LENGTH_SHORT).show();
			medAccountNumber.setText("");
			medBankName.setText("");
			medCurrentBalance.setText("");
			medAccountNumber.setFocusable(true);
		}
	}
}

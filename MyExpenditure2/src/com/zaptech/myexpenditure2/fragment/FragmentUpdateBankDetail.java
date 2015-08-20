package com.zaptech.myexpenditure2.fragment;

import java.util.ArrayList;

import android.content.Intent;
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

public class FragmentUpdateBankDetail extends Fragment implements
		OnClickListener {
	private EditText medAccountNumber;
	private EditText medBankName;
	private EditText medCurrentBalance;
	private Button mbtnUpdateNow;
	private DBHelper dbHelper;
	private String strAccountNoToUpdate;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_updatebankdetail,
				container, false);
		Bundle bundle = this.getArguments();
		strAccountNoToUpdate = bundle.getString("BankDetail");

		init(rootView);
		setValues();
		return rootView;
	}

	public void init(View rootView) {
		dbHelper = new DBHelper(getActivity());
		medAccountNumber = (EditText) rootView
				.findViewById(R.id.ed_updateaccountNumber);
		medBankName = (EditText) rootView.findViewById(R.id.ed_updatebankName);
		medCurrentBalance = (EditText) rootView
				.findViewById(R.id.ed_updatecurrentBalance);

		mbtnUpdateNow = (Button) rootView
				.findViewById(R.id.btn_updateBankDetailsNow);
		mbtnUpdateNow.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_updateBankDetailsNow:
			updateBankDetails();
			break;

		default:
			break;
		}

	}

	private void setValues() {
		ArrayList<String> arrayListBankDetails = new ArrayList<String>();
		arrayListBankDetails = dbHelper
				.getBankDetailsToUpdate(strAccountNoToUpdate);
		if (arrayListBankDetails.size() <= 0) {
			Toast.makeText(getActivity(), "No Record Found..!",
					Toast.LENGTH_SHORT).show();

		} else {
			String strAccountNo = arrayListBankDetails.get(0).toString();
			String strBankName = arrayListBankDetails.get(1).toString();
			String strCurrentBalance = arrayListBankDetails.get(2).toString();
			medAccountNumber.setText(strAccountNo);
			medBankName.setText(strBankName);
			medCurrentBalance.setText(strCurrentBalance);
		}

	}

	private void updateBankDetails() {

		dbHelper.updateBankDetails(medAccountNumber.getText().toString(),
				medBankName.getText().toString(), medCurrentBalance.getText()
						.toString());
		Toast.makeText(getActivity(), "Update Successfull", Toast.LENGTH_SHORT)
				.show();

	}

}

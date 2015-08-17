package com.zaptech.myexpenditure2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.zaptech.myexpenditure2.R;

public class FragmentAddBankDetails extends Fragment {
	private Button mBtnBack;
	private Button mBtnExit;
	private Button mBtnAddBank;
	private Button mBtnAddBankCancel;
	private Button mBtnAddBalance;
	private Button mBtnAddBalanceCancel;
	private Button mBtnShowBankDetails;

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
		mBtnBack = (Button) rootView.findViewById(R.id.btn_backFromBankDetails);
		mBtnExit = (Button) rootView.findViewById(R.id.btn_exitFromBankDetails);
		mBtnAddBank = (Button) rootView
				.findViewById(R.id.btn_addBankDetailsNow);
		mBtnAddBankCancel = (Button) rootView
				.findViewById(R.id.btn_cancelBankDetailsNow);
		mBtnAddBalance = (Button) rootView.findViewById(R.id.btn_addBalanceNow);
		mBtnAddBalanceCancel = (Button) rootView
				.findViewById(R.id.btn_cancelAddBalanceNow);
		mBtnShowBankDetails = (Button) rootView
				.findViewById(R.id.btn_showBankDetails);
	}

	public void onClick() {
		mBtnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fManageBank = new FragmentManageBanking();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fManageBank);
				//ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				//ft.addToBackStack(null);
				ft.commit();
			}
		});
		mBtnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnAddBank.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnAddBankCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnAddBalance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnAddBalanceCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnShowBankDetails.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}
}

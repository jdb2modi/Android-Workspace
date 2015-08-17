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

public class FragmentManageBanking extends Fragment {
	private Button mBtnAddBankDetails;
	private Button mBtnBankHistory;
	private Button mBtnUpdateBankDetails;
	private Button mBtnRemoveBankDetails;
	private Button mBtnClearAll;
	private Button mBtnBack;
	private Button mBtnExit;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_managebanking, container,
				false);
		init(rootView);
		onClick();
		return rootView;
	}

	public void init(View rootView) {
		mBtnAddBankDetails = (Button) rootView
				.findViewById(R.id.btn_addBankDetails);
		mBtnBankHistory = (Button) rootView.findViewById(R.id.btn_bankHistory);
		mBtnUpdateBankDetails = (Button) rootView
				.findViewById(R.id.btn_updateBankDetails);
		mBtnRemoveBankDetails = (Button) rootView
				.findViewById(R.id.btn_removeBank);
		mBtnClearAll = (Button) rootView
				.findViewById(R.id.btn_ClearAllBankHistory);
		mBtnBack = (Button) rootView
				.findViewById(R.id.btn_backFromManageBanking);
		mBtnExit = (Button) rootView
				.findViewById(R.id.btn_exitFromManageBanking);

	}

	public void onClick() {
		mBtnAddBankDetails.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fAddBankDetails = new FragmentAddBankDetails();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fAddBankDetails);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		mBtnBankHistory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fBankDetails = new FragmentBankHistory();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fBankDetails);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		mBtnUpdateBankDetails.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnRemoveBankDetails.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnClearAll.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fHome = new FragmentHome();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fHome);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		mBtnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}
}

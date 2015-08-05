package com.zaptech.myexpenditure2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentExpenceHistory extends Fragment {
	private Button mBtnBack;
	private Button mBtnExit;
	private Button mBtnStartDate;
	private Button mBtnEndDate;
	private Button mBtnShowHistory;
	private Button mBtnShowAllHistory;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_expencehistory,
				container, false);
		init(rootView);
		onClick();
		return rootView;
	}

	public void init(View rootView) {
		mBtnBack = (Button) rootView.findViewById(R.id.btn_backFromHistory);
		mBtnExit = (Button) rootView.findViewById(R.id.btn_exitFromHistory);
		mBtnStartDate = (Button) rootView.findViewById(R.id.btn_startingDate);
		mBtnEndDate = (Button) rootView.findViewById(R.id.btn_endingDate);
		mBtnShowHistory = (Button) rootView
				.findViewById(R.id.btn_showSpecificHistory);
		mBtnShowAllHistory = (Button) rootView
				.findViewById(R.id.btn_showAllHistory);
	}

	public void onClick() {
		mBtnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fManageExpence = new FragmentManageExpence();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fManageExpence);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack("FragmentExpenceHistory");
				ft.commit();
			}
		});
		mBtnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnStartDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnEndDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnShowHistory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnShowAllHistory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fViewExpenceHistory = new Fragment_ViewExpenceHistory();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fViewExpenceHistory);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
	}
}

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

public class FragmentAddExpence extends Fragment {
	private Button mBtnBack;
	private Button mBtnExit;
	private Button mBtnDateOfExpence;
	private Button mBtnSave;
	private Button mBtnCancel;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_addexpence, container,
				false);
		init(rootView);
		onClick();
		return rootView;
	}

	public void init(View rootView) {
		mBtnBack = (Button) rootView.findViewById(R.id.btn_backFromAddExpence);
		mBtnExit = (Button) rootView.findViewById(R.id.btn_exitFromAddExpence);
		mBtnDateOfExpence = (Button) rootView.findViewById(R.id.btn_setDate);
		mBtnSave = (Button) rootView.findViewById(R.id.btn_saveOnAddExpence);
		mBtnCancel = (Button) rootView
				.findViewById(R.id.btn_cancelOnAddExpence);
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
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		mBtnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnDateOfExpence.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}
}

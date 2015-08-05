package com.zaptech.myexpenditure2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Fragment_ViewExpenceHistory extends Fragment {
	private Button mBtnBack;
	private Button mBtnExit;

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
		mBtnBack = (Button) rootView.findViewById(R.id.btn_backFromViewHistory);
		mBtnExit = (Button) rootView.findViewById(R.id.btn_exitFromViewHistory);
	}

	public void onClick() {
		mBtnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fExpenceHistory = new FragmentExpenceHistory();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fExpenceHistory);

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

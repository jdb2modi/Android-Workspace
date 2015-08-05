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
import android.widget.Toast;

public class FragmentHome extends Fragment {
	private Button mBtn_manageExpence;
	private Button mBtn_manageBanking;
	private Button mBtn_settings;
	private Button mBtn_about;
	private Button mBtn_exit;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_home, container, false);
		init(rootView);
		onClick();
		return rootView;
	}

	public void init(View rootView) {
		mBtn_manageBanking = (Button) rootView
				.findViewById(R.id.btn_manageBanking);
		mBtn_manageExpence = (Button) rootView
				.findViewById(R.id.btn_manageExpence);
		mBtn_settings = (Button) rootView.findViewById(R.id.btn_Settings);
		mBtn_about = (Button) rootView.findViewById(R.id.btn_homeAbout);
		mBtn_exit = (Button) rootView.findViewById(R.id.btn_homeExit);
	}

	public void onClick() {
		mBtn_manageExpence.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fManageExpence = new FragmentManageExpence();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fManageExpence);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack("FragmentHome");
				ft.commit();

			}
		});
		mBtn_manageBanking.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fManageBanking = new FragmentManageBanking();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fManageBanking);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();

			}
		});
		mBtn_settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fSettings = new FragmentSettings();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fSettings);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();

			}
		});
		mBtn_about.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fabout = new FragmentAbout();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fabout);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		mBtn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}
}

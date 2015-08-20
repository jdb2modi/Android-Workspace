package com.zaptech.myexpenditure2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.zaptech.myexpenditure2.Activity_Home;
import com.zaptech.myexpenditure2.R;

public class FragmentHome extends Fragment implements OnKeyListener {
	private Button mBtn_manageExpence;
	private Button mBtn_manageBanking;
	private Button mBtn_settings;

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
		Toast.makeText(getActivity(), "Welcome", Toast.LENGTH_SHORT).show();
	}

	public void onClick() {
		mBtn_manageExpence.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Activity_Home) getActivity()).displayView(1);
			}
		});
		mBtn_manageBanking.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Activity_Home) getActivity()).displayView(2);
			}
		});
		mBtn_settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Activity_Home) getActivity()).displayView(3);
				/*
				 * Fragment fSettings = new FragmentSettings();
				 * FragmentTransaction ft = getFragmentManager()
				 * .beginTransaction();
				 * 
				 * ft.setCustomAnimations(R.anim.in_from_right_activity,
				 * R.anim.out_to_left_activity);
				 * 
				 * ft.replace(R.id.main, fSettings);
				 * ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				 * ft.addToBackStack(null); ft.commit();
				 */

			}
		});

	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			getActivity().finish();

		}
		return false;
	}

	public void acccess() {
		Toast.makeText(getActivity(), "Called", Toast.LENGTH_SHORT).show();
	}
}

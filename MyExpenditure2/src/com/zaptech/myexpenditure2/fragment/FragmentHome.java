package com.zaptech.myexpenditure2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zaptech.myexpenditure2.R;

public class FragmentHome extends Fragment {
	/*
	 * private Button mBtn_manageExpence; private Button mBtn_manageBanking;
	 * private Button mBtn_settings;
	 */
	private FragmentTabHost mTabHost;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		/*
		 * View rootView; rootView = inflater.inflate(R.layout.fragment_home,
		 * container, false); init(rootView); onClick();
		 * 
		 * return rootView;
		 */
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);

		mTabHost = (FragmentTabHost) rootView
				.findViewById(android.R.id.tabhost);
		mTabHost.setup(getActivity(), getChildFragmentManager(),
				R.id.hometabcontent);

		mTabHost.addTab(
				mTabHost.newTabSpec("fragmentb").setIndicator("Expenses"),
				FragmentManageExpence.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("fragmentc")
				.setIndicator("Banking"), FragmentManageBanking.class, null);
		mTabHost.addTab(
				mTabHost.newTabSpec("fragmentd").setIndicator("Settings"),
				FragmentSettings.class, null);

		return rootView;
	}

	public void init(View rootView) {
		/*
		 * mBtn_manageBanking = (Button) rootView
		 * .findViewById(R.id.btn_manageBanking); mBtn_manageExpence = (Button)
		 * rootView .findViewById(R.id.btn_manageExpence); mBtn_settings =
		 * (Button) rootView.findViewById(R.id.btn_Settings);
		 */

	}

	/*
	 * public void onClick() { mBtn_manageExpence.setOnClickListener(new
	 * OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { ((Activity_Home)
	 * getActivity()).displayView(1); } });
	 * mBtn_manageBanking.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { ((Activity_Home)
	 * getActivity()).displayView(2); } }); mBtn_settings.setOnClickListener(new
	 * OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { ((Activity_Home)
	 * getActivity()).displayView(3);
	 * 
	 * Fragment fSettings = new FragmentSettings(); FragmentTransaction ft =
	 * getFragmentManager() .beginTransaction();
	 * 
	 * ft.setCustomAnimations(R.anim.in_from_right_activity,
	 * R.anim.out_to_left_activity);
	 * 
	 * ft.replace(R.id.main, fSettings);
	 * ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
	 * ft.addToBackStack(null); ft.commit();
	 * 
	 * 
	 * } });
	 */

}

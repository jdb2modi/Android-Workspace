package com.zaptech.tabfragment;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub

	}

	@Override
	public Fragment getItem(int arg0) {

		switch (arg0) {
		case 0:
			// Top Rated fragment activity
			return new FirstActivity();
		case 1:
			// Games fragment activity
			return new SecondActivity();
		case 2:
			// Movies fragment activity
			return new ThirdActivity();
		}

		return null;
	}

	@Override
	public int getCount() {

		return 3;
	}

}

package com.zaptech.viewpagerlatest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

	public MyPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:

			return new GamesFragment();

		case 1:

			return new MoviesFragment();

		case 2:

			return new TopRatedFragment();

		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {

		return 3;
	}

}

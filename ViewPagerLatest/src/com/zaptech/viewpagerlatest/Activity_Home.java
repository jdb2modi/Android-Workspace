package com.zaptech.viewpagerlatest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class Activity_Home extends FragmentActivity {
	ViewPager pager;
	PagerAdapter pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		pd = new MyPagerAdapter(getSupportFragmentManager());
		init();
		pager.setAdapter(pd);

	}

	public void init() {
		pager = (ViewPager) findViewById(R.id.pager);

	}
}

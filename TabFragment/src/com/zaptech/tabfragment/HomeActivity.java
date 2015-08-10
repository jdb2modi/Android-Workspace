package com.zaptech.tabfragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class HomeActivity extends FragmentActivity implements
		ActionBar.TabListener {

	ActionBar.Tab firstTab, secondTab, thirdTab;

	Fragment firstFragmentTab = new FirstActivity();
	Fragment secondFragmentTab = new SecondActivity();
	Fragment thirdFragmentTab = new ThirdActivity();

	public static GoogleAnalytics analytics;
	public static Tracker tracker;

	private EasyTracker easyTracker = null;

	TabsPagerAdapter obj_pagerAdapter;

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Top Rated", "Games", "Movies" };

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_home);

		final ActionBar actionBar = getActionBar();
		//
		// if (android.os.Build.VERSION.SDK_INT < 11) {
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// }
		actionBar.setHomeButtonEnabled(false);
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//
		// firstTab = actionBar.newTab().setIcon(R.drawable.ic_launcher)
		// .setText("First Tab");
		// secondTab = actionBar.newTab().setIcon(R.drawable.ic_launcher)
		// .setText("Second Tab");
		// thirdTab = actionBar.newTab().setIcon(R.drawable.ic_launcher)
		// .setText("Third Tab");
		//
		// /*
		// * firstTab.setTabListener(new TabListener(firstFragmentTab));
		// * secondTab.setTabListener(new TabListener(secondFragmentTab));
		// * thirdTab.setTabListener(new TabListener(thirdFragmentTab));
		// */
		//
		// actionBar.addTab(firstTab);
		// actionBar.addTab(secondTab);
		// actionBar.addTab(thirdTab);
		//
		// viewPager = (ViewPager) findViewById(R.id.pager);
		// List<Fragment> fragments = getFragments();
		//
		// obj_pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(),
		// fragments);
		//
		// ViewPager pager = (ViewPager) findViewById(R.id.pager);
		//
		// pager.setAdapter(obj_pagerAdapter);

		analytics = GoogleAnalytics.getInstance(this);
		analytics.setLocalDispatchPeriod(1800);

		tracker = analytics.newTracker("UA-66147109-2"); // Replace with actual
															// tracker/property
															// Id

		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);

		tracker.setScreenName("main screen");

		tracker.send(new HitBuilders.EventBuilder().setCategory("UX")
				.setAction("click").setLabel("submit").build());

		viewPager = (ViewPager) findViewById(R.id.pager);
		// actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		// actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
				easyTracker = EasyTracker.getInstance();
				// easyTracker.send(MapView.createEvent("your_action",
				// "envet_name", "button_name/id", null).build());

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	private List<Fragment> getFragments() {

		List<Fragment> fList = new ArrayList<Fragment>();

		fList.add(firstFragmentTab);

		fList.add(secondFragmentTab);

		fList.add(thirdFragmentTab);

		return fList;

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	protected void onStart() {

		super.onStart();
		EasyTracker.getInstance().activityStart(this);

	}

	@Override
	protected void onStop() {

		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}
}

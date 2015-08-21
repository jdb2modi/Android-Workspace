package com.zaptech.myexpenditure2;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zaptech.myexpenditure2.adapter.NavDrawerListAdapter;
import com.zaptech.myexpenditure2.fragment.FragmentHome;
import com.zaptech.myexpenditure2.fragment.FragmentManageBanking;
import com.zaptech.myexpenditure2.fragment.FragmentManageExpence;
import com.zaptech.myexpenditure2.fragment.FragmentSettings;
import com.zaptech.myexpenditure2.model.NavDrawerItem;

public class Activity_Home extends FragmentActivity {

	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";

	int temp = 99;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	int flag = 0;
	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		sp = getSharedPreferences(MyPREFERENCES, Context.MODE_APPEND);
		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
				.getResourceId(0, -1)));
		// Manage Expences
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));
		// Manage Banking
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1)));
		// Settings
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1), true, "22"));
		// Exit
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));
		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);

		}

	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
		case R.id.settings:
			displayView(3);
			return true;
		case R.id.home:
			displayView(0);
			return true;
		case R.id.expences:
			displayView(1);
			return true;
		case R.id.bank:
			displayView(2);
			return true;
		case R.id.exit:
			// displayView(3);
			exitNow();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */

	public void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;

		switch (position) {
		case 0:

			fragment = new FragmentHome();
			getFragmentManager().popBackStack(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			flag = 1;
			temp = 0;
			break;
		case 1:
			fragment = new FragmentManageExpence();
			temp = 1;
			break;

		case 2:
			fragment = new FragmentManageBanking();
			temp = 2;
			break;
		case 3:
			fragment = new FragmentSettings();
			temp = 3;
			break;
		case 4:
			/*
			 * fragment = new FragmentSettings(); temp = 4;
			 */
			exitNow();
			break;
		default:
			break;
		}

		if (flag == 1) {

			FragmentManager fragmentManager1 = getSupportFragmentManager(); //
			// To Make the BackStackes null
			for (int i = fragmentManager1.getBackStackEntryCount(); i >= 0; i--) {
				fragmentManager1.popBackStack();

			} // Load or sets the HomeFragment after clearing all the backstacks
			fragmentManager1.beginTransaction().replace(R.id.main, fragment)
					.commit();// addToBackStack
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
			flag = 0;

		}

		else if (fragment != null) {
			FragmentManager fragmentManager1 = getSupportFragmentManager();
			fragmentManager1.beginTransaction().replace(R.id.main, fragment)
					.addToBackStack(null).commit();

			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {

			Log.e("MainActivity", "Error in creating fragment");
		}

	}

	/*
	 * @Override public void onBackPressed() { if (temp == 0) {
	 * 
	 * FragmentManager fragmentManager1 = getSupportFragmentManager(); for (int
	 * i = fragmentManager1.getBackStackEntryCount(); i >= 0; i--) {
	 * fragmentManager1.popBackStack();
	 * 
	 * }
	 * 
	 * this.finish(); }
	 * 
	 * super.onBackPressed(); }
	 */
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void exitNow() {
		Toast.makeText(getApplicationContext(), "Exiting...",
				Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alert = new AlertDialog.Builder(Activity_Home.this);
		alert.setTitle("Exit Confirmation");
		alert.setMessage("Want to Exit ?");
		alert.setCancelable(false);
		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();

			}
		});
		alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		alert.show();
	}
}

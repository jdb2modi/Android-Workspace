package com.zaptech.myexpenditure2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class Activity_Home extends FragmentActivity {
	final String[] data = { "Home", "Manage Expences", "Manage Banking",
			"Settings" };
	final String[] fragments = {
			"com.zaptech.myexpenditure2.fragment.FragmentHome",
			"com.zaptech.myexpenditure2.fragment.FragmentManageExpence",
			"com.zaptech.myexpenditure2.fragment.FragmentManageBanking",
			"com.zaptech.myexpenditure2.fragment.FragmentSettings" };
	DrawerLayout drawer;
	ListView navList;
	ImageView imgBtnList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

		imgBtnList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

				if (drawer.isDrawerOpen(navList)) {
					Animation animation1 = AnimationUtils.loadAnimation(
							getApplicationContext(), R.anim.clockwise);

					imgBtnList.startAnimation(animation1);

					drawer.closeDrawer(navList);
					imgBtnList.setBackgroundResource(R.drawable.ic_list);

				} else {
					Animation animation2 = AnimationUtils.loadAnimation(
							getApplicationContext(), R.anim.clockwise);
					imgBtnList.startAnimation(animation2);
					drawer.openDrawer(navList);
					imgBtnList.setBackgroundResource(R.drawable.img_back);
				}

			}
		});
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				Activity_Home.this, android.R.layout.simple_list_item_1, data);

		navList.setAdapter(adapter);
		navList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int pos, long id) {
				drawer.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
					@Override
					public void onDrawerClosed(View drawerView) {
						super.onDrawerClosed(drawerView);
						FragmentTransaction tx = getSupportFragmentManager()
								.beginTransaction();
						tx.replace(R.id.main, Fragment.instantiate(
								Activity_Home.this, fragments[pos]));
						tx.commit();
					}
				});
				drawer.closeDrawer(navList);
			}
		});
		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		tx.replace(R.id.main,
				Fragment.instantiate(Activity_Home.this, fragments[0]));
		tx.commit();
	}

	public void init() {
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		navList = (ListView) findViewById(R.id.drawer);
		imgBtnList = (ImageView) findViewById(R.id.imgBtn_list);
	}

}

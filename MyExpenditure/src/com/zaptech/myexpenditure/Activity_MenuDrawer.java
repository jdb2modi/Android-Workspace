package com.zaptech.myexpenditure;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class Activity_MenuDrawer extends Activity implements OnClickListener {
	DrawerLayout mydrawer;// This is that
	ListView lv_drawer;
	ImageView imgBtnList;
	ArrayList<String> list_MenuItems;
	ArrayAdapter<String> adptMenuItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menudrawer);
		init();
		list_MenuItems.add("Home");
		list_MenuItems.add("Manage Expences");
		list_MenuItems.add("Manage Banking");
		list_MenuItems.add("Settings");
		lv_drawer.setAdapter(adptMenuItems);
		lv_drawer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// String strSelected = lv_drawer.getSelectedItem().toString()
				// .trim();
				String strSelected = list_MenuItems.get(position);
				System.err
						.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>strSelectred : "
								+ strSelected);
				if (strSelected.equals("Manage Expences")) {
					finish();
					Intent intent = new Intent(Activity_MenuDrawer.this,
							Activity_ManageExpence.class);
					startActivity(intent);
				} else if (strSelected.equals("Manage Banking")) {
					finish();
					Intent intent = new Intent(Activity_MenuDrawer.this,
							Activity_ManageBanking.class);
					startActivity(intent);
				} else if (strSelected.equals("Home")) {
					finish();
					Intent intent = new Intent(Activity_MenuDrawer.this,
							Activity_Home.class);
					startActivity(intent);
				} else {
					finish();
					Intent intent = new Intent(Activity_MenuDrawer.this,
							Activity_Settings.class);
					startActivity(intent);
				}

			}

		});
	}

	public void init() {
		lv_drawer = (ListView) findViewById(R.id.listviewMenuItem);// This is
																	// that
		mydrawer = (DrawerLayout) findViewById(R.id.drawer_layout);// This is
																	// that
		imgBtnList = (ImageView) findViewById(R.id.imgBtn_list);// This is that
		imgBtnList.setOnClickListener(this);// This is that

		list_MenuItems = new ArrayList<String>();

		adptMenuItems = new ArrayAdapter<String>(Activity_MenuDrawer.this,
				android.R.layout.simple_dropdown_item_1line, list_MenuItems);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBtn_list:// ////////THIS IS THAT
			mydrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

			if (mydrawer.isDrawerOpen(lv_drawer)) {
				Animation animation1 = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.clockwise);

				imgBtnList.startAnimation(animation1);

				mydrawer.closeDrawer(lv_drawer);
				imgBtnList.setBackgroundResource(R.drawable.ic_list);

			} else {
				Animation animation2 = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.clockwise);
				imgBtnList.startAnimation(animation2);
				mydrawer.openDrawer(lv_drawer);
				imgBtnList.setBackgroundResource(R.drawable.img_back);
			}
		}

	}

}

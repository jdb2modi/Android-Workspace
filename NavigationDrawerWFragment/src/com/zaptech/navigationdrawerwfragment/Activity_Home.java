package com.zaptech.navigationdrawerwfragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Activity_Home extends Activity {
	ListView mListView;
	DrawerLayout drawer;
	final String data[] = { "one", "two", "three" };
	final String Fragments[] = {
			"com.zaptech.navigationdrawerwfragment.Fragment1",
			"com.zaptech.navigationdrawerwfragment.Fragment2",
			"com.zaptech.navigationdrawerwfragment.Fragment3" };
	ArrayAdapter<String> adpt = new ArrayAdapter<String>(
Activity_Home.this, android.R.layout.simple_list_item_1, data);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		mListView.setAdapter(adpt);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				drawer.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
					@Override
					public void onDrawerClosed(View drawerView) {
						 // FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
						super.onDrawerClosed(drawerView);
					}
				});
				
			}
		});
	}

	

	public void init() {
		mListView = (ListView) findViewById(R.id.listview_COntentList);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
	}
}

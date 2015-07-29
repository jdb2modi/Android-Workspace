package com.zaptech.navigationdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView drawer;
	DrawerLayout mydrawer;
	String[] colorlist;
	View v;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawer = (ListView) findViewById(R.id.left_drawer);
		mydrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		colorlist = getResources().getStringArray(R.array.colorList);
		ArrayAdapter<String> adpt = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, colorlist);
		drawer.setAdapter(adpt);
		drawer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				v = view;
				if (drawer.getItemAtPosition(position).toString()
						.equalsIgnoreCase("Red")) {
					view.setBackgroundColor(Color.RED);
				}
				if (drawer.getItemAtPosition(position).toString()
						.equalsIgnoreCase("Green")) {
					view.setBackgroundColor(Color.GREEN);
				}
				if (drawer.getItemAtPosition(position).toString()
						.equalsIgnoreCase("Blue")) {
					view.setBackgroundColor(Color.BLUE);
				}
				if (drawer.getItemAtPosition(position).toString()
						.equalsIgnoreCase("Yellow")) {
					view.setBackgroundColor(Color.YELLOW);
				}
				if (drawer.getItemAtPosition(position).toString()
						.equalsIgnoreCase("Pink")) {
					view.setBackgroundColor(Color.parseColor("#FFC0CB"));
				}

			}
		});
	}
}

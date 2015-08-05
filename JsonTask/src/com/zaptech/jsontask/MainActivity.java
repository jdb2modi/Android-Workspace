package com.zaptech.jsontask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

@SuppressLint("Recycle")
@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	@SuppressWarnings("unused")
	private CharSequence mDrawerTitle;

	private CharSequence mTitle;

	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private NavDrawerListAdapter adapter;

	ProgressDialog progress_Data;

	ModelMenuItem modelMenuItem;

	MyDatabaseHelper myData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		new getMenuKey().execute();

		navMenuTitles = getResources().getStringArray(R.array.listItems);

		navMenuIcons = getResources().obtainTypedArray(R.array.listIcons);

		ArrayList<NavDrawerItem> navDrawerItems = new ArrayList<NavDrawerItem>();

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
				.getResourceId(5, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons
				.getResourceId(6, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons
				.getResourceId(7, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons
				.getResourceId(8, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons
				.getResourceId(9, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[10], navMenuIcons
				.getResourceId(10, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[11], navMenuIcons
				.getResourceId(11, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[12], navMenuIcons
				.getResourceId(12, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[13], navMenuIcons
				.getResourceId(13, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[14], navMenuIcons
				.getResourceId(14, -1)));
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		navMenuIcons.recycle();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		

		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	public void init() {
		myData = new MyDatabaseHelper(MainActivity.this);

		try {
			myData.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		progress_Data = new ProgressDialog(MainActivity.this);

		modelMenuItem = new ModelMenuItem();
	}

	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void displayView(int position) {
		switch (position) {
		case 2:
			Intent news = new Intent(MainActivity.this, DisplayNewsItem.class);
			startActivity(news);
			break;

		case 8:
			Intent intent = new Intent(MainActivity.this, DisplayhomeItem.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

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

	class getMenuKey extends AsyncTask<Void, Void, Void> {

		String Str_Get = "";

		@Override
		protected void onPreExecute() {
			progress_Data.setTitle("Menu Items");
			progress_Data.setMessage("Loading.......");
			progress_Data.setCancelable(false);
			progress_Data.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			Str_Get = GET("http://80.93.28.24/json/autoexpress.json");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (progress_Data.isShowing()) {
				progress_Data.dismiss();
			}
			parseData(Str_Get);
			super.onPostExecute(result);
		}

	}

	public String GET(String string) {
		String str_Result = "";
		InputStream inputStream = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(new HttpGet(string));
			inputStream = httpResponse.getEntity().getContent();
			if (inputStream != null) {
				str_Result = convertInputStreamToString(inputStream);
			} else {
				str_Result = "String Not Found";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return str_Result;
	}

	public String convertInputStreamToString(InputStream inputStream) {
		String str_Line = "";
		String str_Result = "";
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(
				inputStream));
		try {
			while ((str_Line = bufferReader.readLine()) != null) {
				str_Result += str_Line;
				inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str_Result;
	}

	public void parseData(String str_Get) {
		try {
			JSONObject rootObj = new JSONObject(str_Get);
			Iterator<String> iterator = rootObj.keys();
			while (iterator.hasNext()) {
				String Str_Key = (String) iterator.next();
				modelMenuItem.setItemName(Str_Key);
				myData.insertMenuItem(modelMenuItem);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}

package com.zaptech.listviewgesture;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_Home extends Activity {

	ListView listView;
	String[] names = { "User 1", "User 2", "User 3", "User 4", "User 5",
			"User 6" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		listView = (ListView) findViewById(R.id.listView1);
		MyListAdapter adapter = new MyListAdapter(this, names);
		listView.setAdapter(adapter);

	}

}

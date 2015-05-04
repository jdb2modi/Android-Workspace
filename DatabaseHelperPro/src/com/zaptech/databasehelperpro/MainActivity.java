package com.zaptech.databasehelperpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	TextView tvAdd, tvUpdate, tvDelete, tvViewAll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		handleIntent();
	}

	public void init() {
		tvAdd = (TextView) findViewById(R.id.tvAdd);
		tvUpdate = (TextView) findViewById(R.id.tvUpdate);
		tvViewAll = (TextView) findViewById(R.id.tvViewAll);
		tvDelete = (TextView) findViewById(R.id.tvDelete);
		// Adding Listener
		tvAdd.setOnClickListener(this);
		tvUpdate.setOnClickListener(this);
		tvDelete.setOnClickListener(this);
		tvViewAll.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvAdd:
			Intent iAdd = new Intent(getApplicationContext(),
					AddUpdateAct.class);
			startActivity(iAdd);
			break;
		case R.id.tvUpdate:
			Intent iUpdate = new Intent(getApplicationContext(),
					AddUpdateAct.class);
			startActivity(iUpdate);
			break;
		case R.id.tvViewAll:

			break;
		case R.id.tvDelete:

			break;

		default:
			break;
		}

	}
	public void handleIntent(){
		Intent iFromBack=getIntent();
		
	}

}

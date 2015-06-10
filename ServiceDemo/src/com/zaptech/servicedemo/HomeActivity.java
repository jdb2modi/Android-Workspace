package com.zaptech.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	// Start the service
	public void startService(View view) {
		startService(new Intent(this, MyService.class));
	}

	// Stop the service
	public void stopService(View view) {
		stopService(new Intent(this, MyService.class));
	}

}
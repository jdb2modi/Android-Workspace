package com.zaptech.broadcastdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		broadcastIntent();
	}

	public void broadcastIntent() {
		Intent myIntent = new Intent();
		myIntent.setAction("JiNTENT");
		sendBroadcast(myIntent);
	}
}

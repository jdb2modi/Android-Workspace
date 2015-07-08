package com.zaptech.broadcastthroughservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Activity_Home extends Activity {
	IntentFilter filter;
	Intent intentService;
	public static String BROADCAST = "broadcast";
	MyBroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		intentService = new Intent(this, MyService.class);
		startService(intentService);
		receiver = new MyBroadcastReceiver();
		filter = new IntentFilter();
		filter.addAction(Activity_Home.BROADCAST);
		System.err.println(">>>>>>>>>>>>>>>>>>>>>INSIDE ONCREATE");
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onResume() {
		System.err.println(">>>>>>>>>>>>>>>>>>>>>INSIDE ONRESUME");
		this.registerReceiver(receiver, filter);

		super.onResume();
	}

	@Override
	protected void onPause() {
		System.err.println(">>>>>>>>>>>>>>>>>>>>>INSIDE ONPAUSE");
		unregisterReceiver(receiver);
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// unregisterReceiver(new MyBroadcastReceiver());
		super.onDestroy();
	}

	public class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			System.err.println(">>>>>>>>>>>>>>>>>>>>>CALLED");
			Toast.makeText(Activity_Home.this, "Received", Toast.LENGTH_LONG)
					.show();
		}

	}
}

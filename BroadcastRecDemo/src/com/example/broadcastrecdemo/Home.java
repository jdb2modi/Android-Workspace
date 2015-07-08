package com.example.broadcastrecdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class Home extends Activity {
	public static String sAction = "BROADCAST";
	IntentFilter filter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		System.err
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>InOnCreate1");
		Intent myinIntent = new Intent(this, MyService.class);
		startService(myinIntent);
		filter = new IntentFilter();
		filter.addAction(sAction);

	}

	@Override
	protected void onResume() {
		System.err
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>IN AN ON RESUME");
		registerReceiver(new MyBroadcastReceiver(), filter);
		super.onResume();
	}

	@Override
	protected void onPause() {
		System.err
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>IN AN ON PAUSE");
		unregisterReceiver(new MyBroadcastReceiver());
		super.onPause();
	}

	class MyBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(Home.this, "inside onReceive", Toast.LENGTH_LONG)
					.show();
			System.err
					.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>IN AN ONRECEIVE");
		}
	}
}

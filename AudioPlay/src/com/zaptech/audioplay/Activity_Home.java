package com.zaptech.audioplay;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Home extends Activity implements OnClickListener {
	Button btnPlay, btnPause;
	IntentFilter filter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		System.err
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>oncreate");
		init();
		registerReceiver(new MyReceiver(), filter);
	}

	public void init() {
		btnPause = (Button) findViewById(R.id.btnPause);
		btnPause.setOnClickListener(this);
		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnPlay.setOnClickListener(this);
		filter = new IntentFilter();
		filter.addAction("BRO");
	}

	@Override
	protected void onResume() {
		registerReceiver(new MyReceiver(), filter);
		super.onResume();
	}

	@Override
	protected void onPause() {
		unregisterReceiver(new MyReceiver());
		super.onPause();
	}

	public void playAudio() {
		Intent intent = new Intent(Activity_Home.this, MyService.class);
		startService(intent);
		System.err
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>PlayAudio");
	}

	public void pauseAudio() {
		Intent intent = new Intent(Activity_Home.this, MyService.class);
		stopService(intent);
		System.err
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>PauseAudio");
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnPause:
			pauseAudio();
			break;
		case R.id.btnPlay:
			playAudio();
			break;
		default:
			break;
		}

	}

	public class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			System.err.println(">>>>>>>>>>>>>>>>>>>>>CALLED");
			Toast.makeText(getApplicationContext(), "Stoped", 2500).show();

		}

	}
}

package com.zaptech.dataoperationpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LauncherActivity extends Activity {

	private final int SPLASH_DISPLAY_LENGTH = 1200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				/* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = new Intent(LauncherActivity.this,
						MainActivity.class);
				LauncherActivity.this.startActivity(mainIntent);
				LauncherActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}

}

package com.zaptech.moneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Activity_Welcome extends Activity {
	private final int SPLASH_DISPLAY_LENGTH = 1200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__welcome);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				/* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = new Intent(Activity_Welcome.this,
						HomeActivity.class);
				Activity_Welcome.this.startActivity(mainIntent);
				Activity_Welcome.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}

}

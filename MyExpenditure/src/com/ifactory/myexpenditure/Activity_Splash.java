package com.ifactory.myexpenditure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Activity_Splash extends Activity {
	private final int SPLASH_DISPLAY_LENGTH = 1200;
	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String PASSWORD = "password";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__splash);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				sp = getSharedPreferences(MyPREFERENCES, Context.MODE_APPEND);
				/* Create an Intent that will start the Menu-Activity. */
				if (sp.contains(PASSWORD)) {
					Intent mainIntent = new Intent(Activity_Splash.this,
							Activity_Home.class);
					Activity_Splash.this.startActivity(mainIntent);
					Activity_Splash.this.finish();
				}
				if (!sp.contains(PASSWORD)) {
					Intent mainIntent = new Intent(Activity_Splash.this,
							Activity_Login.class);
					Activity_Splash.this.startActivity(mainIntent);
					Activity_Splash.this.finish();
				}

			}
		}, SPLASH_DISPLAY_LENGTH);
	}

	
}

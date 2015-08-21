package com.zaptech.myexpenditure2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class Activity_Splash extends Activity {
	private final int SPLASH_DISPLAY_LENGTH = 5000;
	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				/* Create an Intent that will start the Menu-Activity. */
				sp = getSharedPreferences(MyPREFERENCES, Context.MODE_APPEND);
				Editor ed = sp.edit();
				if (sp.contains("ENABLED")) {
					Intent mainIntent = new Intent(Activity_Splash.this,
							Activity_Login.class);
					Activity_Splash.this.startActivity(mainIntent);
					Activity_Splash.this.finish();

				} else {
					Intent mainIntent = new Intent(Activity_Splash.this,
							Activity_Home.class);
					Activity_Splash.this.startActivity(mainIntent);
					Activity_Splash.this.finish();

				}

			}
		}, SPLASH_DISPLAY_LENGTH);
	}
}

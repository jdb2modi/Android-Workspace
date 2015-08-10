package com.zaptech.googleanalyticsexample;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.zaptech.googleanalyticsexample.GoogleAnalyticsAPP.TrackerName;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Tracker t = ((GoogleAnalyticsAPP) getApplication())
				.getTracker(TrackerName.APP_TRACKER);
		t.setScreenName("Home");
		t.send(new HitBuilders.AppViewBuilder().build());
		t.send(new HitBuilders.EventBuilder().setCategory("Button")
				.setAction("Click").setLabel("Cool Button").build());
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		GoogleAnalytics.getInstance(MainActivity.this)
				.reportActivityStart(this);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		GoogleAnalytics.getInstance(MainActivity.this).reportActivityStop(this);
	}
}

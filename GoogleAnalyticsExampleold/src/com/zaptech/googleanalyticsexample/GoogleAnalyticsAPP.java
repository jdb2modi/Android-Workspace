package com.zaptech.googleanalyticsexample;

import java.util.HashMap;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class GoogleAnalyticsAPP extends android.app.Application {
	public static GoogleAnalytics analytics;
	public static Tracker tracker;

	private static final String PROPERTY_ID = "UA-66147109-4";

	public static int GENERAL_TRACKER = 0;

	public enum TrackerName {
		APP_TRACKER, GLOBAL_TRACKER, ECOMMERCE_TRACKER,
	}

	public HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

	public GoogleAnalyticsAPP() {
		super();
	}

	@Override
	public void onCreate() {
		analytics = GoogleAnalytics.getInstance(GoogleAnalyticsAPP.this);
		analytics.setLocalDispatchPeriod(1800);

		tracker = analytics.newTracker("UA-66147109-4"); // Replace with actual
															// tracker/property
															// Id
		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);
	}

	/*
	 * public synchronized Tracker getTracker(TrackerName appTracker) { if
	 * (!mTrackers.containsKey(appTracker)) { GoogleAnalytics analytics =
	 * GoogleAnalytics.getInstance(this); Tracker t = (appTracker ==
	 * TrackerName.APP_TRACKER) ? analytics .newTracker(PROPERTY_ID) :
	 * (appTracker == TrackerName.GLOBAL_TRACKER) ? analytics
	 * .newTracker(R.xml.global_tracker) : analytics
	 * .newTracker(R.xml.ecommerce); mTrackers.put(appTracker, t); } // return
	 * mTrackers.get(appTracker); return (Tracker) mTrackers.get(appTracker); }
	 */
	public synchronized Tracker getTracker(TrackerName appTracker) {
		if (!mTrackers.containsKey(appTracker)) {
			GoogleAnalytics googleAnalytics = GoogleAnalytics
					.getInstance(GoogleAnalyticsAPP.this);
			Tracker tracker = (appTracker == TrackerName.APP_TRACKER) ? googleAnalytics
					.newTracker(PROPERTY_ID)
					: (appTracker == TrackerName.GLOBAL_TRACKER) ? googleAnalytics
							.newTracker(R.xml.global_tracker)
							: (googleAnalytics.newTracker(R.xml.ecommerce));
			mTrackers.put(appTracker, tracker);
		}
		return mTrackers.get(appTracker);
	}
}
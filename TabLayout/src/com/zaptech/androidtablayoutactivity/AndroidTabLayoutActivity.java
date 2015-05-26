package com.zaptech.androidtablayoutactivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabLayoutActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_tab_layout);

		TabHost tabHost = getTabHost();

		TabSpec photospec = tabHost.newTabSpec("Photos");

		photospec.setIndicator("",
				getResources().getDrawable(R.drawable.photoes));
		Intent photosIntent = new Intent(this, PhotoesActivity.class);
		photospec.setContent(photosIntent);

		TabSpec songspec = tabHost.newTabSpec("Songs");
		songspec.setIndicator("Songs",
				getResources().getDrawable(R.drawable.songs));
		Intent songsIntent = new Intent(this, SongsActivity.class);
		songspec.setContent(songsIntent);

		TabSpec videospec = tabHost.newTabSpec("Videos");
		videospec.setIndicator("Videos",
				getResources().getDrawable(R.drawable.videos));
		Intent videosIntent = new Intent(this, VideosActivity.class);
		videospec.setContent(videosIntent);

		tabHost.addTab(photospec); // Adding photos tab
		tabHost.addTab(songspec); // Adding songs tab
		tabHost.addTab(videospec); // Adding videos tab
		tabHost.addTab(photospec); // Adding photos tab
		tabHost.addTab(songspec); // Adding songs tab
		tabHost.addTab(videospec);
		tabHost.addTab(photospec); // Adding photos tab
		tabHost.addTab(songspec); // Adding songs tab
		tabHost.addTab(videospec);
		tabHost.addTab(songspec); // Adding songs tab
		tabHost.addTab(videospec);
		tabHost.addTab(photospec); // Adding photos tab
		tabHost.addTab(songspec); // Adding songs tab
		tabHost.addTab(videospec);

	}
}
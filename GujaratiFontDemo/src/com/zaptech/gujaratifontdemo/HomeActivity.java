package com.zaptech.gujaratifontdemo;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		TextView mtxt = (TextView) findViewById(R.id.tv1);

		Typeface face1 = Typeface.createFromAsset(getAssets(),

		"Lohit-Gujarati.ttf");

		mtxt.setTypeface(face1);

		
	}
}

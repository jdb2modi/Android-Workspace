package com.example.quizapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class WeeklyScores extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weekly_scores);
	}
	/*
	 * public void OnClickHandler(View v) { switch (v.getId()) { case
	 * R.id.aboutus_btn_back: Intent i_back = new Intent(AboutUs.this,
	 * AccountSetting.class); startActivity(i_back); break;
	 * 
	 * } }
	 */
}
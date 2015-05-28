package com.zaptech.taskralativelayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ActivityFive extends Activity {
	ImageView imgExit;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_five);
		imgExit = (ImageView) findViewById(R.id.img3);
		imgExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}
}

package com.zaptech.taskralativelayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ActivityTwo extends Activity implements OnClickListener {
	ImageButton imgBtnHome, imgBtnNext, imgBtnPrevious, imgBtnExit;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		init();
	}

	public void init() {
		imgBtnHome = (ImageButton) findViewById(R.id.imageButtonHomeOnActivity2);
		imgBtnNext = (ImageButton) findViewById(R.id.imageButtonNextOnActivity2);
		imgBtnPrevious = (ImageButton) findViewById(R.id.imageButtonPreviousOnActivity2);
		imgBtnExit = (ImageButton) findViewById(R.id.imageButtonExitOnActivity2);
		// Adding Listeners
		imgBtnHome.setOnClickListener(this);
		imgBtnNext.setOnClickListener(this);
		imgBtnPrevious.setOnClickListener(this);
		imgBtnExit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButtonHomeOnActivity2:
			intent = new Intent(ActivityTwo.this, HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButtonNextOnActivity2:
			intent = new Intent(ActivityTwo.this, ActivityThree.class);
			startActivity(intent);
			break;
		case R.id.imageButtonPreviousOnActivity2:
			intent = new Intent(ActivityTwo.this, HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButtonExitOnActivity2:
			this.finish();
			break;
		default:
			break;
		}

	}
}

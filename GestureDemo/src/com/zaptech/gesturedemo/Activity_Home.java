package com.zaptech.gesturedemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class Activity_Home extends Activity {

	final String DEBUG_TAG = "ACTION DETECTED";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = MotionEventCompat.getActionMasked(event);

		switch (action) {
		case (MotionEvent.ACTION_DOWN):
			Log.d(DEBUG_TAG, "Action was DOWN");
			Toast.makeText(Activity_Home.this, "Action was DOWN",
					Toast.LENGTH_SHORT).show();
			return true;
		case (MotionEvent.ACTION_MOVE):
			Toast.makeText(Activity_Home.this, "Action was MOVE",
					Toast.LENGTH_SHORT).show();
			Log.d(DEBUG_TAG, "Action was MOVE");
			return true;
		case (MotionEvent.ACTION_UP):
			Toast.makeText(Activity_Home.this, "Action was UP",
					Toast.LENGTH_SHORT).show();
			Log.d(DEBUG_TAG, "Action was UP");
			return true;
		case (MotionEvent.ACTION_CANCEL):
			Toast.makeText(Activity_Home.this, "Action was CANCEL",
					Toast.LENGTH_SHORT).show();
			Log.d(DEBUG_TAG, "Action was CANCEL");
			return true;
		case (MotionEvent.ACTION_OUTSIDE):
			Toast.makeText(Activity_Home.this, "Action was OUTSIDE",
					Toast.LENGTH_SHORT).show();
			Log.d(DEBUG_TAG, "Movement occurred outside bounds "
					+ "of current screen element");
			return true;
		}
		return super.onTouchEvent(event);
	}
}

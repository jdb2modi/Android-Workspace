package com.zaptech.viewflipperdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	ViewFlipper vf;
	float lastX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	public void init() {
		vf = (ViewFlipper) findViewById(R.id.vf1);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			lastX = event.getX();

			break;

		case MotionEvent.ACTION_UP:

			float currentX = event.getX();
			if (lastX > currentX) {
				vf.setInAnimation(this, R.anim.fromright);
				vf.setOutAnimation(this, R.anim.outtoleft);
				vf.showNext();
			}
			if (lastX < currentX) {

				vf.setInAnimation(this, R.anim.fromleft);
				vf.setOutAnimation(this, R.anim.outtoright);
				vf.showPrevious();
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
}

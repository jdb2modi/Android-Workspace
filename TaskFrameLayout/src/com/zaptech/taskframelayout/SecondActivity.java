package com.zaptech.taskframelayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class SecondActivity extends Activity implements OnClickListener {
	ImageView imgGanesha, img2, img3, img4;
	Animation rotateAnimation;
	Button buttonHome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		init();
		rotateAnimation = AnimationUtils.loadAnimation(SecondActivity.this,
				R.anim.rotateanimation);
		img2.startAnimation(rotateAnimation);
		buttonHome = (Button) findViewById(R.id.btnHomeFromSecond);
		buttonHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(),
						HomeActivity.class);
				startActivity(intent);

			}
		});
		// imgGanesha.startAnimation(rotateAnimation);
	}

	public void init() {
		imgGanesha = (ImageView) findViewById(R.id.img1OnSecond);
		img2 = (ImageView) findViewById(R.id.img2OnSecond);
		img3 = (ImageView) findViewById(R.id.img3OnSecond);
		img4 = (ImageView) findViewById(R.id.img4OnSecond);
		imgGanesha.setOnClickListener(this);
		img2.setOnClickListener(this);
		img3.setOnClickListener(this);
		img4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img1OnSecond:

			break;

		default:
			break;
		}

	}
}

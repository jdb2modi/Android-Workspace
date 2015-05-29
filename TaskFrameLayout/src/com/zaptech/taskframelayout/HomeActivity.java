package com.zaptech.taskframelayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends Activity implements OnClickListener {
	ImageView img1, img2, img3, img4, img5;
	Intent intent;
	Animation rotateAnim;
	Button btnExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ShapeDrawable sp = new ShapeDrawable();
		init();

	}

	public void init() {
		img1 = (ImageView) findViewById(R.id.img1OnHome);
		img2 = (ImageView) findViewById(R.id.img2OnHome);
		img3 = (ImageView) findViewById(R.id.img3OnHome);
		img4 = (ImageView) findViewById(R.id.img4OnHome);
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
		img3.setOnClickListener(this);
		img4.setOnClickListener(this);
		btnExit = (Button) findViewById(R.id.btnExit);
		btnExit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img1OnHome:
			finish();
			intent = new Intent(HomeActivity.this, HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.img2OnHome:
			finish();
			intent = new Intent(HomeActivity.this, SecondActivity.class);
			startActivity(intent);
			break;

		case R.id.img3OnHome:
			finish();
			intent = new Intent(HomeActivity.this, ThirdActivity.class);
			startActivity(intent);
			break;

		case R.id.img4OnHome:
			finish();
			intent = new Intent(HomeActivity.this, FourthActivity.class);
			startActivity(intent);
			break;
		case R.id.btnExit:
			finish();
			intent = new Intent(HomeActivity.this, FifthActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}

package com.zaptech.taskralativelayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends Activity implements OnClickListener {
	private Button btnPattern1, btnPattern2, btnPattern3, btnPattern4;
	private ImageView imgContent, imgExit;
	private int flag = 0;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	public void init() {
		btnPattern1 = (Button) findViewById(R.id.buttonOneOnHome);
		btnPattern2 = (Button) findViewById(R.id.buttonTwoOnHome);
		btnPattern3 = (Button) findViewById(R.id.buttonThreeOnHome);
		btnPattern4 = (Button) findViewById(R.id.buttonFourOnHome);
		btnPattern1.setOnClickListener(this);
		btnPattern2.setOnClickListener(this);
		btnPattern3.setOnClickListener(this);
		btnPattern4.setOnClickListener(this);
		imgContent = (ImageView) findViewById(R.id.img1);
		imgContent.setOnClickListener(this);
		imgExit = (ImageView) findViewById(R.id.img5);
		imgExit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonOneOnHome:
			flag = 1;
			imgContent.setBackgroundResource(R.drawable.one);

			break;
		case R.id.buttonTwoOnHome:
			flag = 2;
			imgContent.setBackgroundResource(R.drawable.two);

			break;
		case R.id.buttonThreeOnHome:
			flag = 3;
			imgContent.setBackgroundResource(R.drawable.three);

			break;
		case R.id.buttonFourOnHome:
			flag = 4;
			imgContent.setBackgroundResource(R.drawable.four);

			break;
		case R.id.img5:

			intent = new Intent(HomeActivity.this, ActivityFive.class);
			startActivity(intent);

			break;
		case R.id.img1:
			switch (flag) {
			case 1:
				intent = new Intent(HomeActivity.this, HomeActivity.class);
				startActivity(intent);
				break;
			case 2:
				intent = new Intent(HomeActivity.this, ActivityTwo.class);
				startActivity(intent);
				break;
			case 3:
				intent = new Intent(HomeActivity.this, ActivityThree.class);
				startActivity(intent);
				break;
			case 4:
				intent = new Intent(HomeActivity.this, ActivityFour.class);
				startActivity(intent);
				break;
			default:
				break;
			}
			break;
		}

	}
}

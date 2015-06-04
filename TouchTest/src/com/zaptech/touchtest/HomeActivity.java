package com.zaptech.touchtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends Activity implements OnClickListener {
	Button btn1, btn2, btn3,btn4;
	int flag1 = 0, flag2 = 0, flag3 = 0,flag4=0;
	ImageView img1to2, img2to3,img3to4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	public void init() {
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		img1to2 = (ImageView) findViewById(R.id.img1to2);
		img2to3 = (ImageView) findViewById(R.id.img2to3);
		img3to4 = (ImageView) findViewById(R.id.img3to4);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			flag1 = 1;
			if (flag1 == 1 && flag2 == 1 || flag2 == 1 && flag1 == 1) {
				img1to2.setVisibility(1);
			}
			break;
		case R.id.btn2:
			flag2 = 1;
			if (flag1 == 1 && flag2 == 1) {
				img1to2.setVisibility(1);
			}
			break;
		case R.id.btn3:
			flag3 = 1;
			if (flag2 == 1 && flag3 == 1) {
				img2to3.setVisibility(1);
			}
			break;
		case R.id.btn4:
			flag4 = 1;
			if (flag1 == 1 && flag4 == 1) {
				img3to4.setVisibility(1);
			}
			break;
		default:
			break;
		}
	}
}

package com.zaptech.mytoss;

import java.io.File;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity_Toss extends Activity implements OnClickListener {
	ImageView imgToss;
	Button btn_TossNow;
	Animation animRotate;
	int random;
	String strPath1, strPath2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toss);
		init();
		Intent intent = getIntent();
		strPath1 = intent.getStringExtra("Path1");
		strPath2 = intent.getStringExtra("Path2");

	}

	public void init() {
		imgToss = (ImageView) findViewById(R.id.img_Toss);
		btn_TossNow = (Button) findViewById(R.id.btn_TossNow);
		btn_TossNow.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_TossNow:

			animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotatetoss);
			imgToss.startAnimation(animRotate);
			new Thread(new Runnable() {

				@Override
				public void run() {
					// random = Integer.parseInt(String.valueOf(Math.random()));

					Random rno = new Random();
					random = rno.nextInt();
					System.err
							.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>jrandom+++++++++++"
									+ random);

				}
			}).start();
			animRotate.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					imgToss.setImageResource(R.drawable.ic_launcher);

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					Bitmap bitmap2, bitmap1;
					String str1;
					System.err.println(">>>>>>>>>END>>>>>>>>");
					if (random % 2 == 0) {

						File imageFile2 = new File(strPath1);

						bitmap2 = BitmapFactory.decodeFile(imageFile2
								.getAbsolutePath());
						imgToss.setImageBitmap(bitmap2);

						str1 = imageFile2.getAbsolutePath().toString();

					} else {
						File imageFile2 = new File(strPath2);

						bitmap2 = BitmapFactory.decodeFile(imageFile2
								.getAbsolutePath());
						imgToss.setImageBitmap(bitmap2);
						String str = String.valueOf(imgToss.getBackground());
						str1 = imageFile2.getAbsolutePath().toString();
					}
					if(str1.equals(strPath1)){
						Toast.makeText(getApplicationContext(), "TEAM 1 WON", 2500).show();
					}
					else{
						Toast.makeText(getApplicationContext(), "TEAM 2 WON", 2500).show();
					}
				}
			});

			break;

		default:
			break;
		}

	}
}

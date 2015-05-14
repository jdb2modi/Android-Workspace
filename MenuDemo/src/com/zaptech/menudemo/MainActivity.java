package com.zaptech.menudemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.Scale:
			ImageView image = (ImageView) findViewById(R.id.imageView1);
			Animation animation = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.scale);
			image.startAnimation(animation);
			return true;
		case R.id.rotate:
			ImageView image1 = (ImageView) findViewById(R.id.imageView1);
			Animation animation1 = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.rotate);
			image1.startAnimation(animation1);
			return true;
		case R.id.Alpha:
			ImageView image2 = (ImageView) findViewById(R.id.imageView1);
			Animation animation2 = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.alpha);
			image2.startAnimation(animation2);
			return true;
		case R.id.Translate:
			ImageView image3 = (ImageView) findViewById(R.id.imageView1);

			Animation animation3 = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.translate);
			image3.startAnimation(animation3);
			return true;
		}
		return false;
	}

}

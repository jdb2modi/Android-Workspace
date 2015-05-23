package com.zaptech.toastlong;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	CountDownTimer timer = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		TextView textView1 = new TextView(HomeActivity.this);
		textView1.setTextColor(Color.RED);
		textView1.setGravity(Gravity.CENTER_VERTICAL);
		textView1.setTextSize(20);
		textView1.setBackgroundColor(Color.BLUE);
		textView1.setText("I AM JAIMIN MODI");
		final Toast toast;
		toast = new Toast(HomeActivity.this);
		toast.setView(textView1);
		timer = new CountDownTimer(60000,2000) {

			@Override
			public void onTick(long millisUntilFinished) {
				toast.show();

			}

			@Override
			public void onFinish() {
				toast.cancel();

			}
		}.start();
	}
}

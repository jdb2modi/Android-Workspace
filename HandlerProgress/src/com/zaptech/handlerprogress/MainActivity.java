package com.zaptech.handlerprogress;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button btn1;
	ProgressBar pBar;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	public void init() {
		btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(this);
		handler = new Handler();
		pBar = (ProgressBar) findViewById(R.id.progressBar);
	}

	class Task implements Runnable {

		@Override
		public void run() {
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>INSIODE RUNNABLE");
			for (int i = 0; i <= 100; i++) {
				final int value = i;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Posting Background work to Handler...
				handler.post(new Runnable() {

					@Override
					public void run() {
						System.err
								.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>INSIODE HANDLER");
						pBar.setProgress(value);
						if (value == 100) {
							Toast.makeText(MainActivity.this,
									"Process Completed..!!", Toast.LENGTH_LONG)
									.show();
						}

					}
				});
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>BUTTONn CL icled");
			new Thread(new Task()).start();
			break;

		default:
			break;
		}

	}
}

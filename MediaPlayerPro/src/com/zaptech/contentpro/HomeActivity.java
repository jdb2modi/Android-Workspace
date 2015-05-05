package com.zaptech.contentpro;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	Button btnStart, btnStop, btnPause;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		btnPause = (Button) findViewById(R.id.buttonPause);
		btnStart = (Button) findViewById(R.id.buttonStart);
		btnStop = (Button) findViewById(R.id.buttonStop);

		btnPause.setOnClickListener(HomeActivity.this);
		btnStart.setOnClickListener(HomeActivity.this);
		btnStop.setOnClickListener(HomeActivity.this);

		try {
			mp = new MediaPlayer();
			mp.setDataSource("/sdcard/Music/5.mp3");
			mp.prepare();
			mp.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonPause:

			Toast.makeText(HomeActivity.this, "Paused", Toast.LENGTH_LONG)
					.show();
			mp.pause();

			break;
		case R.id.buttonStart:
			Toast.makeText(HomeActivity.this, "Playing", Toast.LENGTH_LONG)
					.show();
			mp.start();

			break;
		case R.id.buttonStop:

			Toast.makeText(HomeActivity.this, "Stoped", Toast.LENGTH_LONG)
					.show();
			mp.stop();

			break;

		default:
			break;
		}
	}
}
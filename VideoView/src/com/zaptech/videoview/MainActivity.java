package com.zaptech.videoview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class MainActivity extends Activity {
	VideoView videoView1;
	android.widget.MediaController mediaController;
	ProgressDialog pd;
	int position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		if (mediaController == null) {
			mediaController = new android.widget.MediaController(this);
		}
		try {
			videoView1.setMediaController(mediaController);
			videoView1.setVideoURI(Uri.parse("android.resource://"
					+ getPackageName() + "/" + R.raw.demo3));
		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
		videoView1.requestFocus();
		videoView1.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				pd.dismiss();
				videoView1.seekTo(position);
				if (position == 0) {
					videoView1.start();
				} else {
					videoView1.pause();
				}
			}
		});
	}

	public void init() {
		videoView1 = (VideoView) findViewById(R.id.videoView1);
		pd = new ProgressDialog(this);
		pd.setTitle("Loading");
		pd.setCancelable(false);
		pd.setMessage("Preparing to Play...");
		pd.show();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		outState.putInt("Position", videoView1.getCurrentPosition());
		videoView1.pause();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {

		super.onRestoreInstanceState(savedInstanceState);
		position = savedInstanceState.getInt("Position");
		videoView1.seekTo(position);
	}
}

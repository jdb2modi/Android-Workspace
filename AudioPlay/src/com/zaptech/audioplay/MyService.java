package com.zaptech.audioplay;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
	MediaPlayer mPlayer;

	@Override
	public void onCreate() {
		init();
		System.err
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SERVICE oncreate");
		super.onCreate();
	}

	public void init() {
		mPlayer = MediaPlayer.create(this, R.raw.sleepway);
		mPlayer.setLooping(false);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {

			@Override
			public void run() {

				mPlayer.start();

				System.err
						.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ONSTART COM Playing");

			}
		}).run();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		mPlayer.stop();
		if (!mPlayer.isPlaying()) {
			Intent intent2 = new Intent();
			intent2.setAction("BRO");
			sendBroadcast(intent2);
		}
		System.err
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>DEST royed");

		mPlayer.release();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

}

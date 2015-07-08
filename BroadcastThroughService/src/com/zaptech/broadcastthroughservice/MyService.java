package com.zaptech.broadcastthroughservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	boolean running = true;
	private static final String ACTION_PLAY = "com.example.action.PLAY";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		new Thread(new Runnable() {
			int i = 0;

			@Override
			public void run() {
				while (running) {
					Intent Myintent = new Intent();
					Myintent.setAction(Activity_Home.BROADCAST);
					Myintent.putExtra("COUNT", i++);
					sendBroadcast(Myintent);
					Log.i("bipin.........", ">>>>>>>>>>>>>" + i++);
				}

			}
		}).start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public void onDestroy() {
		running = false;
		super.onDestroy();
	}

}
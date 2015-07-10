package com.zaptech.broadcastthroughservice;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	boolean running = true;
	int i = 0;
	Timer timer = new Timer();
	TimerTask mTimerTask;
	private static final String ACTION_PLAY = "com.example.action.PLAY";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		task();
		return super.onStartCommand(intent, flags, startId);
	}

	public void task() {
		mTimerTask = new TimerTask() {

			@Override
			public void run() {
				/*
				 * new Thread(new Runnable() {
				 * 
				 * @Override public void run() {
				 */

				Intent Myintent = new Intent();
				Myintent.putExtra("COUNT", "modi");
				Myintent.putExtra("CC", i++);
				Myintent.setAction(Activity_Home.BROADCAST);

				sendBroadcast(Myintent);

			}
			// }).start();

			// }
		};
		timer.schedule(mTimerTask, 0, 5000);
	}

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		/*if (timer != null) {
			timer.cancel();
		}
		stopSelf();*/
	}

}
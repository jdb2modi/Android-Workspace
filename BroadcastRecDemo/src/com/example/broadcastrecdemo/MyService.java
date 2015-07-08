package com.example.broadcastrecdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	   

	@Override
	public void onCreate() {
		Log.i("ServiceonCreate", ">>>>>>>>>>>>>>>>>>>>>>" + true);
		super.onCreate();
	}

	@Override
	public int onStartCommand(final Intent intent, int flags, int startId) {
		Log.i("ServiceonStartCommand", ">>>>zaptech>>>>>>>>>>>>>>>>>>>>" + true);

		new Thread(new Runnable() {
			int k = 0;

			@Override
			public void run() {

				try {

					intent.setAction(Home.sAction);
					intent.putExtra("COUNTER", k++);
					sendBroadcast(intent);
					System.err
							.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SENT BROADCAST");

					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}

			}
		}).start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}
}

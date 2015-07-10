package com.zaptech.alarmmanager;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity_home extends Activity implements OnClickListener {
	Button btnStart, btnStop, btnat10;
	PendingIntent pendingIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initializeControls();
		// alarmTask();
		System.err.println("Inside oncreate...>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	public void initializeControls() {
		btnStart = (Button) findViewById(R.id.startAlarm);
		btnStart.setOnClickListener(this);
		btnStop = (Button) findViewById(R.id.stopAlarm);
		btnStop.setOnClickListener(this);
		btnat10 = (Button) findViewById(R.id.stopAlarmAt10);
		btnat10.setOnClickListener(this);
		System.err.println("Inside initializxe...>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	public void alarmTask() {
		System.err.println("Inside alarmTask...>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

	}

	public void start() {
		System.err.println("Inside start...>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		int interval = 1000;
		Intent alarmIntent = new Intent(Activity_home.this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(Activity_home.this, 0,
				alarmIntent, 0);
		manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
				System.currentTimeMillis(), interval, pendingIntent);
		Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();

	}

	public void stop() {

		AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent alarmIntent = new Intent(Activity_home.this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(Activity_home.this, 0,
				alarmIntent, 0);
		manager.cancel(pendingIntent);
		Toast.makeText(this, "Alarm Stopped/Canceled", Toast.LENGTH_SHORT)
				.show();
	}

	public void stopAt10() {

		AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		Intent alarmIntent = new Intent(Activity_home.this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(Activity_home.this, 0,
				alarmIntent, 0);

		Calendar time = Calendar.getInstance();
		time.set(Calendar.HOUR, 6);
		time.set(Calendar.MINUTE, 16);
		time.set(Calendar.SECOND, 0);

		/* Repeating on every 2 seconds interval */
		manager.setRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),
				2000, pendingIntent);
		// alarmManager.set(AlarmManager.RTC,time.getTimeInMillis(),pendingIntent);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.startAlarm:
			start();
			break;
		case R.id.stopAlarm:
			stop();
			break;
		case R.id.stopAlarmAt10:
			stopAt10();
			break;
		default:
			break;
		}

	}

}

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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class Activity_home extends Activity implements OnClickListener {
	private Button btnStart, btnStop, btnat10;
	private PendingIntent pendingIntent;
	int hour, min, sec;
	// For timePicker..
	private TimePicker timePicker1;
	private TextView txtTime;
	private String format = "";
	private Calendar calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initializeControls();

		// alarmTask();
		System.err.println("Inside oncreate...>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		timePicker1.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				setTime();
			}
		});
	}

	public void initializeControls() {
		btnStart = (Button) findViewById(R.id.startAlarm);
		btnStart.setOnClickListener(this);
		btnStop = (Button) findViewById(R.id.stopAlarm);
		btnStop.setOnClickListener(this);
		btnat10 = (Button) findViewById(R.id.stopAlarmAt10);
		btnat10.setOnClickListener(this);
		System.err.println("Inside initializxe...>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		txtTime = (TextView) findViewById(R.id.txtTime);
		// For Time-Picker
		timePicker1 = (TimePicker) findViewById(R.id.timepick1);
		txtTime = (TextView) findViewById(R.id.txtTime);
		calendar = Calendar.getInstance();

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
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, sec);

		/* Repeating on every 2 seconds interval */
		manager.setRepeating(AlarmManager.RTC_WAKEUP,
				calendar.getTimeInMillis(), 2000, pendingIntent);
		// alarmManager.set(AlarmManager.RTC,time.getTimeInMillis(),pendingIntent);

	}

	// /TimePicker
	public void setTime() {
		hour = timePicker1.getCurrentHour();
		min = timePicker1.getCurrentMinute();
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>setTime" + hour);
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>setTime" + min);
		showTime(hour, min);
	}

	// /TimePicker
	public void showTime(int hour, int min) {
		if (hour == 0) {
			hour += 12;
			format = "AM";
		} else if (hour == 12) {
			format = "PM";
		} else if (hour > 12) {
			hour -= 12;
			format = "PM";
		} else {
			format = "AM";
		}
		if (min < 10) {
			txtTime.setText(new StringBuilder().append(hour).append(" : 0")
					.append(min).append(" ").append(format));
		} else if (hour < 10) {
			txtTime.setText(new StringBuilder().append(" 0").append(hour)
					.append(" : ").append(min).append(" ").append(format));
		} else if (min < 10 && hour < 10) {
			txtTime.setText(new StringBuilder().append(" 0").append(hour)
					.append(" : 0").append(min).append(" ").append(format));
		} else {
			txtTime.setText(new StringBuilder().append(hour).append(" : ")
					.append(min).append(" ").append(format));
		}
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

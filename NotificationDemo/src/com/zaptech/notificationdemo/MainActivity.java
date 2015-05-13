package com.zaptech.notificationdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private NotificationManager mNotificationManager;
	private int notificationID = 100;
	private int numMessages = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button startBtn = (Button) findViewById(R.id.start);
		startBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				displayNotification();
			}

			private void displayNotification() {
				// TODO Auto-generated method stub

				Log.i("Start", "notification");

				/* Invoking the default notification service */
				NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
						getApplicationContext());

				mBuilder.setContentTitle("New Message");
				mBuilder.setContentText("You've received new message.");
				mBuilder.setTicker("New Message Alert!");
				mBuilder.setSmallIcon(R.drawable.ic_launcher);

				/*
				 * Increase notification number every time a new notification
				 * arrives
				 */
				mBuilder.setNumber(++numMessages);

				/* Creates an explicit intent for an Activity in your app */
				Intent resultIntent = new Intent(MainActivity.this,
						NotificationActivity.class);

				TaskStackBuilder a = TaskStackBuilder.create(MainActivity.this);

				a.addParentStack(NotificationActivity.class);

				/*
				 * Adds the Intent that starts the Activity to the top of the
				 * stack
				 */
				a.addNextIntent(resultIntent);
				PendingIntent resultPendingIntent = a.getPendingIntent(0,
						PendingIntent.FLAG_UPDATE_CURRENT);

				mBuilder.setContentIntent(resultPendingIntent);

				mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

				/*
				 * notificationID allows you to update the notification later
				 * on.
				 */
				mNotificationManager.notify(notificationID, mBuilder.build());

			}
		});

		Button cancelBtn = (Button) findViewById(R.id.cancel);
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				cancelNotification();
			}

			private void cancelNotification() {
				// TODO Auto-generated method stub
				Log.i("Cancel", "notification");
				mNotificationManager.cancel(notificationID);
			}
		});

		Button updateBtn = (Button) findViewById(R.id.update);
		updateBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				updateNotification();
			}

			private void updateNotification() {
				// TODO Auto-generated method stub
				// Log.i("Update", "notification");
				//
				// /* Invoking the default notification service */
				// NotificationCompat.Builder mBuilder =
				// new NotificationCompat.Builder(this);
				//
				// mBuilder.setContentTitle("Updated Message");
				// mBuilder.setContentText("You've got updated message.");
				// mBuilder.setTicker("Updated Message Alert!");
				// mBuilder.setSmallIcon(R.drawable.ic_launcher);
				//
				// /* Increase notification number every time a new notification
				// arrives */
				// mBuilder.setNumber(++numMessages);
				//
				// /* Creates an explicit intent for an Activity in your app */
				// Intent resultIntent = new
				// Intent(MainActivity.this,NotificationActivity.class);
				//
				// TaskStackBuilder stackBuilder =
				// TaskStackBuilder.create(this);
				// stackBuilder.addParentStack(NotificationActivity.class);
				//
				// /* Adds the Intent that starts the Activity to the top of the
				// stack */
				// stackBuilder.addNextIntent(resultIntent);
				// PendingIntent resultPendingIntent =
				// stackBuilder.getPendingIntent(
				// 0,
				// PendingIntent.FLAG_UPDATE_CURRENT
				// );
				//
				// mBuilder.setContentIntent(resultPendingIntent);
				//
				// mNotificationManager =
				// (NotificationManager)
				// getSystemService(Context.NOTIFICATION_SERVICE);
				//
				// /* Update the existing notification using same notification
				// ID */
				// mNotificationManager.notify(notificationID,
				// mBuilder.build());
			}
		});

	}
}

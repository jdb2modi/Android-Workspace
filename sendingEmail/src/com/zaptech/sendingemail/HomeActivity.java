package com.zaptech.sendingemail;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {
	Button startBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Button startBtn = (Button) findViewById(R.id.sendEmail);
		startBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				sendingEmail();
			}
		});

	}

	public void sendingEmail() {
		Log.i("Send email", "");

		String[] TO = { "jdb2modi@gmail.com" };
		String[] CC = { "bdbhargavmodi@gmail.com" };
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setType("text/plain");

		emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
		emailIntent.putExtra(Intent.EXTRA_CC, CC);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

		try {
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			finish();
			Log.i("Finished sending email...", "");
			Toast.makeText(HomeActivity.this, "Emial has been sent",
					Toast.LENGTH_LONG).show();
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(HomeActivity.this,
					"There is no email client installed.", Toast.LENGTH_SHORT)
					.show();
		}
	}
}

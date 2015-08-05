package com.zaptech.myexpenditure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class Activity_About extends Activity implements OnClickListener {
	private Button mBtn_1;
	private Button mBtn_2;
	private Button mBtn_exit;
	private WebView mWebView;

	protected void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.activity_about);
		init();
		setTypeface();
	};

	public void init() {
		mBtn_1 = (Button) findViewById(R.id.btn_getStarted);
		mBtn_1.setOnClickListener(this);
		mBtn_2 = (Button) findViewById(R.id.btn_getEnded);
		mBtn_2.setOnClickListener(this);
		mBtn_exit = (Button) findViewById(R.id.btn_exitFromAbout);
		mBtn_exit.setOnClickListener(this);
		mWebView = (WebView) findViewById(R.id.webview_About);

		mWebView.loadUrl("file:///android_asset/1.html");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_getStarted:
			mWebView.loadUrl("file:///android_asset/1.html");
			break;
		case R.id.btn_getEnded:
			mWebView.loadUrl("file:///android_asset/2.html");
			break;
		case R.id.btn_exitFromAbout:
			exit();
			break;
		default:
			break;
		}

	}

	private void exit() {
		Toast.makeText(getApplicationContext(), "Exiting...",
				Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alert = new AlertDialog.Builder(Activity_About.this);
		alert.setTitle("Exit Confirmation");
		alert.setMessage("Are you want to Close the Application ?");
		alert.setCancelable(false);
		alert.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();

			}
		});
		alert.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		alert.show();
	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getAssets(),
				"fonts/Tahoma.ttf");

		mBtn_1.setTypeface(tyFace);
		mBtn_2.setTypeface(tyFace);
		mBtn_exit.setTypeface(tyFace);

	}
}

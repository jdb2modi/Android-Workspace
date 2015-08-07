package com.zaptech.flurryanalytics;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.flurry.android.FlurryAgent;

public class MainActivity extends Activity implements OnClickListener {
	Button btn1, btn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		configureAndInitFlurry();
		init();

	}

	public void init() {
		btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(this);
		btn2 = (Button) findViewById(R.id.btn2);
		btn2.setOnClickListener(this);
	}

	public void configureAndInitFlurry() {
		// configure Flurry
		FlurryAgent.setLogEnabled(false);

		// init Flurry
		FlurryAgent.init(this, "TNHBJ85F24N8MNK8KMPQ");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			FlurryParameter();
			FlurryAgent.logEvent("Button1 Clicked", true);
			break;
		case R.id.btn2:
			FlurryParameter();
			FlurryAgent.logEvent("Button2 Clicked", true);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onStart() {
		FlurryAgent.onStartSession(MainActivity.this, "TNHBJ85F24N8MNK8KMPQ");
		super.onStart();
	}

	@Override
	protected void onStop() {
		FlurryAgent.onEndSession(MainActivity.this);
		super.onStop();
	}

	public void FlurryParameter() {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("Button One Parameter : ", "Jaimin Modi");
		parameters.put("Button Two Parameter : ", "Modi Jaimin");
		FlurryAgent.logEvent("Button has been Clicked", parameters);

	}
}

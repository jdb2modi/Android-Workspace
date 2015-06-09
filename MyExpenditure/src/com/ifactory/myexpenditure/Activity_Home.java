package com.ifactory.myexpenditure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Home extends Activity implements OnClickListener {
	Button btn_logout, btn_addExpence, btn_history, btn_banking;
	TextView txt_dailyExpence;
	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String PASSWORD = "password";
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

	}

	public void init() {
		btn_logout = (Button) findViewById(R.id.btn_homeLogout);
		btn_logout.setOnClickListener(this);
		btn_addExpence = (Button) findViewById(R.id.btn_addExpence);
		btn_addExpence.setOnClickListener(this);
		btn_banking = (Button) findViewById(R.id.btn_banking);
		btn_banking.setOnClickListener(this);
		sp = getSharedPreferences(MyPREFERENCES, MODE_APPEND);
		btn_history = (Button) findViewById(R.id.btn_history);
		btn_history.setOnClickListener(this);
		txt_dailyExpence = (TextView) findViewById(R.id.txt_dailyExpence);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_homeLogout:
			Editor edit = sp.edit();
			edit.clear();
			edit.commit();
			Toast.makeText(getApplicationContext(), "Logging out...",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_addExpence:
			finish();
			intent = new Intent(getApplicationContext(),
					Activity_AddExpence.class);
			startActivity(intent);
			break;
		case R.id.btn_history:
			finish();
			intent = new Intent(Activity_Home.this, Activity_History.class);
			startActivity(intent);
			break;
		case R.id.btn_banking:
			finish();
			intent = new Intent(Activity_Home.this, Activity_Banking.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getAssets(),
				"fonts/Tahoma.ttf");

		btn_logout.setTypeface(tyFace);
		btn_addExpence.setTypeface(tyFace);
		btn_history.setTypeface(tyFace);
		btn_banking.setTypeface(tyFace);
		txt_dailyExpence.setTypeface(tyFace);

	}
}
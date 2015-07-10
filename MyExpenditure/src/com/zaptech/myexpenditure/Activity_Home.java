package com.zaptech.myexpenditure;

import com.ifactory.myexpenditure.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	private Button btn_logout, btn_addExpence, btn_history, btn_banking,
			btn_settings, btn_about;
	private TextView txt_dailyExpence;

	public static final String MyPREFERENCES = "MyPrefs";
	public static final String PASSWORD = "password";

	DBHelper dbHelper;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

	}

	public void init() {
		dbHelper = new DBHelper(this);
		btn_logout = (Button) findViewById(R.id.btn_homeExit);
		btn_logout.setOnClickListener(this);
		btn_addExpence = (Button) findViewById(R.id.btn_addExpence);
		btn_addExpence.setOnClickListener(this);
		btn_banking = (Button) findViewById(R.id.btn_banking);
		btn_banking.setOnClickListener(this);
		btn_history = (Button) findViewById(R.id.btn_history);
		btn_history.setOnClickListener(this);
		btn_settings = (Button) findViewById(R.id.btn_Settings);
		btn_settings.setOnClickListener(this);
		btn_about = (Button) findViewById(R.id.btn_homeAbout);
		btn_about.setOnClickListener(this);
		txt_dailyExpence = (TextView) findViewById(R.id.txt_dailyExpence);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_homeExit:

			Toast.makeText(getApplicationContext(), "Exiting...",
					Toast.LENGTH_SHORT).show();
			AlertDialog.Builder alert = new AlertDialog.Builder(
					Activity_Home.this);
			alert.setTitle("Exit Confirmation");
			alert.setMessage("Are you want to Close the Application ?");
			alert.setCancelable(false);
			alert.setPositiveButton("EXIT",
					new DialogInterface.OnClickListener() {

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
		case R.id.btn_Settings:
			finish();
			intent = new Intent(Activity_Home.this, Activity_Settings.class);
			startActivity(intent);
			break;
		case R.id.btn_homeAbout:
			finish();
			intent = new Intent(Activity_Home.this, Activity_About.class);
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

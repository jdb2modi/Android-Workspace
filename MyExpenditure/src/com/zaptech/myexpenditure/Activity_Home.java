package com.zaptech.myexpenditure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Home extends Activity implements OnClickListener {
	private Button btn_logout, btn_manageBanking, btn_settings, btn_about,
			btn_manageExpence;
	private TextView txt_dailyExpence;

	public static final String MyPREFERENCES = "MyPrefs";
	public static final String PASSWORD = "password";

	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		setTypeface();
	}

	public void init() {

		btn_logout = (Button) findViewById(R.id.btn_homeExit);
		btn_logout.setOnClickListener(this);

		btn_manageBanking = (Button) findViewById(R.id.btn_manageBanking);
		btn_manageBanking.setOnClickListener(this);

		btn_settings = (Button) findViewById(R.id.btn_Settings);
		btn_settings.setOnClickListener(this);

		btn_about = (Button) findViewById(R.id.btn_homeAbout);
		btn_about.setOnClickListener(this);

		btn_manageExpence = (Button) findViewById(R.id.btn_manageExpence);
		btn_manageExpence.setOnClickListener(this);

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

		case R.id.btn_manageBanking:
			finish();
			intent = new Intent(Activity_Home.this,
					Activity_ManageBanking.class);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);
			break;
		case R.id.btn_Settings:
			finish();
			intent = new Intent(Activity_Home.this, Activity_Settings.class);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);
			break;
		case R.id.btn_homeAbout:
			finish();
			intent = new Intent(Activity_Home.this, Activity_About.class);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);
			break;
		case R.id.btn_manageExpence:
			finish();
			intent = new Intent(Activity_Home.this,
					Activity_ManageExpence.class);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
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

		btn_manageBanking.setTypeface(tyFace);
		txt_dailyExpence.setTypeface(tyFace);

	}
}

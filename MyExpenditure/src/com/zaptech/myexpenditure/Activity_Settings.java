package com.zaptech.myexpenditure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class Activity_Settings extends Activity implements OnClickListener {

	public static final String MyPREFERENCES = "MyPrefs";
	Button btn_exit, btn_back, btn_changeCode, btn_clearExpenceHistory,
			btn_clearBankHistory, btn_setAuthentication;
	Switch switch_securityCode;
	SharedPreferences spAuthentication;
	Intent intent;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		init();
		setTypeface();
		checkSwitch();
	}

	public void init() {
		btn_exit = (Button) findViewById(R.id.btn_exitFromSettings);
		btn_exit.setOnClickListener(this);
		btn_back = (Button) findViewById(R.id.btn_backFromSettings);
		btn_back.setOnClickListener(this);
		btn_clearExpenceHistory = (Button) findViewById(R.id.btn_clearExpenceHistory);
		btn_clearExpenceHistory.setOnClickListener(this);
		btn_clearBankHistory = (Button) findViewById(R.id.btn_clearBankHistory);
		btn_clearBankHistory.setOnClickListener(this);
		btn_changeCode = (Button) findViewById(R.id.btn_changeCode);
		btn_changeCode.setOnClickListener(this);
		btn_setAuthentication = (Button) findViewById(R.id.btn_setAuthentication);
		btn_setAuthentication.setOnClickListener(this);
		switch_securityCode = (Switch) findViewById(R.id.switch_SecurityCode);
		spAuthentication = getSharedPreferences(MyPREFERENCES,
				Context.MODE_APPEND);
		dbHelper = new DBHelper(this);

	}

	public void checkSwitch() {
		if (spAuthentication.contains("ENABLED")) {
			switch_securityCode.setChecked(true);
		} else {
			switch_securityCode.setChecked(false);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_exitFromSettings:

			Toast.makeText(getApplicationContext(), "Exiting...",
					Toast.LENGTH_SHORT).show();
			AlertDialog.Builder alert = new AlertDialog.Builder(
					Activity_Settings.this);
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
		case R.id.btn_backFromSettings:
			finish();
			intent = new Intent(Activity_Settings.this, Activity_Home.class);
			overridePendingTransition(R.anim.in_from_left_activity,
					R.anim.out_to_right_activity);
			startActivity(intent);
			break;
		case R.id.btn_changeCode:
			finish();
			intent = new Intent(Activity_Settings.this,
					Activity_ChangeCode.class);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);

			break;
		case R.id.btn_clearExpenceHistory:
			AlertDialog.Builder alertDeleteHistory = new AlertDialog.Builder(
					Activity_Settings.this);
			alertDeleteHistory.setTitle("Delete Confirmation");
			alertDeleteHistory
					.setMessage("Are you want to clear Expence history ?");
			alertDeleteHistory.setCancelable(false);
			alertDeleteHistory.setPositiveButton("DELETE NOW",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dbHelper.deleteExpenceHistory();
							Toast.makeText(Activity_Settings.this,
									"All Expence history deleted.",
									Toast.LENGTH_SHORT).show();
						}
					});
			alertDeleteHistory.setNegativeButton("CANCEL",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			alertDeleteHistory.show();
			break;
		case R.id.btn_clearBankHistory:
			AlertDialog.Builder alertDeleteHistory2 = new AlertDialog.Builder(
					Activity_Settings.this);
			alertDeleteHistory2.setTitle("Clear Bank History");
			alertDeleteHistory2
					.setMessage("Are you want to clear Bank history ?");
			alertDeleteHistory2.setCancelable(false);
			alertDeleteHistory2.setPositiveButton("CLEAR NOW",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dbHelper.deletebankHistory();
							Toast.makeText(Activity_Settings.this,
									"All Bank history cleared.",
									Toast.LENGTH_SHORT).show();
						}
					});
			alertDeleteHistory2.setNegativeButton("CANCEL",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			alertDeleteHistory2.show();
			break;
		case R.id.btn_setAuthentication:
			switch_securityCode.setVisibility(1);

			switch_securityCode
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							Editor ed = spAuthentication.edit();
							if (switch_securityCode.isChecked()) {

								ed.putBoolean("ENABLED", true);
								// switch_securityCode.setChecked(true);

							} else {
								// switch_securityCode.setChecked(false);
								ed.clear();

							}
							ed.commit();
						}
					});
			break;
		default:
			break;
		}

	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getAssets(),
				"fonts/Tahoma.ttf");

		btn_setAuthentication.setTypeface(tyFace);
		btn_exit.setTypeface(tyFace);
		btn_back.setTypeface(tyFace);
		btn_changeCode.setTypeface(tyFace);
		btn_clearExpenceHistory.setTypeface(tyFace);
		btn_clearBankHistory.setTypeface(tyFace);

	}
}

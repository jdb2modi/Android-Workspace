package com.ifactory.myexpenditure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Settings extends Activity implements OnClickListener {
	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";
	Button btn_exit, btn_back, btn_changeCode, btn_clearHistory,
			btn_setAuthentication;
	Intent intent;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		init();
	}

	public void init() {
		btn_exit = (Button) findViewById(R.id.btn_exitFromSettings);
		btn_exit.setOnClickListener(this);
		btn_back = (Button) findViewById(R.id.btn_backFromSettings);
		btn_back.setOnClickListener(this);
		btn_clearHistory = (Button) findViewById(R.id.btn_clearAllHistory);
		btn_clearHistory.setOnClickListener(this);
		btn_changeCode = (Button) findViewById(R.id.btn_setAuthentication);
		btn_changeCode.setOnClickListener(this);
		btn_setAuthentication = (Button) findViewById(R.id.btn_setAuthentication);
		btn_setAuthentication.setOnClickListener(this);
		dbHelper = new DBHelper(Activity_Settings.this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_exitFromSettings:
			Editor edit = sp.edit();
			edit.clear();
			edit.commit();
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
			startActivity(intent);
			break;
		case R.id.btn_changeCode:
			break;
		case R.id.btn_clearAllHistory:
			AlertDialog.Builder alertDeleteHistory = new AlertDialog.Builder(
					Activity_Settings.this);
			alertDeleteHistory.setTitle("Delete Confirmation");
			alertDeleteHistory
					.setMessage("Are you want to delete all the Expence history ?");
			alertDeleteHistory.setCancelable(false);
			alertDeleteHistory.setPositiveButton("DELETE NOW",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dbHelper.deleteHistory();
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
		case R.id.btn_setAuthentication:
			break;
		default:
			break;
		}

	}
}

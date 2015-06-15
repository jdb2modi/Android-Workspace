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

public class Activity_Banking extends Activity implements OnClickListener {
	Button btn_exit, btn_back;
	Intent intent;
	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bank_details);
		init();
	}

	public void init() {
		btn_exit = (Button) findViewById(R.id.btn_exitFromBankDetails);
		btn_back = (Button) findViewById(R.id.btn_backFromBankDetails);
		btn_exit.setOnClickListener(this);
		btn_back.setOnClickListener(this);
		sp = getSharedPreferences(MyPREFERENCES, MODE_APPEND);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_exitFromBankDetails:
			Editor edit = sp.edit();
			edit.clear();
			edit.commit();
			Toast.makeText(getApplicationContext(), "Exiting...",
					Toast.LENGTH_SHORT).show();
			AlertDialog.Builder alert = new AlertDialog.Builder(
					Activity_Banking.this);
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
		case R.id.btn_backFromBankDetails:
			finish();
			intent = new Intent(Activity_Banking.this, Activity_Home.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}

package com.zaptech.myexpenditure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Login extends Activity implements OnClickListener {
	EditText edPassword;
	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String CODE = "code";
	Button btn_Login, btn_Exit, btn_Hint;
	Intent intent;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__login);
		init();
		setTypeface();
		checkSecurityPref();
	}

	public void init() {
		edPassword = (EditText) findViewById(R.id.ed_password);
		sp = getSharedPreferences(MyPREFERENCES, Context.MODE_APPEND);
		btn_Login = (Button) findViewById(R.id.btn_authenticateNow);
		btn_Login.setOnClickListener(this);
		btn_Exit = (Button) findViewById(R.id.btn_exitFromLogin);
		btn_Exit.setOnClickListener(this);
		btn_Hint = (Button) findViewById(R.id.btn_passwordHint);
		btn_Hint.setOnClickListener(this);
		dbHelper = new DBHelper(Activity_Login.this);

	}

	public void checkSecurityPref() {
		if (!sp.contains("ENABLED")) {

			finish();
			Intent intent = new Intent(Activity_Login.this,
					Activity_MenuDrawer.class);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);

		}
	}

	public void authenticate() {
		String strEd = edPassword.getText().toString();
		String strTemp = dbHelper.getPassword().toString();
		if (dbHelper.checkRow() == 0) {
			btn_Hint.setVisibility(View.VISIBLE);
			dbHelper.insertPassword();
			Toast.makeText(Activity_Login.this, "Your Security code is 12345",
					Toast.LENGTH_LONG).show();
			Toast.makeText(Activity_Login.this,
					"First time Security code is 12345", Toast.LENGTH_SHORT)
					.show();
			String strPass = String.valueOf(dbHelper.getPassword());

			if (strEd.equals(strPass)) {
				finish();
				Intent intent = new Intent(Activity_Login.this,
						Activity_MenuDrawer.class);
				overridePendingTransition(R.anim.in_from_right_activity,
						R.anim.out_to_left_activity);
				startActivity(intent);
			}
		} else {
			btn_Hint.setVisibility(View.INVISIBLE);
			String strPass = String.valueOf(dbHelper.getPassword());

			if (strEd.equals(strPass)) {
				finish();
				Intent intent = new Intent(Activity_Login.this,
						Activity_MenuDrawer.class);
				overridePendingTransition(R.anim.in_from_right_activity,
						R.anim.out_to_left_activity);
				startActivity(intent);
			} else {
				Toast.makeText(Activity_Login.this,
						"Wrong Authentication Code.!", Toast.LENGTH_LONG)
						.show();
				edPassword.setText("");
				edPassword.setFocusable(true);
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_authenticateNow:
			authenticate();
			break;
		case R.id.btn_exitFromLogin:

			Toast.makeText(getApplicationContext(), "Exiting...",
					Toast.LENGTH_SHORT).show();
			AlertDialog.Builder alert = new AlertDialog.Builder(
					Activity_Login.this);
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
		case R.id.btn_passwordHint:
			Toast.makeText(Activity_Login.this, "Your Security Code is 12345",
					Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}

	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getAssets(),
				"fonts/Tahoma.ttf");

		edPassword.setTypeface(tyFace);
		btn_Login.setTypeface(tyFace);
		btn_Exit.setTypeface(tyFace);
		btn_Hint.setTypeface(tyFace);

	}
}

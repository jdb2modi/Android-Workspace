package com.zaptech.myexpenditure;

import com.ifactory.myexpenditure.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	Activity_Settings as;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__login);
		init();

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
		as = new Activity_Settings();
	}

	public void checkSecurityPref() {
		if (!sp.contains("ENABLED")) {

			finish();
			Intent intent = new Intent(Activity_Login.this, Activity_Home.class);
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
					"First time Security code is 12345", Toast.LENGTH_LONG)
					.show();
			String strPass = String.valueOf(dbHelper.getPassword());

			if (strEd.equals(strPass)) {
				finish();
				Intent intent = new Intent(Activity_Login.this,
						Activity_Home.class);
				startActivity(intent);
			}
		} else {
			btn_Hint.setVisibility(View.INVISIBLE);
			String strPass = String.valueOf(dbHelper.getPassword());
			Toast.makeText(Activity_Login.this, strPass, Toast.LENGTH_LONG)
					.show();
			if (strEd.equals(strPass)) {
				finish();
				Intent intent = new Intent(Activity_Login.this,
						Activity_Home.class);
				startActivity(intent);
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
}

package com.ifactory.myexpenditure;

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
	Button btn_Login, btn_Exit;
	Intent intent;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__login);
		init();
		savePreferences();
		checkPref();
	}

	public void savePreferences() {

		if (!sp.contains(CODE)) {
			Editor editor = sp.edit();
			editor.putString(CODE, "0");
			editor.commit();

		}

	}

	public void init() {
		edPassword = (EditText) findViewById(R.id.ed_password);
		sp = getSharedPreferences(MyPREFERENCES, Context.MODE_APPEND);
		btn_Login = (Button) findViewById(R.id.btn_authenticateNow);
		btn_Login.setOnClickListener(this);
		btn_Exit = (Button) findViewById(R.id.btn_exitFromLogin);
		btn_Exit.setOnClickListener(this);
		dbHelper = new DBHelper(Activity_Login.this);
	}

	public void checkPref() {
		if (sp.contains(CODE)) {
			String strCode = sp.getString(CODE, "");
			if (strCode.equals("1")) {
				String myPass = dbHelper.checkPassword().toString();
				if (edPassword.getText().toString().equals(myPass)) {
					finish();
					intent = new Intent(Activity_Login.this,
							Activity_Home.class);
					startActivity(intent);
				} else {
					Toast.makeText(Activity_Login.this, "Wrong Password",
							Toast.LENGTH_LONG).show();
					Toast.makeText(Activity_Login.this, "Wrong Password",
							Toast.LENGTH_LONG).show();
				}

			} else {
				if (edPassword.getText().toString().equals("12345")) {
					finish();
					intent = new Intent(Activity_Login.this,
							Activity_Home.class);
					startActivity(intent);
				} else {
					Toast.makeText(Activity_Login.this,
							"Current Security Code is 12345", Toast.LENGTH_LONG).show();
					Toast.makeText(Activity_Login.this,
							"Your Password is 12345", Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_authenticateNow:
			checkPref();
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
		default:
			break;
		}

	}
}

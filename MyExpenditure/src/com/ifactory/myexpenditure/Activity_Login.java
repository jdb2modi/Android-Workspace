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
	public static final String PASSWORD = "password";
	Button btn_Login, btn_Exit;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__login);
		init();

	}

	public void init() {
		edPassword = (EditText) findViewById(R.id.ed_password);
		sp = getSharedPreferences(MyPREFERENCES, Context.MODE_APPEND);
		btn_Login = (Button) findViewById(R.id.btn_authenticateNow);
		btn_Login.setOnClickListener(this);
		btn_Exit = (Button) findViewById(R.id.btn_exitFromLogin);
		btn_Exit.setOnClickListener(this);
	}

	public void savePreferences() {

		Editor editor = sp.edit();
		editor.putString(PASSWORD, edPassword.getText().toString());
		editor.commit();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_authenticateNow:
			savePreferences();
			Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT)
					.show();
			finish();
			intent = new Intent(Activity_Login.this, Activity_Home.class);
			startActivity(intent);
			break;
		case R.id.btn_exitFromLogin:
			Editor edit = sp.edit();
			edit.clear();
			edit.commit();
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

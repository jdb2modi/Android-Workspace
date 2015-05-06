package com.zaptech.sessionmanagement;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends Activity implements OnClickListener {
	EditText edUsername, edPassword;
	Button btnLogin;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String name = "nameKey";
	public static final String pass = "passwordKey";
	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	public void init() {
		edUsername = (EditText) findViewById(R.id.edUsername);
		edPassword = (EditText) findViewById(R.id.edPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);
	}

	protected void onResume() {
		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				this.MODE_PRIVATE);
		if (sharedpreferences.contains(name)) {
			if (sharedpreferences.contains(pass)) {
				Intent i = new Intent(HomeActivity.this, Welcome.class);
				startActivity(i);
			}
		}

		super.onResume();
	}

	public void login() {
		Editor editor = sharedpreferences.edit();
		String u = edUsername.getText().toString();
		String p = edPassword.getText().toString();
		editor.putString(name, u);
		editor.putString(pass, p);
		editor.commit();
		Intent i = new Intent(HomeActivity.this, Welcome.class);
		startActivity(i);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			login();
			break;

		default:
			break;
		}
	}
}

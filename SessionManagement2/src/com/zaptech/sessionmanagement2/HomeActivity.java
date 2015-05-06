package com.zaptech.sessionmanagement2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends Activity implements OnClickListener {
	EditText edUsername, edPassword;
	Button btnLogin;
	SharedPreferences sharedPreferences;

	public static final String PRE_NAME = "UserData";
	public static final String UERNAME = "Username";
	public static final String PASSWORD = "Password";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	@Override
	protected void onResume() {
		if (sharedPreferences.contains(UERNAME)
				&& sharedPreferences.contains(PASSWORD)) {
			gotoWelcome();
		}
		super.onResume();
	}

	public void init() {
		btnLogin = (Button) findViewById(R.id.btnLogin);
		edPassword = (EditText) findViewById(R.id.edPassword);
		edUsername = (EditText) findViewById(R.id.edUsername);
		btnLogin.setOnClickListener(this);
		sharedPreferences = this.getSharedPreferences(PRE_NAME,
				Context.MODE_PRIVATE);
	}

	public void login() {
		Editor editor = sharedPreferences.edit();
		editor.putString(UERNAME, edUsername.getText().toString());
		editor.putString(PASSWORD, edPassword.getText().toString());
		editor.commit();
		gotoWelcome();
	}

	public void gotoWelcome() {
		Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
		startActivity(intent);
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

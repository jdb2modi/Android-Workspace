package com.ifactory.myexpenditure;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	Button btn_Login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__login);
		init();

	}

	public void init() {
		edPassword = (EditText) findViewById(R.id.ed_Password);
		sp = getSharedPreferences(MyPREFERENCES, Context.MODE_APPEND);
		btn_Login = (Button) findViewById(R.id.btn_LoginNow);
		btn_Login.setOnClickListener(this);
	}

	public void savePreferences() {

		Editor editor = sp.edit();
		editor.putString(PASSWORD, edPassword.getText().toString());
		editor.commit();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_LoginNow:
			savePreferences();
			Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT)
					.show();
			break;

		default:
			break;
		}

	}
}

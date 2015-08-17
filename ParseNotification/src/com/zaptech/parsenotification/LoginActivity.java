package com.zaptech.parsenotification;

import com.zaptech.parsenotification.helper.ParseUtils;
import com.zaptech.parsenotification.helper.PrefManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText inputEmail;

	private Button btnLogin;

	private PrefManager pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Verifying parse configuration. This method is for developers only.
		ParseUtils.verifyParseConfiguration(this);

		pref = new PrefManager(getApplicationContext());
		if (pref.isLoggedIn()) {
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);

			finish();
		}
		setContentView(R.layout.activity_login);
		init();
	}

	public void init() {
		inputEmail = (EditText) findViewById(R.id.email);

		btnLogin = (Button) findViewById(R.id.btnLogin);

		btnLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			parseLogin();
			break;

		default:
			break;
		}
	}

	public void parseLogin() {
		String email = inputEmail.getText().toString();
		if (isValidEmail(email)) {
			pref.createLoginSession(email);
			startActivity(new Intent(LoginActivity.this, MainActivity.class));
			finish();
		} else {
			Toast.makeText(LoginActivity.this, "Please Enter Valid Email Address!!", Toast.LENGTH_SHORT).show();
		}
	}

	private boolean isValidEmail(String string) {
		return !TextUtils.isEmpty(string) && android.util.Patterns.EMAIL_ADDRESS.matcher(string).matches();
	}
}

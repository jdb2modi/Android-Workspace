package com.zaptech.tasklinearlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	Button btnVisitButtons, btnOtherControls, btnRegister, btnLogin, btnExit;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	public void init() {
		btnVisitButtons = (Button) findViewById(R.id.buttonVisitButtons);
		btnOtherControls = (Button) findViewById(R.id.buttonVisitAnotherControls);
		btnRegister = (Button) findViewById(R.id.buttonRegister);
		btnLogin = (Button) findViewById(R.id.buttonLogin);
		btnExit = (Button) findViewById(R.id.buttonExit);
		btnVisitButtons.setOnClickListener(this);
		btnOtherControls.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		btnExit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonVisitButtons:
			intent = new Intent(HomeActivity.this, SecondActivity.class);
			startActivity(intent);
			Toast.makeText(HomeActivity.this, R.string.toastMovedtoSecond,
					Toast.LENGTH_LONG).show();
			break;
		case R.id.buttonVisitAnotherControls:
			intent = new Intent(HomeActivity.this, ThirdActivity.class);
			startActivity(intent);
			Toast.makeText(HomeActivity.this, "Moved to Other Controls",
					Toast.LENGTH_LONG).show();
			break;
		case R.id.buttonRegister:
			intent = new Intent(HomeActivity.this, FourthActivity.class);
			startActivity(intent);
			Toast.makeText(HomeActivity.this, "Moved to Registration",
					Toast.LENGTH_LONG).show();
			break;
		case R.id.buttonLogin:
			intent = new Intent(HomeActivity.this, FifthActivity.class);
			startActivity(intent);
			Toast.makeText(HomeActivity.this, "Moved to Login",
					Toast.LENGTH_LONG).show();
			break;
		case R.id.buttonExit:
			Toast.makeText(HomeActivity.this, "Exiting", Toast.LENGTH_LONG)
					.show();
			this.finish();
			break;

		default:
			break;
		}

	}
}

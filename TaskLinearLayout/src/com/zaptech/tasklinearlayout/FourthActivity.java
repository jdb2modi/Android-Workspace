package com.zaptech.tasklinearlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FourthActivity extends Activity implements OnClickListener {
	Button btnGotoLogin;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);
		init();
	}

	public void init() {
		btnGotoLogin = (Button) findViewById(R.id.buttonGoToLogin);
		btnGotoLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonGoToLogin:
			this.finish();
			intent = new Intent(FourthActivity.this, FifthActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}

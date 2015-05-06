package com.zaptech.sessionmanagement;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends Activity implements OnClickListener {

	TextView tvWelcome;
	Button btnLogout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		init();
	}

	public void init() {
		btnLogout = (Button) findViewById(R.id.btnLogout);
		tvWelcome = (TextView) findViewById(R.id.tvWelcome);
		btnLogout.setOnClickListener(this);
	}

	public void logout() {
		SharedPreferences sharedpreferences = getSharedPreferences(
				HomeActivity.MyPREFERENCES, Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		editor.clear();
		editor.commit();
		moveTaskToBack(true);
		Welcome.this.finish();
	}

	/*public void exit(View view) {
		moveTaskToBack(true);
		Welcome.this.finish();
	}
*/
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogout:
			logout();
			break;

		default:
			break;
		}

	}

}

package com.example.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class AdvancedSetting extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.advanced_settings);
	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.advancedset_btn_back:
			Intent i_back = new Intent(AdvancedSetting.this,
					AccountSetting.class);
			startActivity(i_back);
			break;

		}
	}
}

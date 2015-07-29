package com.example.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class ContactUs extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_us);
	}
	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.contactus_btn_back:
			Intent i_back = new Intent(ContactUs.this,
					AccountSetting.class);
			startActivity(i_back);
			break;

		}
	}
}

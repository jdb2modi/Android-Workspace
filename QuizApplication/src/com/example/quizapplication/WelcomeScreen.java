package com.example.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class WelcomeScreen extends Activity {
	MediaPlayer mp;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		
	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.welcome_btn_email_login:
		
			 Intent intent = new Intent(WelcomeScreen.this,
					 LogInWith_Email.class);
					 startActivity(intent); 
			break;
		}
	}
}

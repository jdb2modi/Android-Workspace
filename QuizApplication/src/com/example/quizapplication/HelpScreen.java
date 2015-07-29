package com.example.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class HelpScreen extends Activity {
	TextView txthelp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.help);
		txthelp = (TextView) findViewById(R.id.help_txtV_detail);
		txthelp.setText("Login with Facebook or with email to play with your friends."+"\n\nFind your friends and invite them to play with you."
		+"\n\nSelect any of the options (Facebook, Email, Username, Random) to create the game."+"\n\nSpin the wheel to select the category."
				+"\n\nRe-spin the wheel by decreasing the 20 coins."+"\n\nTouch the correct option displayed on the screen."
		+"\n\nYou can remove two wrong options by decreasing the 20 coins."+"\n\nOnce the player complete the match you will get the scores.");

		
	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.help_btn_back:
			Intent i_back = new Intent(HelpScreen.this, AccountSetting.class);
			startActivity(i_back);
			break;

		}
	}
}

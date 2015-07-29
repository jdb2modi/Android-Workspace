package com.example.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class AboutUs extends Activity {
	TextView tv_aboutUs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about_us);

		tv_aboutUs = (TextView) findViewById(R.id.aboutus_txtVw_containt);

		tv_aboutUs
				.setText("Without limit competing against your friends and other opponents around the world with this fun and informative trivia game. "
						+ "\n \nPeople have been playing games in various forms since the days of the caveman, and competition is deeply ingrained in the human psyche."
						+ "\n\nWho doesnít love a good trivia game(s)? They are available in all shapes and sizes, from board games to apps, and cover a huge range of different subjects."
						+ "\n\nQuizz-it has been specially designed and developed to be different than other trivia apps where you just compete against yourself by trying to correctly answer a bunch of questions. By using social networking ,"
						+ "Quizz-it"
						+ "provides for real interactive game competition against your friends and other opponents worldwide."
						+ "\n \nWith the spin of a wheel, each quiz randomly selects questions from either Sport, Logic, Music, Movie, History or Geography."
						+ "\n\nOne very neat aspect of this game is the fact that players can easily challenge their friends using their Facebook account or their email account. This means that the app creates real competition! This is a really great addition and definitely makes this app a must install.");
	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.aboutus_btn_back:
			Intent i_back = new Intent(AboutUs.this, AccountSetting.class);
			startActivity(i_back);
			break;

		}
	}
}

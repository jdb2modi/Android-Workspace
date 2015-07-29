package com.example.quizapplication;


import com.example.quizapplication.model.ImageLoader;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryList extends Activity{
	private SharedPreferences preferences;
	TextView txtTimer,txtques,txtquestext,txtOpp,txtUser;
	ImageView Imgdusterline,ImgUserPhoto,ImgOppPhoto;
	public ImageLoader imageLoader;
	String Url = "http://122.170.97.189:81/iphonequiz/user_photos/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category);
		preferences = PreferenceManager
				.getDefaultSharedPreferences(CategoryList.this);
		imageLoader = new ImageLoader(CategoryList.this);
		String userName = preferences.getString("u_name_pref", "");
		String oppPhoto = preferences.getString("opp_Pic_pref", "");
	
		Log.e("Oppenent Photo : ", Url + oppPhoto);
		ImgUserPhoto = (ImageView) findViewById(R.id.category_imgVw_user);
		ImgOppPhoto = (ImageView) findViewById(R.id.category_imgVw_opponent);
		txtUser = (TextView) findViewById(R.id.category_txt_name_user);
		txtOpp = (TextView)  findViewById(R.id.category_txt_name_opponent);
		txtTimer = (TextView) findViewById(R.id.txtstart);
		txtques = (TextView) findViewById(R.id.quizscreen_txt_question);
		txtquestext = (TextView) findViewById(R.id.quizscreen_txt_questionText);
		txtUser.setText("Me");
		txtOpp.setText(userName);
		imageLoader.DisplayImage(oppPhoto,ImgUserPhoto);
		imageLoader.DisplayImage(Url + oppPhoto, ImgOppPhoto);
		
		try { 
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		CountDownTimer cn =  new CountDownTimer(4000,1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				long milli = millisUntilFinished/1000;
				txtTimer.setText("" + milli);
				
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent itoquestionlist = new Intent(getApplicationContext(), QuizScreen.class);
				startActivity(itoquestionlist);
								
			}
		};
		
		cn.start();
		
	}

}

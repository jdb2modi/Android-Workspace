package com.example.quizapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class QuizScreenImage extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.quizscreen_image);
	}
}

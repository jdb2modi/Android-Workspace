package com.example.quizapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Shop extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop);
	}
	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.shop_btn_back:
			finish();
		break;

		}
	}
}

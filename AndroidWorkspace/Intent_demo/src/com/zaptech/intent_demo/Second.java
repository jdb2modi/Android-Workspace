package com.zaptech.intent_demo;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Second extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_second);
		Intent i= getIntent();
		Toast.makeText(getApplicationContext(),String.valueOf(i.getStringExtra("Name")),Toast.LENGTH_LONG).show();
				
		
	}
	

	
}

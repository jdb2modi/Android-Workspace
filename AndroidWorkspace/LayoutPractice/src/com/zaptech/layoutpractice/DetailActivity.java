package com.zaptech.layoutpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends Activity {
	
	private String name;
	private String lastName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acivity_main_details);
		
		Intent extra= getIntent();
		if (extra!=null) {
			name=extra.getStringExtra("USERNAME");
			lastName=extra.getStringExtra("LASTNAME");
		}
		
		Toast.makeText(this, "Hi "+name+" "+lastName, Toast.LENGTH_LONG).show();
	}

}

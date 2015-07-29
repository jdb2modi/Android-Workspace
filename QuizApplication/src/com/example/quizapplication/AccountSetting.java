package com.example.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class AccountSetting extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.account_setting);
	}
	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.accountSet_btn_back:
			 Intent i_back = new Intent(AccountSetting.this,
					 HomeScreen.class);
					 startActivity(i_back); 
			break;
		case R.id.accountset_linear_profilesetting:
			 Intent i_profileset = new Intent(AccountSetting.this,
					 ProfileSetting.class);
					 startActivity(i_profileset); 
			break;
		case R.id.accountset_linear_help:
			 Intent i_help = new Intent(AccountSetting.this,
					 HelpScreen.class);
					 startActivity(i_help); 
			break;
		case R.id.accountset_linear_aboutus:
			 Intent i_aboutus = new Intent(AccountSetting.this,
					 AboutUs.class);
					 startActivity(i_aboutus); 
			break;
		case R.id.accountset_linear_contactus:
			final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);                    
	        emailIntent.setType("plain/text");               
	        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ "rutupatel90@gmail.com"});  
	        emailIntent.putExtra(android.content.Intent.EXTRA_CC, new String[] {"dharapatel23@yahoo.com"});
	        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");             
	        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, ""); 
	        //emailIntent.putExtra(android.content.Intent.E, "");     
	        AccountSetting.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			break;
		case R.id.accountset_btn_advenceset:
			 Intent i_advset = new Intent(AccountSetting.this,
					 AdvancedSetting.class);
					 startActivity(i_advset); 
			break;
			
		
		}
	}
}

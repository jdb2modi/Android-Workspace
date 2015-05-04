package com.zaptech.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserDataActivity extends Activity implements OnClickListener {

	EditText edName,edLastName,edEmail,edContact,edGender,edCity;
	TextView tvUserBanner,tvAndroid,tvIos,tvBerry;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userdata_layout);
		init();
		getData();
	}
	public void init(){
		edName=(EditText)findViewById(R.id.user_form_et_name);
		edLastName=(EditText)findViewById(R.id.user_form_et_lastname);
		edEmail=(EditText)findViewById(R.id.user_form_et_email);
		edContact=(EditText)findViewById(R.id.user_form_et_contact);
		edGender=(EditText)findViewById(R.id.user_ed_gender);
		edCity=(EditText)findViewById(R.id.user_form_et_city);
		
		tvUserBanner=(TextView)findViewById(R.id.user_Banner_Text);
		tvAndroid=(TextView)findViewById(R.id.userTv_Android);
		tvIos=(TextView)findViewById(R.id.userTv_IOS);
		tvBerry=(TextView)findViewById(R.id.userTv_Berry);
		///Adding Listener
		tvAndroid.setOnClickListener(UserDataActivity.this);
		tvIos.setOnClickListener(UserDataActivity.this);
		tvBerry.setOnClickListener(UserDataActivity.this);
		
	}
	public void getData(){
		String strName,strLastName,strEmail,strContact,strGender,strCity;
		Intent i=getIntent();
		strName=i.getStringExtra("NAME");
		strLastName=i.getStringExtra("LASTNAME");
		strEmail=i.getStringExtra("EMAIL");
		strContact=i.getStringExtra("CONTACT");
		strGender=i.getStringExtra("GENDER");
		strCity=i.getStringExtra("CITY");
		
		edName.setText(strName);
		edLastName.setText(strLastName);
		edEmail.setText(strEmail);
		edContact.setText(strContact);
		edGender.setText(strGender);
		edCity.setText(strCity);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.userTv_IOS:
				tvUserBanner.setText("IPhone Operating System");
			break;
		case R.id.userTv_Android:
				tvUserBanner.setText("Android Operating System");
			break;
		case R.id.userTv_Berry:
				tvUserBanner.setText("BlackBerry Operating System");
			break;
		default:
			break;
		}
		
	}
}

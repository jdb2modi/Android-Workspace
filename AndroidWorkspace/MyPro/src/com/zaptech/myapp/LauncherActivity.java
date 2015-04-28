package com.zaptech.myapp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LauncherActivity extends Activity implements OnClickListener {
	Button btnLogin,btnRegister;
	ImageView imgHome;
	ImageButton imgBtnHomeInfo;
	TextView tvIosl,tvAndroidl,tvBerryl,tvCenterBannerText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
		showMessage("Welcome to JDB Project", "Good Luck..!!!, Go Ahead.");
		init();
	}//End of onCreate()...
	public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
	public void init(){
		btnLogin=(Button)findViewById(R.id.login);
		btnRegister=(Button) findViewById(R.id.register);
		imgHome=(ImageView)findViewById(R.id.home_logo);
		imgBtnHomeInfo=(ImageButton)findViewById(R.id.home_info);
		
		
		tvIosl=(TextView)findViewById(R.id.launcher_bannerTv_IOS);
		tvAndroidl=(TextView)findViewById(R.id.launcher_bannerTv_Android);
		tvBerryl=(TextView)findViewById(R.id.launcher_bannerTv_Berry);
		tvCenterBannerText=(TextView)findViewById(R.id.Center_Banner_Text);
		
		//Adding Listener to Buttons..
		btnLogin.setOnClickListener(LauncherActivity.this);
		btnRegister.setOnClickListener(LauncherActivity.this);
		tvIosl.setOnClickListener(this);
		tvAndroidl.setOnClickListener(this);
		tvBerryl.setOnClickListener(this);
		imgBtnHomeInfo.setOnClickListener(this);
	
		//imgHome.setOnClickListener(LauncherActivity.this);
		
	}//End of init()...
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register:
				Intent intentReg=new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intentReg);
			break;
		case R.id.home_logo:
			Toast.makeText(getApplicationContext(),"Already at Home",Toast.LENGTH_LONG).show();
		break;
		case R.id.launcher_bannerTv_Android:
			tvCenterBannerText.setText("Android Operating System");
		break;
		case R.id.launcher_bannerTv_IOS:
			tvCenterBannerText.setText("Iphone Operating System");
		break;
		case R.id.launcher_bannerTv_Berry:
			tvCenterBannerText.setText("Black-Berry Oprating System");
		break;
		case R.id.login:
			showLoginDialog();
		break;
		case R.id.home_info:
			
			showInfoDialog();
		default:
			break;
		}//End of Switch()...
		
	}//End of onClick()...
	public void showLoginDialog(){
		final Dialog loginDialod = new Dialog(LauncherActivity.this);
		//loginDialod.requestWindowFeature(Window.FEATURE_NO_TITLE);
		loginDialod.setContentView(R.layout.login_layout);
		loginDialod.setTitle("Login");
		loginDialod.setCancelable(true);
		loginDialod.dismiss();
		loginDialod.show();
		
		
	}//End of showLoginDialog()...
	public void showInfoDialog(){
		final Dialog infoDialod = new Dialog(LauncherActivity.this);
		//loginDialod.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		infoDialod.setTitle("Mobile Computing");
		infoDialod.setContentView(R.layout.infolayout);
		infoDialod.setCancelable(true);
		infoDialod.show();
		
		
	}//End of showLoginDialog()...
}

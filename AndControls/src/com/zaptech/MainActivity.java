package com.zaptech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView txAuto,txRating,txWebview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txAuto=(TextView)findViewById(R.id.txtAutoComplete);
		txRating=(TextView)findViewById(R.id.txtRating);
		txWebview=(TextView)findViewById(R.id.webView1);
		txAuto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,AutoCompleteAct.class);
				startActivity(i);
			}
		});
		txRating.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,AutoCompleteAct.class);
				startActivity(i);
			}
		});
		txWebview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,WebViewActivity.class);
				startActivity(i);
			}
		});
	}
}

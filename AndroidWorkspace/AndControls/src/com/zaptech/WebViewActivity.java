package com.zaptech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
	WebView wv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		wv1=(WebView)findViewById(R.id.webView1);
		Intent i=getIntent();
		
		//wv1.loadUrl("http://www.javatpoint.com/android-webview-example");
	}
}

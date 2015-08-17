package com.zaptech.webviewclientapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	private ProgressBar mprogress;
	private WebView mwebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();

		mwebView.setWebViewClient(new myWebViewClient());
		mwebView.getSettings().setJavaScriptEnabled(true);
		mwebView.loadUrl("http://developer.android.com/reference/android/view/inputmethod/InputMethodManager.html");
		System.err.println("After LoadURL");
	}

	public void init() {
		mprogress = (ProgressBar) findViewById(R.id.p1);
		mwebView = (WebView) findViewById(R.id.webview1);
		System.err.println("Inside init");

	}

	public class myWebViewClient extends WebViewClient {
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {

			super.onPageStarted(view, url, favicon);
			System.err.println("Inside onPageStarted");
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			System.err.println("Inside urlLoading");
			return super.shouldOverrideUrlLoading(view, url);
		}

		@Override
		public void onPageFinished(WebView view, String url) {

			super.onPageFinished(view, url);
			System.err.println("Inside onPageFinished");
			mprogress.setVisibility(View.GONE);
		}
	}
}

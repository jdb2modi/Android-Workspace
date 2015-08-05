package com.zaptech.myexpenditure2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

public class FragmentAbout extends Fragment {

	private Button mBtnExit;
	private Button mGetStarted;
	private Button mGetEnded;
	private WebView mWebView;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_about, container, false);
		init(rootView);
		onClick();
		return rootView;
	}

	public void init(View rootView) {
		mBtnExit = (Button) rootView.findViewById(R.id.btn_exitFromAbout);
		mGetStarted = (Button) rootView.findViewById(R.id.btn_getStarted);
		mGetEnded = (Button) rootView.findViewById(R.id.btn_getEnded);
	}

	public void onClick() {
		mBtnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mGetStarted.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mGetEnded.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}
}
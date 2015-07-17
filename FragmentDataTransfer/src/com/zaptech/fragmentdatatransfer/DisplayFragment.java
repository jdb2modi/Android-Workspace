package com.zaptech.fragmentdatatransfer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayFragment extends Fragment {
	TextView txtDisplay;
	String myStr;
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootview;
		rootview = inflater
				.inflate(R.layout.fragment_display, container, false);
		init(rootview);
		Bundle bundle = this.getArguments();
		myStr = bundle.getString("name");
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>"+myStr);
		txtDisplay.setText(myStr);
		return rootview;

	}

	public void init(View rootview) {
		txtDisplay = (TextView) rootview.findViewById(R.id.txtDisplay);
	}
}

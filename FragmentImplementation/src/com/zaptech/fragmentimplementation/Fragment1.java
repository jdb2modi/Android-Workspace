package com.zaptech.fragmentimplementation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment1 extends Fragment {
	TextView txt1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment1, null);
		init(rootView);
		return rootView;
	}

	public void init(View rootView) {
		txt1 = (TextView) rootView.findViewById(R.id.txtFragment1);
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>TXT1 DEFAULT: "+txt1.getText().toString());
		Bundle bundle = getArguments();
		if (bundle != null) {
			String msg = bundle.getString("MSG1");
			if (msg != null) {
				txt1.setText(msg);
				System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>TXT1 DEFAULT: "+txt1.getText().toString());
			}
			else
			{
				txt1.setText("null 1");
				System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>TXT1 DEFAULT: "+txt1.getText().toString());
			}
		}

	}
}

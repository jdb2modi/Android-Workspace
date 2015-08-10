package com.zaptech.tabfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondActivity extends Fragment {

	TextView txt_getData;
	String value;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.second_layout, container,
				false);

		// Bundle args = getArguments();

		// value = args.getString("YourKey");

		System.err.println("Data Rec===" + value);
		txt_getData = (TextView) rootView.findViewById(R.id.txt_getData);

		// txt_getData.setText(value);
		return rootView;
	}
}

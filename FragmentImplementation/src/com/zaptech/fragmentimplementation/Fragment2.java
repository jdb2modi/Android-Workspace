package com.zaptech.fragmentimplementation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {
	TextView txt2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment2, null);
		init(rootView);
		return rootView;
	}

	public void init(View rootView) {
		txt2 = (TextView) rootView.findViewById(R.id.txtFragment2);
		Bundle bundle = getArguments();
		if (bundle != null) {
			String msg = bundle.getString("MSG2");
			if (msg != null) {
				txt2.setText(msg);
			}
		}

	}
}

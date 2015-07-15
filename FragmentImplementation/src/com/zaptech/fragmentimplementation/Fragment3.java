package com.zaptech.fragmentimplementation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment3 extends Fragment {
	TextView txt3;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootview;
		rootview = inflater.inflate(R.layout.fragment3, null);
		init(rootview);
		return rootview;

	}

	public void init(View rootview) {
		txt3 = (TextView) rootview.findViewById(R.id.txtFragment3);
		Bundle bundle = getArguments();
		if (bundle != null) {
			String msg = bundle.getString("MSG3");
			if (msg != null) {
				txt3.setText(msg);
			}
		}

	}
}

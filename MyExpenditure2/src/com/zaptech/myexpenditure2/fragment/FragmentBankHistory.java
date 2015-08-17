package com.zaptech.myexpenditure2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zaptech.myexpenditure2.R;

public class FragmentBankHistory extends Fragment {
	private ListView mlistBankDetails;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_bankhistory, container,
				false);
		init(rootView);
		onClick();
		return rootView;
	}

	public void init(View rootView) {
		mlistBankDetails = (ListView) rootView
				.findViewById(R.id.listBankDetails);
	}

	public void onClick() {

	}
}

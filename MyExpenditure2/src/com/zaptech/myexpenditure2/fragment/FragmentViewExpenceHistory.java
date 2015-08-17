package com.zaptech.myexpenditure2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zaptech.myexpenditure2.R;

public class FragmentViewExpenceHistory extends Fragment {
	private ListView mlistExpenceHistory;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_view_expencehistory,
				container, false);
		init(rootView);
		return rootView;
	}

	public void init(View rootView) {
		mlistExpenceHistory = (ListView) rootView
				.findViewById(R.id.listHistory);
	}
}

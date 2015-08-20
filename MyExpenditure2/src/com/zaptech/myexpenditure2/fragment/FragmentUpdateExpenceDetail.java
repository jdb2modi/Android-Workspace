package com.zaptech.myexpenditure2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zaptech.myexpenditure2.R;

public class FragmentUpdateExpenceDetail extends Fragment implements
		OnClickListener {
	private EditText medExpenceTitle;
	private EditText medExpenceDescription;
	private EditText medExpenceAmount;
	private String strData;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_updateexpencedetail,
				container, false);
		init(rootView);
		Bundle bundle = this.getArguments();
		strData = bundle.getString("ExpenceTitleToUpdate");

		return rootView;
	}

	public void init(View rootView) {
		medExpenceAmount = (EditText) rootView
				.findViewById(R.id.ed_updateExpenceTitle);
		medExpenceDescription = (EditText) rootView
				.findViewById(R.id.ed_updateExpenceDescription);
		medExpenceTitle = (EditText) rootView
				.findViewById(R.id.ed_updateExpenceTitle);
	}

	@Override
	public void onClick(View v) {

	}

}

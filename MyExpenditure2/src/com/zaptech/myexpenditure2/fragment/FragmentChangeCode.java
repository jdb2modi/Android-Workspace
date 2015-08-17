package com.zaptech.myexpenditure2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zaptech.myexpenditure2.R;

public class FragmentChangeCode extends Fragment {
	private EditText mEdCurrentCode;
	private EditText mEdNewCode;
	private EditText mEdConfirmCode;
	private Button mBtnBack;
	private Button mBtnExit;
	private Button mBtnChangeAuthentication;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_changecode, container,
				false);
		init(rootView);
		onClick();
		return rootView;
	}

	public void init(View rootView) {
		mEdCurrentCode = (EditText) rootView.findViewById(R.id.ed_currentCode);
		mEdNewCode = (EditText) rootView.findViewById(R.id.ed_newCode);
		mEdConfirmCode = (EditText) rootView.findViewById(R.id.ed_confirmCode);

		mBtnBack = (Button) rootView.findViewById(R.id.btn_backFromChangeCode);
		mBtnExit = (Button) rootView.findViewById(R.id.btn_exitFromChangeCode);
		mBtnChangeAuthentication = (Button) rootView
				.findViewById(R.id.btn_changeAuthentication);
	}

	public void onClick() {
		mBtnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fsettings = new FragmentSettings();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fsettings);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		mBtnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnChangeAuthentication.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}
}

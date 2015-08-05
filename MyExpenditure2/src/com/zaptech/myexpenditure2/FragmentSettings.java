package com.zaptech.myexpenditure2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class FragmentSettings extends Fragment {

	private Button mBtnCahngeCode;
	private Button mBtnClearExpenceHistory;
	private Button mBtnClearBankHistory;
	private Button mBtnSetAuthentication;
	private Button mBtnBack;
	private Button mBtnExit;
	private Switch mSwitchSecurityCode;
	SharedPreferences spAuthentication;
	DBHelper dbHelper;
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_settings, container,
				false);
		init(rootView);
		checkSwitch();
		// setTypeface();
		onClick();

		return rootView;
	}

	public void init(View rootView) {
		mBtnBack = (Button) rootView.findViewById(R.id.btn_backFromSettings);
		mBtnExit = (Button) rootView.findViewById(R.id.btn_exitFromSettings);
		mBtnSetAuthentication = (Button) rootView
				.findViewById(R.id.btn_setAuthentication);
		mBtnClearBankHistory = (Button) rootView
				.findViewById(R.id.btn_clearBankHistory);
		mBtnClearExpenceHistory = (Button) rootView
				.findViewById(R.id.btn_clearExpenceHistory);
		mBtnCahngeCode = (Button) rootView.findViewById(R.id.btn_changeCode);
		mSwitchSecurityCode = (Switch) rootView
				.findViewById(R.id.switch_SecurityCode);
		spAuthentication = getActivity().getSharedPreferences(MyPREFERENCES,
				Context.MODE_APPEND);
		dbHelper = new DBHelper(getActivity());
	}

	public void onClick() {
		mBtnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fHome = new FragmentHome();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fHome);
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
		mBtnSetAuthentication.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mSwitchSecurityCode.setVisibility(1);

				mSwitchSecurityCode
						.setOnCheckedChangeListener(new OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								Editor ed = spAuthentication.edit();
								if (mSwitchSecurityCode.isChecked()) {

									ed.putBoolean("ENABLED", true);
									// switch_securityCode.setChecked(true);

								} else {
									// switch_securityCode.setChecked(false);
									ed.clear();

								}
								ed.commit();
							}
						});
			}
		});
		mBtnClearBankHistory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDeleteHistory2 = new AlertDialog.Builder(
						getActivity());
				alertDeleteHistory2.setTitle("Clear Bank History");
				alertDeleteHistory2
						.setMessage("Are you want to clear Bank history ?");
				alertDeleteHistory2.setCancelable(false);
				alertDeleteHistory2.setPositiveButton("CLEAR NOW",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dbHelper.deletebankHistory();
								Toast.makeText(getActivity(),
										"All Bank history cleared.",
										Toast.LENGTH_SHORT).show();
							}
						});
				alertDeleteHistory2.setNegativeButton("CANCEL",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				alertDeleteHistory2.show();
			}
		});
		mBtnClearExpenceHistory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnCahngeCode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fChangeCode = new FragmentChangeCode();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fChangeCode);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
	}

	public void checkSwitch() {
		if (spAuthentication.contains("ENABLED")) {
			mSwitchSecurityCode.setChecked(true);
		} else {
			mSwitchSecurityCode.setChecked(false);
		}
	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Tahoma.ttf");

		mBtnSetAuthentication.setTypeface(tyFace);
		mBtnExit.setTypeface(tyFace);
		mBtnBack.setTypeface(tyFace);
		mBtnCahngeCode.setTypeface(tyFace);
		mBtnClearExpenceHistory.setTypeface(tyFace);
		mBtnClearBankHistory.setTypeface(tyFace);

	}
}

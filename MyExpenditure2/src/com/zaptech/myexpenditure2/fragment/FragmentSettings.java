package com.zaptech.myexpenditure2.fragment;

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

import com.zaptech.myexpenditure2.R;
import com.zaptech.myexpenditure2.database.DBHelper;

public class FragmentSettings extends Fragment {

	private Button mBtnCahngeCode;
	private Button mBtnClearExpenceHistory;
	private Button mBtnClearBankHistory;
	private Button mBtnSetAuthentication;

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
				clearExpenceHistory();
			}
		});
		mBtnCahngeCode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fChangeCode = new FragmentChangeCode();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();

				ft.setCustomAnimations(R.anim.in_from_right_activity,
						R.anim.out_to_left_activity);
				ft.replace(R.id.main, fChangeCode);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack("FragmentSettings");
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
		mBtnCahngeCode.setTypeface(tyFace);
		mBtnClearExpenceHistory.setTypeface(tyFace);
		mBtnClearBankHistory.setTypeface(tyFace);

	}

	public void clearExpenceHistory() {
		AlertDialog.Builder alertDeleteHistory = new AlertDialog.Builder(
				getActivity());
		alertDeleteHistory.setTitle("Delete Confirmation");
		alertDeleteHistory
				.setMessage("Are you want to clear Expence history ?");
		alertDeleteHistory.setCancelable(false);
		alertDeleteHistory.setPositiveButton("DELETE NOW",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dbHelper.deleteExpenceHistory();
						Toast.makeText(getActivity(),
								"All Expence history deleted.",
								Toast.LENGTH_SHORT).show();
					}
				});
		alertDeleteHistory.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		alertDeleteHistory.show();
	}
}

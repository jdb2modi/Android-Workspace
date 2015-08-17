package com.zaptech.myexpenditure2.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.zaptech.myexpenditure2.R;

public class FragmentManageExpence extends Fragment {
	private Button mBtnAddExpence;
	private Button mBtnExpenceHistory;
	private Button mBtnUpdateExpenceDetails;
	private Button mBtnRemoveExpence;
	private Button mBtnClearExpenceHistory;
	private Button mBtnBack;
	private Button mBtnExit;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_manageexpence, container,
				false);
		init(rootView);
		onClick();
		return rootView;
	}

	public void init(View rootView) {
		mBtnAddExpence = (Button) rootView
				.findViewById(R.id.btn_addExpenceOnManageExpence);
		mBtnExpenceHistory = (Button) rootView
				.findViewById(R.id.btn_expenceHistoryOnManageExpence);
		mBtnUpdateExpenceDetails = (Button) rootView
				.findViewById(R.id.btn_updateExpenceOnManageExpence);
		mBtnRemoveExpence = (Button) rootView
				.findViewById(R.id.btn_removeExpenceOnManageExpence);
		mBtnClearExpenceHistory = (Button) rootView
				.findViewById(R.id.btn_removeAllExpenceOnManageExpence);
		mBtnBack = (Button) rootView.findViewById(R.id.btn_backOnManageExpence);
		mBtnExit = (Button) rootView.findViewById(R.id.btn_exitOnManageExpence);
	}

	public void onClick() {
		mBtnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fHome = new FragmentHome();
				Fragment fManageExpence = new FragmentManageExpence();
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
				Toast.makeText(getActivity(), "Exiting...", Toast.LENGTH_SHORT)
						.show();
				AlertDialog.Builder alert = new AlertDialog.Builder(
						getActivity().getApplicationContext());
				alert.setTitle("Exit Confirmation");
				alert.setMessage("Are you want to Close the Application ?");
				alert.setCancelable(false);
				alert.setPositiveButton("EXIT",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								getActivity().finish();

							}
						});
				alert.setNegativeButton("CANCEL",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				alert.show();
			}
		});
		mBtnAddExpence.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fAddExpence = new FragmentAddExpence();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fAddExpence);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		mBtnExpenceHistory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fExpenceHistory = new FragmentExpenceHistory();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.main, fExpenceHistory);
				// ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				// ft.addToBackStack(null);
				ft.commit();
			}
		});
		mBtnUpdateExpenceDetails.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnRemoveExpence.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mBtnClearExpenceHistory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}
}

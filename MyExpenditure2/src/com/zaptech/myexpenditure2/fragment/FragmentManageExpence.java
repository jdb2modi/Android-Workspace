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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaptech.myexpenditure2.R;
import com.zaptech.myexpenditure2.database.DBHelper;

public class FragmentManageExpence extends Fragment {
	private Button mBtnAddExpence;
	private Button mBtnExpenceHistory;
	private Button mBtnUpdateExpenceDetails;
	private Button mBtnRemoveExpence;
	private Button mBtnClearExpenceHistory;

	private DBHelper dbHelper;

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
		dbHelper = new DBHelper(getActivity());
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

	}

	public void onClick() {

		mBtnAddExpence.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fAddExpence = new FragmentAddExpence();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();

				ft.setCustomAnimations(R.anim.in_from_right_activity,
						R.anim.out_to_left_activity);

				ft.replace(R.id.main, fAddExpence);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null).commit();

			}
		});
		mBtnExpenceHistory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fExpenceHistory = new FragmentExpenceHistory();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();

				ft.setCustomAnimations(R.anim.in_from_right_activity,
						R.anim.out_to_left_activity);

				ft.replace(R.id.main, fExpenceHistory);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack("FragmentManageExpence");
				ft.commit();
			}
		});
		mBtnUpdateExpenceDetails.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateAlert();
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
				clearExpenceHistory();
			}
		});

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

	private void updateAlert() {
		LayoutInflater li = LayoutInflater.from(getActivity());
		View promptsView = li.inflate(R.layout.custom_update_expence, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				getActivity());

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final Spinner spinExpenceToUpdate = (Spinner) promptsView
				.findViewById(R.id.spinExpenceToUpdate);
		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						Fragment fUpdateExpence = new FragmentUpdateExpenceDetail();
						FragmentTransaction ft = getFragmentManager()
								.beginTransaction();

						getActivity().overridePendingTransition(
								R.anim.in_from_right_activity,
								R.anim.out_to_left_activity);

						ft.replace(R.id.main, fUpdateExpence);
						ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
						ft.addToBackStack("FragmentManageExpence");
						ft.commit();
						Bundle bundle = new Bundle();
						bundle.putString("ExpenceTitleToUpdate",
								spinExpenceToUpdate.getSelectedItem()
										.toString().trim());
						fUpdateExpence.setArguments(bundle);
					}
				})
				.setNegativeButton("CANCEL",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}
}

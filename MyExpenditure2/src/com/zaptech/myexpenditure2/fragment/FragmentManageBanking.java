package com.zaptech.myexpenditure2.fragment;

import java.util.ArrayList;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zaptech.myexpenditure2.R;
import com.zaptech.myexpenditure2.database.DBHelper;

public class FragmentManageBanking extends Fragment implements OnClickListener {
	private Button mBtnAddBankDetails;
	private Button mBtnBankHistory;
	private Button mBtnUpdateBankDetails;
	private Button mBtnRemoveBankDetails;
	private Button mBtnClearAll;
	private DBHelper dbHelper;
	private ArrayAdapter<String> adptAno;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_managebanking, container,
				false);
		init(rootView);

		return rootView;
	}

	public void init(View rootView) {
		dbHelper = new DBHelper(getActivity());
		mBtnAddBankDetails = (Button) rootView
				.findViewById(R.id.btn_addBankDetails);
		mBtnAddBankDetails.setOnClickListener(this);
		mBtnBankHistory = (Button) rootView.findViewById(R.id.btn_bankHistory);
		mBtnBankHistory.setOnClickListener(this);
		mBtnUpdateBankDetails = (Button) rootView
				.findViewById(R.id.btn_updateBankDetails);
		mBtnUpdateBankDetails.setOnClickListener(this);
		mBtnRemoveBankDetails = (Button) rootView
				.findViewById(R.id.btn_removeBank);
		mBtnRemoveBankDetails.setOnClickListener(this);
		mBtnClearAll = (Button) rootView
				.findViewById(R.id.btn_ClearAllBankHistory);
		mBtnClearAll.setOnClickListener(this);

	}

	private void updateAlert() {
		adptAno = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line,
				dbHelper.getAccountNumbers());
		LayoutInflater li = LayoutInflater.from(getActivity());
		View promptsView = li.inflate(R.layout.custom_update_bankdetails, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				getActivity());

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final Spinner userInput = (Spinner) promptsView
				.findViewById(R.id.spinAccountNumberToUpdate);
		userInput.setAdapter(adptAno);
		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text

						Fragment fUpdateBankDetails = new FragmentUpdateBankDetail();
						FragmentTransaction ft = getFragmentManager()
								.beginTransaction();

						ft.setCustomAnimations(R.anim.in_from_right_activity,
								R.anim.out_to_left_activity);

						ft.replace(R.id.main, fUpdateBankDetails);
						ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
						ft.addToBackStack(null);
						ft.commit();
						Bundle bundle = new Bundle();
						bundle.putString("BankDetail", userInput
								.getSelectedItem().toString().trim());
						fUpdateBankDetails.setArguments(bundle);

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_addBankDetails:
			Fragment fAddBankDetails = new FragmentAddBankDetails();
			FragmentTransaction ft = getFragmentManager().beginTransaction();

			ft.setCustomAnimations(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			ft.replace(R.id.main, fAddBankDetails);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.addToBackStack("FragmentManageBanking");
			ft.commit();
			break;
		case R.id.btn_bankHistory:
			Fragment fBankDetails = new FragmentBankHistory();
			FragmentTransaction ft2 = getFragmentManager().beginTransaction();

			ft2.setCustomAnimations(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);

			ft2.replace(R.id.main, fBankDetails);
			ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft2.addToBackStack("FragmentManageBanking");
			ft2.commit();
			break;
		case R.id.btn_updateBankDetails:
			updateAlert();
			break;
		case R.id.btn_removeBank:
			removeAlert();
			break;
		case R.id.btn_ClearAllBankHistory:
			clearBankHistory();
			break;
		default:
			break;
		}

	}

	private void clearBankHistory() {
		AlertDialog.Builder alertDeleteHistory = new AlertDialog.Builder(
				getActivity());
		alertDeleteHistory.setTitle("Clear Bank History");
		alertDeleteHistory
				.setMessage("Are you want to clear all the bank history ?");
		alertDeleteHistory.setCancelable(false);
		alertDeleteHistory.setPositiveButton("CLEAR ALL",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dbHelper.deletebankHistory();
						Toast.makeText(getActivity(), "Bank History cleared.",
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

	public void removeAlert() {
		ArrayList<String> arrayListBankNames = new ArrayList<String>();
		arrayListBankNames = dbHelper.getBankNames();
		LayoutInflater li = LayoutInflater.from(getActivity());
		View promptsView = li.inflate(R.layout.custom_removebank, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				getActivity());

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		// //To Initialize Spinner...
		final Spinner spinBankToRemove = (Spinner) promptsView
				.findViewById(R.id.spinnerBanksToRemove);
		ArrayAdapter<String> adptBankNames = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_dropdown_item_1line,
				arrayListBankNames);
		spinBankToRemove.setAdapter(adptBankNames);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						dbHelper.removeSpecificBank(spinBankToRemove
								.getSelectedItem().toString().trim());
						Toast.makeText(getActivity(),
								"Bank Removed Succcessfully",
								Toast.LENGTH_SHORT).show();

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
		if (arrayListBankNames.size() > 0) {
			alertDialog.show();
		} else {
			Toast.makeText(getActivity(), "No Record found to update",
					Toast.LENGTH_SHORT).show();
		}

	}
}

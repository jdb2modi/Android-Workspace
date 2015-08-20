package com.zaptech.myexpenditure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_ManageBanking extends Activity implements OnClickListener {
	private Button mBtnBankHistory;
	private Button mBtnAddBank;
	private Button mBtnUpdateBank;
	private Button mBtnRemoveBank;
	private Button mBtnClearAll;
	private Button mBtn_exit;
	private Button mBtn_back;
	private DBHelper dbHelper;
	private ArrayAdapter<String> mAdptBankName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_managebanking);
		init();
	}

	public void init() {
		mBtnBankHistory = (Button) findViewById(R.id.btn_bankHistory);
		mBtnBankHistory.setOnClickListener(this);
		mBtnAddBank = (Button) findViewById(R.id.btn_addBankDetails);
		mBtnAddBank.setOnClickListener(this);
		mBtnUpdateBank = (Button) findViewById(R.id.btn_updateBankDetails);
		mBtnUpdateBank.setOnClickListener(this);
		mBtnRemoveBank = (Button) findViewById(R.id.btn_removeBank);
		mBtnRemoveBank.setOnClickListener(this);
		mBtnClearAll = (Button) findViewById(R.id.btn_ClearAllBankHistory);
		mBtnClearAll.setOnClickListener(this);
		mBtn_exit = (Button) findViewById(R.id.btn_exitFromManageBanking);
		mBtn_exit.setOnClickListener(this);
		mBtn_back = (Button) findViewById(R.id.btn_backFromManageBanking);
		mBtn_back.setOnClickListener(this);
		dbHelper = new DBHelper(Activity_ManageBanking.this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_addBankDetails:
			finish();
			Intent intent = new Intent(Activity_ManageBanking.this,
					Activity_Banking.class);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);
			break;
		case R.id.btn_bankHistory:
			finish();
			Intent intent2 = new Intent(Activity_ManageBanking.this,
					Activity_ShowBankDetails.class);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent2);
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
		case R.id.btn_backFromManageBanking:
			back();
			break;
		case R.id.btn_exitFromManageBanking:
			exit();
			break;

		default:
			break;
		}
	}

	private void exit() {
		Toast.makeText(getApplicationContext(), "Exiting...",
				Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alert = new AlertDialog.Builder(
				Activity_ManageBanking.this);
		alert.setTitle("Exit Confirmation");
		alert.setMessage("Are you want to Close the Application ?");
		alert.setCancelable(false);
		alert.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();

			}
		});
		alert.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		alert.show();
	}

	private void back() {
		finish();
		Intent mIntent;
		mIntent = new Intent(Activity_ManageBanking.this, Activity_Home.class);
		overridePendingTransition(R.anim.in_from_left_activity,
				R.anim.out_to_right_activity);
		startActivity(mIntent);
	}

	private void clearBankHistory() {
		AlertDialog.Builder alertDeleteHistory = new AlertDialog.Builder(
				Activity_ManageBanking.this);
		alertDeleteHistory.setTitle("Clear Bank History");
		alertDeleteHistory
				.setMessage("Are you want to clear all the bank history ?");
		alertDeleteHistory.setCancelable(false);
		alertDeleteHistory.setPositiveButton("CLEAR ALL",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dbHelper.deletebankHistory();
						Toast.makeText(Activity_ManageBanking.this,
								"Bank History cleared.", Toast.LENGTH_SHORT)
								.show();
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
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.custom_update_bankdetails, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				Activity_ManageBanking.this);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.ed_accountNoUpdateBank);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						finish();
						Intent intentUpdateBankDetails = new Intent(
								Activity_ManageBanking.this,
								Activity_UpdateBankDetails.class);
						intentUpdateBankDetails.putExtra("ACCOUNTNOTOUPDATE",
								userInput.getText().toString().trim());
						overridePendingTransition(
								R.anim.in_from_right_activity,
								R.anim.out_to_left_activity);
						startActivity(intentUpdateBankDetails);
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

	public void removeAlert() {

		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.custom_removebank, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				Activity_ManageBanking.this);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final TextView txtBankToRemove = (TextView) promptsView
				.findViewById(R.id.txt_bankToRemove);

		// //To Initialize Spinner...
		final Spinner spinBankToRemove = (Spinner) promptsView
				.findViewById(R.id.spinnerBanksToRemove);
		ArrayAdapter<String> adptBankNames = new ArrayAdapter<String>(
				Activity_ManageBanking.this,
				android.R.layout.simple_dropdown_item_1line,
				dbHelper.getBankNames());
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
						Toast.makeText(Activity_ManageBanking.this,
								"Bank Removed Succcessfully",
								Toast.LENGTH_SHORT).show();
						back();
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
		if (dbHelper.getBankNames().size() > 0) {
			alertDialog.show();
		} else {
			Toast.makeText(Activity_ManageBanking.this,
					"No Record found to update", Toast.LENGTH_SHORT).show();
		}

	}

}

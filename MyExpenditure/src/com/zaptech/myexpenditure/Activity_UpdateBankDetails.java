package com.zaptech.myexpenditure;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_UpdateBankDetails extends Activity implements
		OnClickListener {
	private Button btnUpdateBankDetailsNow;
	private EditText edAccountNoToUpdate;
	private EditText edBankNameToUpdate;
	private EditText edCurrentBalanaceToUpdate;
	private String strAccountNoToUpdate;
	private DBHelper dbHelper;
	private Button mBtn_exit;
	private Button mBtn_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updatebankdetails);
		init();
		getAccountNumberToUpdate();
		setValues();

		btnUpdateBankDetailsNow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateBankDetails();
			}
		});
	}

	private void init() {

		dbHelper = new DBHelper(Activity_UpdateBankDetails.this);

		btnUpdateBankDetailsNow = (Button) findViewById(R.id.btn_updateBankDetailsNow);
		edAccountNoToUpdate = (EditText) findViewById(R.id.ed_updateaccountNumber);
		edBankNameToUpdate = (EditText) findViewById(R.id.ed_updatebankName);
		edCurrentBalanaceToUpdate = (EditText) findViewById(R.id.ed_updatecurrentBalance);
		mBtn_exit = (Button) findViewById(R.id.btn_exitFromUpdateBank);
		mBtn_exit.setOnClickListener(this);
		mBtn_back = (Button) findViewById(R.id.btn_backFromUpdateBank);
		mBtn_back.setOnClickListener(this);
		edBankNameToUpdate.setFocusable(true);
	}

	private void setValues() {
		ArrayList<String> arrayListBankDetails = new ArrayList<String>();
		arrayListBankDetails = dbHelper
				.getBankDetailsToUpdate(strAccountNoToUpdate);
		if (arrayListBankDetails.size() <= 0) {
			Toast.makeText(Activity_UpdateBankDetails.this,
					"No Record Found..!", Toast.LENGTH_SHORT).show();
			finish();
			Intent intent = new Intent(Activity_UpdateBankDetails.this,
					Activity_ManageBanking.class);
			startActivity(intent);
		} else {
			String strAccountNo = arrayListBankDetails.get(0).toString();
			String strBankName = arrayListBankDetails.get(1).toString();
			String strCurrentBalance = arrayListBankDetails.get(2).toString();
			edAccountNoToUpdate.setText(strAccountNo);
			edBankNameToUpdate.setText(strBankName);
			edCurrentBalanaceToUpdate.setText(strCurrentBalance);
		}

	}

	private void getAccountNumberToUpdate() {
		Intent intentAccountNoToUpdate = getIntent();
		strAccountNoToUpdate = intentAccountNoToUpdate
				.getStringExtra("ACCOUNTNOTOUPDATE");
	}

	private void updateBankDetails() {

		dbHelper.updateBankDetails(edAccountNoToUpdate.getText().toString(),
				edBankNameToUpdate.getText().toString(),
				edCurrentBalanaceToUpdate.getText().toString());
		Toast.makeText(Activity_UpdateBankDetails.this, "Update Successfull",
				Toast.LENGTH_SHORT).show();
		back();
	}

	private void exit() {
		Toast.makeText(getApplicationContext(), "Exiting...",
				Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alert = new AlertDialog.Builder(
				Activity_UpdateBankDetails.this);
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
		mIntent = new Intent(Activity_UpdateBankDetails.this,
				Activity_ManageBanking.class);
		overridePendingTransition(R.anim.in_from_left_activity,
				R.anim.out_to_right_activity);
		startActivity(mIntent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_backFromUpdateBank:
			back();
			break;
		case R.id.btn_exitFromUpdateBank:
			exit();
			break;
		default:
			break;
		}

	}

}

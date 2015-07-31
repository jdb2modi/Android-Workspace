package com.zaptech.myexpenditure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Banking extends Activity implements OnClickListener {

	private DBHelper dbHelper;

	private Button mBtn_exit;
	private Button mBtn_back;
	private Button mBtn_addBankDetails;
	private Button mBtn_showBankDetails;

	private EditText mEd_accountNumber;
	private EditText mEd_bankName;
	private EditText mEd_currentBalance;

	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bank_details);
		init();
		setTypeface();
	}

	public void init() {
		dbHelper = new DBHelper(Activity_Banking.this);
		sp = getSharedPreferences(MyPREFERENCES, MODE_APPEND);

		mBtn_exit = (Button) findViewById(R.id.btn_exitFromBankDetails);
		mBtn_back = (Button) findViewById(R.id.btn_backFromBankDetails);
		mBtn_addBankDetails = (Button) findViewById(R.id.btn_addBankDetailsNow);
		mBtn_showBankDetails = (Button) findViewById(R.id.btn_showBankDetails);

		mBtn_exit.setOnClickListener(this);
		mBtn_back.setOnClickListener(this);
		mBtn_addBankDetails.setOnClickListener(this);
		mBtn_showBankDetails.setOnClickListener(this);

		mEd_accountNumber = (EditText) findViewById(R.id.ed_accountNumber);
		mEd_bankName = (EditText) findViewById(R.id.ed_bankName);
		mEd_currentBalance = (EditText) findViewById(R.id.ed_currentBalance);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_exitFromBankDetails:
			exit();
			break;
		case R.id.btn_backFromBankDetails:
			back();
			break;
		case R.id.btn_addBankDetailsNow:
			mValidate();
			break;
		case R.id.btn_showBankDetails:
			finish();
			Intent intent = new Intent(Activity_Banking.this,
					Activity_ShowBankDetails.class);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	private void exit() {
		Toast.makeText(getApplicationContext(), "Exiting...",
				Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alert = new AlertDialog.Builder(
				Activity_Banking.this);
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
		mIntent = new Intent(Activity_Banking.this,
				Activity_ManageBanking.class);
		overridePendingTransition(R.anim.in_from_left_activity,
				R.anim.out_to_right_activity);
		startActivity(mIntent);
	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getAssets(),
				"fonts/Tahoma.ttf");

		mBtn_exit.setTypeface(tyFace);
		mBtn_back.setTypeface(tyFace);
		mBtn_addBankDetails.setTypeface(tyFace);
		mBtn_showBankDetails.setTypeface(tyFace);
		mEd_accountNumber.setTypeface(tyFace);
		mEd_bankName.setTypeface(tyFace);
		mEd_currentBalance.setTypeface(tyFace);

	}

	private void mValidate() {

		if (mEd_accountNumber.getText().toString().trim().length() == 0) {
			mEd_accountNumber.setFocusable(true);
			Toast.makeText(Activity_Banking.this,
					"Please, enter Account number", Toast.LENGTH_SHORT).show();
		} else if (mEd_bankName.getText().toString().trim().length() == 0) {
			mEd_bankName.setFocusable(true);
			Toast.makeText(Activity_Banking.this, "Please, enter Bank name",
					Toast.LENGTH_SHORT).show();
		} else if (mEd_currentBalance.getText().toString().trim().length() == 0) {
			mEd_currentBalance.setFocusable(true);
			Toast.makeText(Activity_Banking.this,
					"Please, enter Current balance", Toast.LENGTH_SHORT).show();
		} else {
			dbHelper.addBankDetails(mEd_accountNumber.getText().toString(),
					mEd_bankName.getText().toString(), mEd_currentBalance
							.getText().toString());
			Toast.makeText(Activity_Banking.this,
					"Bank details added successfully", Toast.LENGTH_SHORT)
					.show();
			mEd_accountNumber.setText("");
			mEd_bankName.setText("");
			mEd_currentBalance.setText("");
			mEd_accountNumber.setFocusable(true);
		}
	}
}

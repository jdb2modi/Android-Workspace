package com.zaptech.myexpenditure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity_ManageExpence extends Activity implements OnClickListener {
	private Button mBtnAddExpence;
	private Button mBtnHistory;
	private Button mBtnRemove;
	private Button mBtnClear;
	private Button mBtnUpdate;
	private Intent intent;
	private DBHelper dbHelper;
	private Button mBtn_exit;
	private Button mBtn_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_expence);
		init();
	}

	public void init() {
		mBtn_exit = (Button) findViewById(R.id.btn_exitOnManageExpence);
		mBtn_exit.setOnClickListener(this);
		mBtn_back = (Button) findViewById(R.id.btn_backOnManageExpence);
		mBtn_back.setOnClickListener(this);
		mBtnAddExpence = (Button) findViewById(R.id.btn_addExpenceOnManageExpence);
		mBtnAddExpence.setOnClickListener(this);
		mBtnHistory = (Button) findViewById(R.id.btn_expenceHistoryOnManageExpence);
		mBtnHistory.setOnClickListener(this);
		mBtnRemove = (Button) findViewById(R.id.btn_removeExpenceOnManageExpence);
		mBtnRemove.setOnClickListener(this);
		mBtnClear = (Button) findViewById(R.id.btn_removeAllExpenceOnManageExpence);
		mBtnClear.setOnClickListener(this);
		mBtnUpdate = (Button) findViewById(R.id.btn_updateExpenceOnManageExpence);
		mBtnUpdate.setOnClickListener(this);
		dbHelper = new DBHelper(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_addExpenceOnManageExpence:
			finish();
			intent = new Intent(Activity_ManageExpence.this,
					Activity_AddExpence.class);

			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);
			break;
		case R.id.btn_expenceHistoryOnManageExpence:
			finish();
			intent = new Intent(Activity_ManageExpence.this,
					Activity_ExpenceHistory.class);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);
			break;
		case R.id.btn_removeExpenceOnManageExpence:

			break;
		case R.id.btn_removeAllExpenceOnManageExpence:
			clearExpenceHistory();
			break;
		case R.id.btn_updateExpenceOnManageExpence:

			break;
		case R.id.btn_exitOnManageExpence:
			exit();
			break;
		case R.id.btn_backOnManageExpence:
			back();
			break;

		default:
			break;
		}

	}

	public void clearExpenceHistory() {
		AlertDialog.Builder alertDeleteHistory = new AlertDialog.Builder(
				Activity_ManageExpence.this);
		alertDeleteHistory.setTitle("Delete Confirmation");
		alertDeleteHistory
				.setMessage("Are you want to clear Expence history ?");
		alertDeleteHistory.setCancelable(false);
		alertDeleteHistory.setPositiveButton("DELETE NOW",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dbHelper.deleteExpenceHistory();
						Toast.makeText(Activity_ManageExpence.this,
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

	private void exit() {
		Toast.makeText(getApplicationContext(), "Exiting...",
				Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alert = new AlertDialog.Builder(
				Activity_ManageExpence.this);
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
		mIntent = new Intent(Activity_ManageExpence.this, Activity_Home.class);
		overridePendingTransition(R.anim.in_from_left_activity,
				R.anim.out_to_right_activity);
		startActivity(mIntent);
	}
}

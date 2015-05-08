package com.zaptech.moneymanager;

import java.sql.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	Button btnAddExpence, btnAddIncome, btnHistory, btnSettings;
	ImageButton imgBtnHome, imgBtnClose;
	TextView tvTotalSummary;
	Intent intent;
	DateFormat df;
	DBHelper dbHelper;
	String strDataToDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		init();
		strDataToDisplay = dbHelper.getData();
		tvTotalSummary.setText(strDataToDisplay);
	}

	public void init() {
		dbHelper = new DBHelper(HomeActivity.this);
		btnAddExpence = (Button) findViewById(R.id.btnAddExpenceHome);
		btnAddIncome = (Button) findViewById(R.id.btnAddIncomeHome);
		btnHistory = (Button) findViewById(R.id.btnHistory);
		btnSettings = (Button) findViewById(R.id.btnSettings);

		tvTotalSummary = (TextView) findViewById(R.id.tvTotalSummaryOnHome);

		imgBtnClose = (ImageButton) findViewById(R.id.imageButtonClose1);
		imgBtnHome = (ImageButton) findViewById(R.id.imageButtonHome1);

		// Adding Listeners
		btnAddExpence.setOnClickListener(this);
		btnAddIncome.setOnClickListener(this);
		btnHistory.setOnClickListener(this);
		btnSettings.setOnClickListener(this);
		imgBtnClose.setOnClickListener(this);
		imgBtnHome.setOnClickListener(this);

	}

	public void exitConfirmation() {
		AlertDialog.Builder ab = new AlertDialog.Builder(HomeActivity.this);
		ab.setTitle(getString(R.string.alertExitTitle));
		ab.setMessage(getString(R.string.alertExitMessege));
		ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				HomeActivity.this.finish();
			}
		});
		ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		ab.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAddExpenceHome:
			intent = new Intent(HomeActivity.this, AddExpence.class);
			startActivity(intent);

			break;
		case R.id.btnAddIncomeHome:
			intent = new Intent(HomeActivity.this, AddIncome.class);
			startActivity(intent);

			break;

		case R.id.btnHistory:

			intent = new Intent(HomeActivity.this, History.class);
			startActivity(intent);

			break;
		case R.id.btnSettings:

			intent = new Intent(HomeActivity.this, Settings.class);
			startActivity(intent);

			break;
		case R.id.imageButtonHome1:

			break;
		case R.id.imageButtonClose1:
			exitConfirmation();
			break;

		default:
			break;
		}
	}
}

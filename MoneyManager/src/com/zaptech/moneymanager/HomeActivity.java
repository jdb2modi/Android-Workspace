package com.zaptech.moneymanager;

import java.sql.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	Button btnAddExpence, btnAddIncome, btnHistory, btnSettings;
	ImageButton imgBtnHome, imgBtnClose, imgBtnBack;
	Intent intent;
	DateFormat df;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		init();
	}

	public void init() {
		btnAddExpence = (Button) findViewById(R.id.btnAddExpenceHome);
		btnAddIncome = (Button) findViewById(R.id.btnAddIncomeHome);
		btnHistory = (Button) findViewById(R.id.btnHistory);
		btnSettings = (Button) findViewById(R.id.btnSettings);

		imgBtnClose = (ImageButton) findViewById(R.id.imageButtonClose1);
		imgBtnHome = (ImageButton) findViewById(R.id.imageButtonHome1);
		imgBtnBack = (ImageButton) findViewById(R.id.imageButtonBack1);
		// Adding Listeners
		btnAddExpence.setOnClickListener(this);
		btnAddIncome.setOnClickListener(this);
		btnHistory.setOnClickListener(this);
		btnSettings.setOnClickListener(this);
		imgBtnClose.setOnClickListener(this);
		imgBtnHome.setOnClickListener(this);
		imgBtnBack.setOnClickListener(this);
		df = new DateFormat();
		df.getDateFormat(getApplicationContext());

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
			this.finish();
			break;
		case R.id.imageButtonBack1:
			moveTaskToBack(true);
			break;

		default:
			break;
		}
	}
}

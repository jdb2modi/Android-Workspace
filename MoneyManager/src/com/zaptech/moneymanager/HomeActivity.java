package com.zaptech.moneymanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends Activity implements OnClickListener {
	Button btnAddExpence, btnAddIncome, btnHistory, btnSettings;
	ImageButton imgBtnHome, imgBtnClose;
	TextView tvBalance, tvExpence, tvIncome, tvTotalBalanceHed,
			tvTotalIncomeHed, tvTotalExpenceHed, tvLogo;
	Intent intent;
	DateFormat df;
	DBHelper dbHelper;
	Typeface tyFace1, typeFace2;
	Settings setting;
	SharedPreferences sharedPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		init();
		displayData();
		setTypeface();
		setButtonColor();
		checkBalanceAlert();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Activity_FragmentSummary frag_summary = new Activity_FragmentSummary();

		ft.add(frag_summary, "");
	}

	public void init() {
		dbHelper = new DBHelper(HomeActivity.this);
		btnAddExpence = (Button) findViewById(R.id.btnAddExpenceHome);
		btnAddIncome = (Button) findViewById(R.id.btnAddIncomeHome);
		btnHistory = (Button) findViewById(R.id.btnHistory);
		btnSettings = (Button) findViewById(R.id.btnSettings);

		tvBalance = (TextView) findViewById(R.id.tvTotalBalance);
		tvExpence = (TextView) findViewById(R.id.tvTotalExpence);
		tvIncome = (TextView) findViewById(R.id.tvTotalIncome);
		tvTotalBalanceHed = (TextView) findViewById(R.id.tvTitleTotalBalance);
		tvTotalExpenceHed = (TextView) findViewById(R.id.tvTitleTotalExpence);
		tvTotalIncomeHed = (TextView) findViewById(R.id.tvTitleTotalIncome);
		tvLogo = (TextView) findViewById(R.id.tvLogo);

		imgBtnClose = (ImageButton) findViewById(R.id.imageButtonClose1);
		imgBtnHome = (ImageButton) findViewById(R.id.imageButtonHome1);
		setting = new Settings();

		// Adding Listeners
		btnAddExpence.setOnClickListener(this);
		btnAddIncome.setOnClickListener(this);
		btnHistory.setOnClickListener(this);
		btnSettings.setOnClickListener(this);
		imgBtnClose.setOnClickListener(this);
		imgBtnHome.setOnClickListener(this);

		sharedPref = getSharedPreferences("pref", Context.MODE_APPEND);

	}

	public void displayData() {
		tvBalance.setText(dbHelper.getBalance());
		tvExpence.setText(dbHelper.getExpence());
		tvIncome.setText(dbHelper.getIncome());
	}

	public void showBalanceAlert() {

		int temp = sharedPref.getInt("minLevel", 1);
		/*if (Integer.parseInt(String.valueOf(tvBalance.getText().toString())) <= temp) {
			AlertDialog.Builder al = new AlertDialog.Builder(this);
			al.setTitle(R.string.alertBalance);
			al.setMessage(R.string.alertBalanceMessage);
			al.show();
		}*/
	}

	public void setTypeface() {
		tyFace1 = Typeface
				.createFromAsset(getAssets(), "fonts/AgentOrange.ttf");
		tvBalance.setTypeface(tyFace1);
		tvExpence.setTypeface(tyFace1);
		tvIncome.setTypeface(tyFace1);
		tvLogo.setTypeface(tyFace1);

		typeFace2 = Typeface.createFromAsset(getAssets(), "fonts/Montague.ttf");
		tvTotalBalanceHed.setTypeface(typeFace2);
		tvTotalIncomeHed.setTypeface(typeFace2);
		tvTotalExpenceHed.setTypeface(typeFace2);

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

	public void setButtonColor() {

		btnAddExpence.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				btnAddExpence.setBackgroundColor(getResources().getColor(
						R.color.green));
				return false;
			}
		});

	}

	public void checkBalanceAlert() {
		if (sharedPref.contains("checked")) {

		} else {
			showBalanceAlert();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAddExpenceHome:
			setButtonColor();
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

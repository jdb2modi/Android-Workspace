package com.zaptech.moneymanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Settings extends Activity implements OnClickListener {
	Button btnClearExpenceHistory, btnClearIncomeHistory, btnClearAllHistory,
			btnSetMinBalance, btnSaveMinBalance;
	ImageButton imgBtnClose, imgBtnBack, imgBtnHome;
	DBHelper dbHelper;
	AlertDialog.Builder ab;
	EditText edMinBalance;
	Intent intent;
	CheckBox chk_minBalAlert;
	public SharedPreferences sharedPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		init();
		disableMinBalAlert();
	}

	public void init() {
		btnClearAllHistory = (Button) findViewById(R.id.btnClearAllHistory);
		btnClearAllHistory.setOnClickListener(this);
		btnClearExpenceHistory = (Button) findViewById(R.id.btnClearExpenceHistory);
		btnClearExpenceHistory.setOnClickListener(this);
		btnClearIncomeHistory = (Button) findViewById(R.id.btnClearIncomeHistory);
		btnClearIncomeHistory.setOnClickListener(this);
		btnSetMinBalance = (Button) findViewById(R.id.btnSetMinBalance);
		btnSetMinBalance.setOnClickListener(this);
		btnSaveMinBalance = (Button) findViewById(R.id.btnSaveMinimumBalance);
		btnSaveMinBalance.setOnClickListener(this);
		imgBtnClose = (ImageButton) findViewById(R.id.imageButtonCloseOnSettings);
		imgBtnClose.setOnClickListener(this);
		imgBtnBack = (ImageButton) findViewById(R.id.imageButtonBackOnSettings);
		imgBtnBack.setOnClickListener(this);
		imgBtnHome = (ImageButton) findViewById(R.id.imageButtonHomeOnSettings);
		imgBtnHome.setOnClickListener(this);
		edMinBalance = (EditText) findViewById(R.id.edMinimumBalance);
		dbHelper = new DBHelper(this);
		chk_minBalAlert = (CheckBox) findViewById(R.id.checkboxAlert);
		sharedPref = getSharedPreferences("pref", Context.MODE_APPEND);
	}

	public void clearAllHistory() {
		ab = new AlertDialog.Builder(this);
		ab.setTitle("Delete All History");
		ab.setMessage("Do you want to Delete all the History ?");
		ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dbHelper.clearAllHistory();
				Toast.makeText(Settings.this,
						getString(R.string.toastAllHistoryDeleted),
						Toast.LENGTH_LONG).show();
			}
		});
		ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		ab.show();
	}

	public void clearIncomeHistory() {
		ab = new AlertDialog.Builder(this);
		ab.setTitle("Delete Income History");
		ab.setMessage("Do you want to Delete Income History ?");
		ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dbHelper.clearIncomeHistory();
				Toast.makeText(Settings.this,
						getString(R.string.toastIncomeHistoryDeleted),
						Toast.LENGTH_LONG).show();
			}
		});
		ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		ab.show();
	}

	public void clearExpenceHistory() {
		ab = new AlertDialog.Builder(this);
		ab.setTitle("Delete Expence History");
		ab.setMessage("Do you want to Delete Expence History ?");
		ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dbHelper.clearExpenceHistory();
				Toast.makeText(Settings.this,
						getString(R.string.toastExpenceHistoryDeleted),
						Toast.LENGTH_LONG).show();
			}
		});
		ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		ab.show();
	}

	public void exitConfirmation() {
		AlertDialog.Builder ab = new AlertDialog.Builder(Settings.this);
		ab.setTitle(getString(R.string.alertExitTitle));
		ab.setMessage(getString(R.string.alertExitMessege));
		ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Settings.this.finish();
			}
		});
		ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		ab.show();
	}

	public void disableMinBalAlert() {
		if (sharedPref.contains("checked")) {
			chk_minBalAlert.setChecked(true);
		}
		chk_minBalAlert
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						Editor edit = sharedPref.edit();
						if (chk_minBalAlert.isChecked()) {
							edit.putString("checked", "checked");
							edit.commit();
							LayoutInflater inflater = getLayoutInflater();
							View view = inflater.inflate(
									R.layout.alert_balance, null);

							Toast toast = new Toast(Settings.this);
							toast.setView(view);
							toast.show();
							Toast.makeText(getApplicationContext(),
									"Balance Alert Disabled",
									Toast.LENGTH_SHORT).show();
						} else {
							edit.remove("checked");
							edit.commit();
						}
					}
				});

	}

	public void saveMinimumBalLevel() {

		Editor edit = sharedPref.edit();
		edit.putInt("minLevel",
				Integer.parseInt(edMinBalance.getText().toString()));
		edit.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnClearAllHistory:
			clearAllHistory();
			break;
		case R.id.btnClearIncomeHistory:
			clearIncomeHistory();
			break;
		case R.id.btnClearExpenceHistory:
			clearExpenceHistory();
			break;
		case R.id.btnSetMinBalance:
			edMinBalance.setVisibility(1);
			btnSaveMinBalance.setVisibility(1);
			break;
		case R.id.btnSaveMinimumBalance:
			saveMinimumBalLevel();
			Toast.makeText(Settings.this, getString(R.string.toastSaveMinBal),
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.imageButtonCloseOnSettings:
			exitConfirmation();
			break;
		case R.id.imageButtonBackOnSettings:
			this.finish();
			intent = new Intent(Settings.this, HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButtonHomeOnSettings:
			this.finish();
			intent = new Intent(Settings.this, HomeActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}

package com.zaptech.moneymanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Settings extends Activity implements OnClickListener {
	Button btnClearExpenceHistory, btnClearIncomeHistory, btnClearAllHistory;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		init();
	}

	public void init() {
		btnClearAllHistory = (Button) findViewById(R.id.btnClearAllHistory);
		btnClearAllHistory.setOnClickListener(this);
		btnClearExpenceHistory = (Button) findViewById(R.id.btnClearExpenceHistory);
		btnClearExpenceHistory.setOnClickListener(this);
		btnClearIncomeHistory = (Button) findViewById(R.id.btnClearIncomeHistory);
		btnClearIncomeHistory.setOnClickListener(this);
		dbHelper = new DBHelper(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnClearAllHistory:
			dbHelper.clearAllHistory();
			Toast.makeText(Settings.this,
					getString(R.string.toastAllHistoryDeleted),
					Toast.LENGTH_LONG).show();
			break;
		case R.id.btnClearIncomeHistory:
			dbHelper.clearIncomeHistory();
			Toast.makeText(Settings.this,
					getString(R.string.toastIncomeHistoryDeleted),
					Toast.LENGTH_LONG).show();
			break;
		case R.id.btnClearExpenceHistory:
			dbHelper.clearExpenceHistory();
			Toast.makeText(Settings.this,
					getString(R.string.toastExpenceHistoryDeleted),
					Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}

	}
}

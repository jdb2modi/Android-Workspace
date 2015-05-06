package com.zaptech.moneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class History extends Activity implements OnClickListener {
	ImageButton imgBtnHome, imgBtnBack;
	Button btnExpenceHistory, btnIncomeHistory;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);
		init();
	}

	public void init() {
		btnExpenceHistory = (Button) findViewById(R.id.btnExpenceHistory);
		btnExpenceHistory.setOnClickListener(this);
		btnIncomeHistory = (Button) findViewById(R.id.btnIncomeHistory);
		btnIncomeHistory.setOnClickListener(this);
		imgBtnHome = (ImageButton) findViewById(R.id.imageButtonHomeOnHistory);
		imgBtnHome.setOnClickListener(this);
		imgBtnBack = (ImageButton) findViewById(R.id.imageButtonBackOnHistory);
		imgBtnBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButtonHomeOnHistory:
			this.finish();
			intent = new Intent(History.this, HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButtonBackOnHistory:
			this.finish();
			intent = new Intent(History.this, HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.btnExpenceHistory:
			intent = new Intent(History.this, ExpenceHistory.class);
			startActivity(intent);
			break;
		case R.id.btnIncomeHistory:
			intent = new Intent(History.this,IncomeHistory.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}

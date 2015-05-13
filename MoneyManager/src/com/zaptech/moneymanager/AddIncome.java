package com.zaptech.moneymanager;

import java.sql.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddIncome extends Activity implements OnClickListener {
	EditText edTitle, edAmount, edDescription;
	Button btnAdd;
	ImageButton imgBtnClose, imgBtnBack, imgBtnHome;
	DBHelper dbHelper;
	Intent intent;
	String strDataToDisplay;
	TextView tvBalance, tvExpence, tvIncome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_income);
		init();
		displayData();

	}

	public void init() {
		edTitle = (EditText) findViewById(R.id.edIncomeTitle);
		edAmount = (EditText) findViewById(R.id.edIncomeAmount);
		edDescription = (EditText) findViewById(R.id.edIncomeDecription);
		btnAdd = (Button) findViewById(R.id.btnAddIncome);
		btnAdd.setOnClickListener(this);

		tvBalance = (TextView) findViewById(R.id.tvTotalBalanceOnIncome);
		tvExpence = (TextView) findViewById(R.id.tvTotalExpenceOnIncome);
		tvIncome = (TextView) findViewById(R.id.tvTotalIncomeOnIncome);

		imgBtnClose = (ImageButton) findViewById(R.id.imageButtonCloseOnIncome);
		imgBtnClose.setOnClickListener(this);
		imgBtnBack = (ImageButton) findViewById(R.id.imageButtonBackOnIncome);
		imgBtnBack.setOnClickListener(this);
		imgBtnHome = (ImageButton) findViewById(R.id.imageButtonHomeOnIncome);
		imgBtnHome.setOnClickListener(this);

		dbHelper = new DBHelper(this);
	}

	public void displayData() {
		tvBalance.setText(dbHelper.getBalance());
		tvExpence.setText(dbHelper.getExpence());
		tvIncome.setText(dbHelper.getIncome());
	}

	public void exitConfirmation() {
		AlertDialog.Builder ab = new AlertDialog.Builder(AddIncome.this);
		ab.setTitle(getString(R.string.alertExitTitle));
		ab.setMessage(getString(R.string.alertExitMessege));
		ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				AddIncome.this.finish();
			}
		});
		ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		ab.show();
	}

	public void clear() {
		edTitle.setText("");
		edAmount.setText("");
		edDescription.setText("");
		edTitle.requestFocus();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAddIncome:
			dbHelper.insertIncomeHistory(edTitle.getText().toString(),
					Integer.parseInt(edAmount.getText().toString()),
					edDescription.getText().toString());
			displayData();
			Toast.makeText(AddIncome.this,
					getString(R.string.toastIncomeAdded), Toast.LENGTH_SHORT)
					.show();

			break;
		case R.id.imageButtonCloseOnIncome:
			exitConfirmation();
			break;
		case R.id.imageButtonBackOnIncome:
			this.finish();
			intent = new Intent(AddIncome.this, HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButtonHomeOnIncome:
			this.finish();
			intent = new Intent(AddIncome.this, HomeActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}

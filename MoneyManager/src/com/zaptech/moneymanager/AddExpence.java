package com.zaptech.moneymanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddExpence extends Activity implements OnClickListener {
	EditText edTitle, edAmount, edDescription;
	Button btnAdd;
	TextView tvBalance, tvExpence, tvIncome;
	ImageButton imgBtnExit, imgBtnBack, imgBtnHome;
	DBHelper dbHelper;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_expence);
		init();
		displayData();
	}

	public void init() {
		edTitle = (EditText) findViewById(R.id.edExpenceTitle);
		edAmount = (EditText) findViewById(R.id.edExpenceAmount);
		edDescription = (EditText) findViewById(R.id.edExpenceDecription);
		btnAdd = (Button) findViewById(R.id.btnAddExpence);
		btnAdd.setOnClickListener(this);

		tvBalance = (TextView) findViewById(R.id.tvTotalBalanceOnExpence);
		tvExpence = (TextView) findViewById(R.id.tvTotalExpenceOnExpence);
		tvIncome = (TextView) findViewById(R.id.tvTotalIncomeOnExpence);

		imgBtnExit = (ImageButton) findViewById(R.id.imageButtonCloseOnExpence);
		imgBtnExit.setOnClickListener(this);
		imgBtnBack = (ImageButton) findViewById(R.id.imageButtonBackOnExpence);
		imgBtnBack.setOnClickListener(this);
		imgBtnHome = (ImageButton) findViewById(R.id.imageButtonHomeOnExpence);
		imgBtnHome.setOnClickListener(this);

		dbHelper = new DBHelper(this);

	}

	public void displayData() {
		tvBalance.setText(dbHelper.getBalance());
		tvExpence.setText(dbHelper.getExpence());
		tvIncome.setText(dbHelper.getIncome());
	}

	public void clear() {
		edTitle.setText("");
		edAmount.setText("");
		edDescription.setText("");

	}

	public void exitConfirmation() {
		AlertDialog.Builder ab = new AlertDialog.Builder(AddExpence.this);
		ab.setTitle(getString(R.string.alertExitTitle));
		ab.setMessage(getString(R.string.alertExitMessege));
		ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				AddExpence.this.finish();
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
		case R.id.btnAddExpence:
			dbHelper.insertExpenceHistory(edTitle.getText().toString(),
					Integer.parseInt(edAmount.getText().toString()),
					edDescription.getText().toString());
			displayData();
			Toast.makeText(AddExpence.this,
					getString(R.string.toastExpenceAdded), Toast.LENGTH_SHORT)
					.show();

			break;
		case R.id.imageButtonCloseOnExpence:
			exitConfirmation();
			break;
		case R.id.imageButtonBackOnExpence:
			this.finish();
			intent = new Intent(AddExpence.this, HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButtonHomeOnExpence:

			intent = new Intent(AddExpence.this, HomeActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}

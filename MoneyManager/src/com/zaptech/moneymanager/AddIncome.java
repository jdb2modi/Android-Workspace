package com.zaptech.moneymanager;

import java.sql.Date;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddIncome extends Activity implements OnClickListener {
	EditText edTitle, edAmount, edDescription;
	Button btnAdd;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_income);
		init();
	}

	public void init() {
		edTitle = (EditText) findViewById(R.id.edIncomeTitle);
		edAmount = (EditText) findViewById(R.id.edIncomeAmount);
		edDescription = (EditText) findViewById(R.id.edIncomeDecription);
		btnAdd = (Button) findViewById(R.id.btnAddIncome);
		btnAdd.setOnClickListener(this);
		dbHelper = new DBHelper(this);
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
			Toast.makeText(AddIncome.this,
					getString(R.string.toastIncomeAdded), Toast.LENGTH_LONG)
					.show();

			clear();
			break;

		default:
			break;
		}
	}
}

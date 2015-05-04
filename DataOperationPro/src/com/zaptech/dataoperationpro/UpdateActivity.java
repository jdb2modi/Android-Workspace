package com.zaptech.dataoperationpro;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends Activity implements OnClickListener {
	EditText edUpdateName;
	Button btnUpdateNow, btnGoBack;
	MyDatabase mdb;
	Spinner spinUpdateAge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		Intent iGet = getIntent();

		init();
		loadSpinnerData();
	}

	public void init() {
		mdb = new MyDatabase(this);

		edUpdateName = (EditText) findViewById(R.id.edUpdateName);

		btnUpdateNow = (Button) findViewById(R.id.btnUpdateNow);
		btnGoBack = (Button) findViewById(R.id.btnGoBackFromUpdate);

		btnUpdateNow.setOnClickListener(this);
		btnGoBack.setOnClickListener(this);

		spinUpdateAge = (Spinner) findViewById(R.id.spinUpdateAge);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnUpdateNow:
			mdb.updateData(edUpdateName.getText().toString(), Integer
					.parseInt(spinUpdateAge.getSelectedItem().toString()));
			Toast.makeText(getApplicationContext(), "One Raw Updated",
					Toast.LENGTH_LONG).show();
			clearData();
			break;
		case R.id.btnGoBackFromUpdate:
			this.finish();
			break;

		default:
			break;
		}

	}

	private void loadSpinnerData() {

		List<String> lables = mdb.getLabels();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, lables);

		spinUpdateAge.setAdapter(dataAdapter);
	}

	public void clearData() {
		edUpdateName.setText("");
		spinUpdateAge.setSelected(false);
	}
}

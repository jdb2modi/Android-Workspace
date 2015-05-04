package com.zaptech.dataoperationpro;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteActivity extends Activity implements OnClickListener {
	Spinner spinAge;
	Button btnDeleteNow, btnGoBack, btnDeleteAll;
	MyDatabase mdb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		Intent iGet = getIntent();
		init();
		loadSpinnerData();
	}

	public void init() {
		mdb = new MyDatabase(this);

		btnDeleteNow = (Button) findViewById(R.id.btnDeleteNow);
		btnGoBack = (Button) findViewById(R.id.btnGoBackFromDelete);
		btnDeleteAll = (Button) findViewById(R.id.btnDeleteAllNow);

		btnDeleteNow.setOnClickListener(this);
		btnGoBack.setOnClickListener(this);
		btnDeleteAll.setOnClickListener(this);

		spinAge = (Spinner) findViewById(R.id.spinDeleteAge);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btnDeleteNow:

			AlertDialog.Builder myAlert = new AlertDialog.Builder(
					DeleteActivity.this);
			myAlert.setTitle("Delete Confirmation");
			myAlert.setMessage("Are you sure, You want to delete ?");
			myAlert.setCancelable(false);
			myAlert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							mdb.deleteData(Integer.parseInt(spinAge
									.getSelectedItem().toString()));
							Toast.makeText(getApplicationContext(),
									"One Raw Deleted", Toast.LENGTH_LONG)
									.show();
							clearData();
						}
					});
			myAlert.setNegativeButton("CANCEL",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			myAlert.show();
			break;
		case R.id.btnDeleteAllNow:

			AlertDialog.Builder myAlert2 = new AlertDialog.Builder(
					DeleteActivity.this);
			myAlert2.setTitle("Delete Confirmation");
			myAlert2.setMessage("Are you sure, You want to delete ?");
			myAlert2.setCancelable(false);
			myAlert2.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							mdb.deleteAllData();
							Toast.makeText(getApplicationContext(),
									"All the Records Deleted",
									Toast.LENGTH_LONG).show();
							clearData();
						}
					});
			myAlert2.setNegativeButton("CANCEL",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			myAlert2.show();
			break;
		case R.id.btnGoBackFromDelete:
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

		spinAge.setAdapter(dataAdapter);
	}

	public void clearData() {
		spinAge.setSelected(false);
	}

}

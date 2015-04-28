package com.zaptech.databasehelperpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddUpdateAct extends Activity implements OnClickListener {
	EditText edName, edRno;
	Button btnAdd, btnDelete;
	TextView tvGoback;
	MyDatabase mdb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addupdate);
		init();
		handleIntent();
	}

	public void init() {
		mdb = new MyDatabase(this);

		edName = (EditText) findViewById(R.id.edName);
		edRno = (EditText) findViewById(R.id.edRno);
		btnAdd = (Button) findViewById(R.id.btnAdd);

		tvGoback = (TextView) findViewById(R.id.tvGoBack);

		// Adding Listener
		btnAdd.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		tvGoback.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAdd:
			String name = edName.getText().toString();
			int rno = Integer.parseInt(edRno.getText().toString());
			mdb.insertData(name, rno);
			Toast.makeText(getApplicationContext(), "Record Inserted",
					Toast.LENGTH_LONG).show();
			break;
		case R.id.btnDelete:
			String rnoDelete = edRno.getText().toString();
			mdb.delete(rnoDelete);
			Toast.makeText(getApplicationContext(), "Record Deleted",
					Toast.LENGTH_LONG).show();
			break;
		case R.id.tvGoBack:
			Intent iGoBack = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(iGoBack);

			break;

		default:
			break;
		}

	}

	public void handleIntent() {
		Intent iAddUpdate = getIntent();

	}
}

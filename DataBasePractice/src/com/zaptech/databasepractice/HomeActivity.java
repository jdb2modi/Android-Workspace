package com.zaptech.databasepractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	EditText edUsername, edPassword, edConfirmPassword, edFirstname,
			edLastname, edAge;
	Button btnRegister, btnUpdate, btnSearch, btnDisplay, btnDelete,
			btnDeleteAll;
	DBHelper myDB;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		this.deleteDatabase("DBNewone.db");
	}

	public void init() {
		edUsername = (EditText) findViewById(R.id.edUsername);
		edPassword = (EditText) findViewById(R.id.edPassword);
		edConfirmPassword = (EditText) findViewById(R.id.edConfirmPassword);
		edFirstname = (EditText) findViewById(R.id.edFirstname);
		edLastname = (EditText) findViewById(R.id.edLastname);
		edAge = (EditText) findViewById(R.id.edAge);

		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		btnDisplay = (Button) findViewById(R.id.btnDisplay);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);

		btnRegister.setOnClickListener(this);
		btnUpdate.setOnClickListener(this);
		btnSearch.setOnClickListener(this);
		btnDisplay.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		btnDeleteAll.setOnClickListener(this);

		myDB = new DBHelper(HomeActivity.this);
		this.deleteDatabase("DBNew.db");
		myDB.getWritableDatabase();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRegister:
			myDB.register(edUsername.getText().toString(), edPassword.getText()
					.toString(), edFirstname.getText().toString(), edLastname
					.getText().toString(), Integer.parseInt(edAge.getText()
					.toString()));
			Toast.makeText(HomeActivity.this, R.string.toastRegistration,
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.btnUpdate:
			myDB.update(edUsername.getText().toString(), edPassword.getText()
					.toString());
			Toast.makeText(HomeActivity.this, R.string.toastUpdate,
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.btnSearch:

			intent = new Intent(HomeActivity.this, Activity_Search.class);
			intent.putExtra("USERNAME", edUsername.getText().toString());
			startActivity(intent);
			break;
		case R.id.btnDisplay:

			intent = new Intent(HomeActivity.this, Activity_Display.class);
			startActivity(intent);
			break;
		case R.id.btnDelete:
			myDB.delete(edUsername.getText().toString(), edPassword.getText()
					.toString());
			Toast.makeText(HomeActivity.this, R.string.toastDelete,
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.btnDeleteAll:
			myDB.deleteAll();
			Toast.makeText(HomeActivity.this, R.string.toastDeleteAll,
					Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

	}

}

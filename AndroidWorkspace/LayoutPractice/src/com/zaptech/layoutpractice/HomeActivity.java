package com.zaptech.layoutpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {

	private EditText edtName;
	private EditText edtlastName;
	private EditText edtEmail;
	private EditText edtPassword;
	private EditText edtConfirmPassword;
	private EditText edtPhoneNumber;
	private Button btnSubmit;
	private Button btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
	}

	public void init() {
		edtName = (EditText) findViewById(R.id.username);
		edtlastName = (EditText) findViewById(R.id.lastName);
		edtEmail = (EditText) findViewById(R.id.emailId);
		edtPassword = (EditText) findViewById(R.id.password);
		edtConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
		edtPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
		btnSubmit = (Button) findViewById(R.id.submit);
		btnSubmit.setOnClickListener(HomeActivity.this);
		btnCancel = (Button) findViewById(R.id.cancel);
		btnCancel.setOnClickListener(HomeActivity.this);
	}

	
	
	public void submitData() {
		if (edtName.getText().toString().trim().length() < 1) {
			Toast.makeText(this, "Username can't be blank", Toast.LENGTH_SHORT)
					.show();
		} else if( edtlastName.getText().toString().trim().length() < 1) {
			Toast.makeText(this, "Lastname can't be blank", Toast.LENGTH_SHORT)
					.show();
		} else {
			Intent myIntent = new Intent(HomeActivity.this,
					DetailActivity.class);
			myIntent.putExtra("USERNAME", edtName.getText().toString());
			myIntent.putExtra("LASTNAME", edtlastName.getText().toString());
			startActivity(myIntent);

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}

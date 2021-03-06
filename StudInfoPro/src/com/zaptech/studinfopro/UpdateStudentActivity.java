package com.zaptech.studinfopro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStudentActivity extends Activity implements OnClickListener {
	Button btnUpdate, btnCancel;
	EditText edStudName, edStudRno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_college);
		init();

	}

	public void init() {
		btnUpdate = (Button) findViewById(R.id.btnUpdateStudNow);
		btnCancel = (Button) findViewById(R.id.btnUpdateStudentCancel);
		edStudName = (EditText) findViewById(R.id.edStudentNameToUpdate);
		edStudRno = (EditText) findViewById(R.id.edStudentRnoToUpdate);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnUpdateStudNow:
			Toast.makeText(getApplicationContext(), "Clicked", 250).show();
			break;
		case R.id.btnUpdateStudentCancel:
			Toast.makeText(getApplicationContext(), "Clicked", 250).show();
			break;
		default:
			break;
		}

	}

}

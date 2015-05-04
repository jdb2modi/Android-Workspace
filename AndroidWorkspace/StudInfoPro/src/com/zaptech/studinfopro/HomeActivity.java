package com.zaptech.studinfopro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	DBHelper myHelper;
	SQLiteDatabase sqLite;
	Button btnCollegeMgt, btnStudentMgt;
	Intent gotoIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	public void init() {
		myHelper = new DBHelper(this);
		btnCollegeMgt = (Button) findViewById(R.id.btnCollegePanel);
		btnStudentMgt = (Button) findViewById(R.id.btnStudentPanel);

		// Adding Listeners
		btnCollegeMgt.setOnClickListener(this);
		btnStudentMgt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnCollegePanel:
			gotoIntent = new Intent(HomeActivity.this, CollegeActivity.class);
			startActivity(gotoIntent);
			break;
		case R.id.btnStudentPanel:
			gotoIntent = new Intent(HomeActivity.this, Student_Act.class);
			startActivity(gotoIntent);
			break;

		default:
			break;
		}

	}
}

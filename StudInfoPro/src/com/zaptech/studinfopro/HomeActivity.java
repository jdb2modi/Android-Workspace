package com.zaptech.studinfopro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	DBHelper myHelper;
	SQLiteDatabase sqLite;
	Button btnCollegeMgt, btnStudentMgt;
	Intent gotoIntent;
	ImageButton imgBtnClose;

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
		imgBtnClose = (ImageButton) findViewById(R.id.imgBtnClose);

		// Adding Listeners
		btnCollegeMgt.setOnClickListener(this);
		btnStudentMgt.setOnClickListener(this);
		imgBtnClose.setOnClickListener(this);
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
		case R.id.imgBtnClose:
			closeApp();
		default:
			break;
		}

	}

	//A Function to close this Application
	public void closeApp() {
		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		ab.setTitle("Exit Confirmation");
		ab.setMessage("Do you want to close this Application ? ");
		ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}

		});
		ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		ab.show();

	}
}

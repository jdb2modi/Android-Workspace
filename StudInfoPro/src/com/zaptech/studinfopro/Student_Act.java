package com.zaptech.studinfopro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Student_Act extends Activity implements OnClickListener {
	Button btnRegisterStudent;
	Button btnUpdateStudent;
	Button btnDeleteStudent;
	Button btnSearchStudent;
	Button btnShowAllStudents;
	Button btnDeleteAllStudent;
	ImageButton btnGoBack;
	ImageButton btnHome;
	Intent mIntent;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studentlayout);
		init();
	}

	public void init() {
		btnRegisterStudent = (Button) findViewById(R.id.btnRegisterStudent);
		btnUpdateStudent = (Button) findViewById(R.id.btnUpdateStudent);
		btnDeleteStudent = (Button) findViewById(R.id.btnDeleteStudent);
		btnSearchStudent = (Button) findViewById(R.id.btnSearchStudent);
		btnDeleteAllStudent = (Button) findViewById(R.id.btnDeleteAllStudents);
		btnShowAllStudents = (Button) findViewById(R.id.btnShowAllStudents);
		btnGoBack = (ImageButton) findViewById(R.id.gobackFromStudent);
		btnHome = (ImageButton) findViewById(R.id.goHomeFromStudent);
		dbHelper = new DBHelper(this);

		// Adding Listeners;
		btnRegisterStudent.setOnClickListener(this);
		btnUpdateStudent.setOnClickListener(this);
		btnDeleteStudent.setOnClickListener(this);
		btnSearchStudent.setOnClickListener(this);
		btnDeleteAllStudent.setOnClickListener(this);
		btnShowAllStudents.setOnClickListener(this);
		btnGoBack.setOnClickListener(this);
		btnHome.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRegisterStudent:
			mIntent = new Intent(Student_Act.this, AddStudentActivity.class);
			startActivity(mIntent);
			break;
		case R.id.btnUpdateStudent:
			mIntent = new Intent(Student_Act.this, UpdateStudentActivity.class);
			startActivity(mIntent);
			break;
		case R.id.btnDeleteStudent:
			mIntent = new Intent(Student_Act.this, DeleteStudentActivity.class);
			startActivity(mIntent);
			break;
		case R.id.btnDeleteAllStudents:
			AlertDialog.Builder alertDeleteAll = new AlertDialog.Builder(this);
			alertDeleteAll.setTitle(getResources().getString(
					R.string.alertDeleteAllTitle));
			alertDeleteAll.setMessage(getResources().getString(
					R.string.alertDeleteAllMessage));
			alertDeleteAll.setPositiveButton("YES",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dbHelper.deleteAllStudentData();
							Toast.makeText(
									Student_Act.this,
									getResources().getString(
											R.string.alertDeleteSuccessful),
									Toast.LENGTH_SHORT).show();
						}
					});
			alertDeleteAll.setNegativeButton("NO",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							Toast.makeText(
									Student_Act.this,
									getResources().getString(
											R.string.alertDeleteCancel),
									Toast.LENGTH_SHORT).show();
						}
					});
			alertDeleteAll.show();

			break;
		case R.id.btnSearchStudent:
			mIntent = new Intent(Student_Act.this, SearchStudentActivity.class);
			startActivity(mIntent);
			break;
		case R.id.btnShowAllStudents:
			mIntent = new Intent(Student_Act.this, DisplayStudentActivity.class);
			startActivity(mIntent);
			break;
		case R.id.gobackFromStudent:
			this.finish();
			break;
		case R.id.goHomeFromStudent:
			this.finish();
			break;

		default:
			break;
		}
	}
}

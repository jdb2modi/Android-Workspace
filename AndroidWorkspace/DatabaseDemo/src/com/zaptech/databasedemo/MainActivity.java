package com.zaptech.databasedemo;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	SQLiteDatabase db;
	EditText edName, edLastName, edEmail;
	Button btnAdd, btnDelete, btnModify, btnView, btnViewAll, btnShowInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		dbCreate();
	}

	public void init() {
		edName = (EditText) findViewById(R.id.edName);
		edLastName = (EditText) findViewById(R.id.edLastName);
		edEmail = (EditText) findViewById(R.id.edEmail);

		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnModify = (Button) findViewById(R.id.btnModify);
		btnView = (Button) findViewById(R.id.btnView);
		btnViewAll = (Button) findViewById(R.id.btnViewAll);
		btnShowInfo = (Button) findViewById(R.id.btnInfo);
		
		btnAdd.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		btnModify.setOnClickListener(this);
		btnView.setOnClickListener(this);
		btnViewAll.setOnClickListener(this);
		btnShowInfo.setOnClickListener(this);
	}

	// /Database Related Functions...
	public void dbCreate() {
		db = openOrCreateDatabase("studentDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR,lastname VARCHAR,email VARCHAR);");
		Toast.makeText(getApplicationContext(), "Database has been created",
				Toast.LENGTH_LONG).show();
	}

	public void dbInsert() {
		db.execSQL("INSERT INTO student VALUES('" + edName.getText() + "','"
				+ edLastName.getText() + "','" + edEmail.getText() + "');");
		Toast.makeText(getApplicationContext(), "Record has been inserted",
				Toast.LENGTH_LONG).show();
	}

	public void dbDelete() {
		db.execSQL("DELETE FROM student WHERE email='" + edEmail.getText()
				+ "'");
	}

	public void dbUpdate() {
		db.execSQL("UPDATE student SET name='" + edName.getText()
				+ "',lastname='" + edLastName.getText() + "' WHERE email='"
				+ edEmail.getText() + "'");
	}

	public void dbView() {
		Cursor c = db
				.rawQuery(
						"SELECT * FROM student WHERE email='"
								+ edEmail.getText() + "'", null);
		if (c.moveToFirst()) {
			edName.setText(c.getString(0));
			edLastName.setText(c.getString(1));
		}
	}

	public void viewAll() {
		Cursor c = db.rawQuery("SELECT * FROM student", null);
		if (c.getCount() == 0) {
			Toast.makeText(getApplicationContext(), "No Record Found",
					Toast.LENGTH_LONG).show();
			return;
		}
		StringBuffer buffer = new StringBuffer();
		while (c.moveToNext()) {
			buffer.append("Name: " + c.getString(0) + "\n");
			buffer.append("Lastname: " + c.getString(1) + "\n");
			buffer.append("Email: " + c.getString(2) + "\n\n");
		}
		 showMessage("Student Details", buffer.toString());
	}
	public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btnAdd:
			dbInsert();
			break;
		case R.id.btnDelete:
			dbDelete();
			break;

		case R.id.btnView:
			dbView();
			break;

		case R.id.btnViewAll:
			viewAll();
			break;

		case R.id.btnModify:
			dbUpdate();
			break;

		case R.id.btnInfo:

			break;

		default:
			break;
		}

	}

}

package com.zaptech.dataoperationpro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button btnAdd, btnUpdate, btnView, btnViewAll, btnDelete, btnDeleteAll;
	EditText edName, edAge;
	MyDatabase mdb;
	WebView myWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();

	}

	public void init() {
		mdb = new MyDatabase(this);

		edName = (EditText) findViewById(R.id.edName);
		edAge = (EditText) findViewById(R.id.edAge);

		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
		btnView = (Button) findViewById(R.id.btnView);
		btnViewAll = (Button) findViewById(R.id.btnViewAll);
		myWebView = (WebView) findViewById(R.id.myWebview);

		btnAdd.setOnClickListener(this);
		btnUpdate.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		btnDeleteAll.setOnClickListener(this);
		btnView.setOnClickListener(this);
		btnViewAll.setOnClickListener(this);
		myWebView.loadUrl("file:///android_asset/Demo.html");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAdd:
			mdb.insertData(edName.getText().toString(),
					Integer.parseInt(edAge.getText().toString()));

			Toast.makeText(getApplicationContext(), "One Row Inserted",
					Toast.LENGTH_LONG).show();
			clearData();
			break;
		case R.id.btnUpdate:
			Intent iUpdate = new Intent(getApplicationContext(),
					UpdateActivity.class);
			startActivity(iUpdate);
			break;
		case R.id.btnDelete:
			Intent iDelete = new Intent(getApplicationContext(),
					DeleteActivity.class);
			startActivity(iDelete);
			break;
		case R.id.btnDeleteAll:
			AlertDialog.Builder deleteAllDialog = new AlertDialog.Builder(
					MainActivity.this);
			deleteAllDialog.setTitle("Delete All Data");
			deleteAllDialog
					.setMessage("Want to delete All the data from database ? ");
			deleteAllDialog.setCancelable(false);
			deleteAllDialog.setPositiveButton("OK",
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
			deleteAllDialog.setNegativeButton("CANCEL",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			deleteAllDialog.show();
			break;
		case R.id.btnView:
			Intent iView = new Intent(getApplicationContext(),
					ViewActivity.class);
			startActivity(iView);
			break;
		case R.id.btnViewAll:
			Intent iViewAll = new Intent(getApplicationContext(),
					ViewAllActivity.class);
			startActivity(iViewAll);
			break;

		default:
			break;
		}

	}

	public void clearData() {
		edName.setText("");
		edAge.setText("");
	}

}

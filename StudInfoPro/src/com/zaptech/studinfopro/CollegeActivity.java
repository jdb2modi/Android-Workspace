package com.zaptech.studinfopro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class CollegeActivity extends Activity implements OnClickListener {
	Button btnAddCollege;
	Button btnUpdateCollege;
	Button btnDeleteCollege;
	Button btnSearchCollege;
	Button btnShowAllColleges;
	Button btnDeleteAllColleges;
	ImageButton btnGoBack;
	ImageButton btnHome;
	Intent mIntent;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_college);
		init();
	}

	public void init() {
		btnAddCollege = (Button) findViewById(R.id.btnAddCollege);
		btnUpdateCollege = (Button) findViewById(R.id.btnUpdateCollege);
		btnDeleteCollege = (Button) findViewById(R.id.btnDeleteCollege);
		btnSearchCollege = (Button) findViewById(R.id.btnSearchCollege);
		btnDeleteAllColleges = (Button) findViewById(R.id.btnDeleteAllColleges);
		btnShowAllColleges = (Button) findViewById(R.id.btnShowAllColleges);
		btnGoBack = (ImageButton) findViewById(R.id.gobackFromCollege);
		btnHome = (ImageButton) findViewById(R.id.goHomeFromCollege);
		dbHelper = new DBHelper(this);

		// Adding Listeners;
		btnAddCollege.setOnClickListener(this);
		btnUpdateCollege.setOnClickListener(this);
		btnDeleteCollege.setOnClickListener(this);
		btnSearchCollege.setOnClickListener(this);
		btnDeleteAllColleges.setOnClickListener(this);
		btnShowAllColleges.setOnClickListener(this);
		btnGoBack.setOnClickListener(this);
		btnHome.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAddCollege:
			mIntent = new Intent(CollegeActivity.this, AddCollegeActivity.class);
			startActivity(mIntent);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			break;
		case R.id.btnUpdateCollege:
			mIntent = new Intent(CollegeActivity.this,
					UpdateCollegeActivity.class);
			startActivity(mIntent);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			break;
		case R.id.btnDeleteCollege:
			mIntent = new Intent(CollegeActivity.this,
					DeleteCollegeActivity.class);
			startActivity(mIntent);overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			break;
		case R.id.btnSearchCollege:
			mIntent = new Intent(CollegeActivity.this,
					SearchCollegeActivity.class);
			startActivity(mIntent);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			break;
		case R.id.btnDeleteAllColleges:
			deleteAllColleges();
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);

			break;
		case R.id.btnShowAllColleges:
			mIntent = new Intent(CollegeActivity.this,
					DisplayCollegeActivity.class);
			startActivity(mIntent);
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			break;
		case R.id.gobackFromCollege:
			this.finish();
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			break;
		case R.id.goHomeFromCollege:
			this.finish();
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			break;

		default:
			break;
		}
	}

	public void deleteAllColleges() {
		AlertDialog.Builder alertDeleteAll = new AlertDialog.Builder(this);
		alertDeleteAll.setTitle(getResources().getString(
				R.string.alertDeleteAllTitle));
		alertDeleteAll.setMessage(getResources().getString(
				R.string.alertDeleteAllMessage));
		alertDeleteAll.setPositiveButton("YES",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dbHelper.deleteAllCollegeData();
						Toast.makeText(
								CollegeActivity.this,
								getResources().getString(
										R.string.alertDeleteSuccessful),
								Toast.LENGTH_SHORT).show();
					}
				});
		alertDeleteAll.setNegativeButton("NO",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Toast.makeText(
								CollegeActivity.this,
								getResources().getString(
										R.string.alertDeleteCancel),
								Toast.LENGTH_SHORT).show();
					}
				});
		alertDeleteAll.show();
	}
}

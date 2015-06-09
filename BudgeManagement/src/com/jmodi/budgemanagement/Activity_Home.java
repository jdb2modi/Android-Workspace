package com.jmodi.budgemanagement;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Home extends Activity implements OnClickListener {
	Button btn_Expences, btn_Incomes, btn_Settings, btn_Overview, btn_History;
	TextView txt_HomeHeader;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		setTypeface();
	}

	public void init() {
		btn_Expences = (Button) findViewById(R.id.btn_Expences);
		btn_Incomes = (Button) findViewById(R.id.btn_Incomes);
		btn_Settings = (Button) findViewById(R.id.btn_Settings);
		btn_History = (Button) findViewById(R.id.btn_History);
		btn_Overview = (Button) findViewById(R.id.btn_Overview);
		// Adding Listeners
		btn_Expences.setOnClickListener(this);
		btn_Incomes.setOnClickListener(this);
		btn_Settings.setOnClickListener(this);
		btn_History.setOnClickListener(this);
		btn_Overview.setOnClickListener(this);

		txt_HomeHeader = (TextView) findViewById(R.id.txt_HomeHeader);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_Overview:
			finish();
			intent = new Intent(Activity_Home.this, Activity_Overview.class);
			startActivity(intent);
			break;
		case R.id.btn_Expences:
			finish();
			intent = new Intent(Activity_Home.this, Activity_Expences.class);
			startActivity(intent);
			break;
		case R.id.btn_Incomes:
			finish();
			intent = new Intent(Activity_Home.this, Activity_Incomes.class);
			startActivity(intent);
			break;
		case R.id.btn_Settings:
			finish();
			intent = new Intent(Activity_Home.this, Activity_Settings.class);
			startActivity(intent);
			break;
		case R.id.btn_History:
			finish();
			intent = new Intent(Activity_Home.this, Activity_History.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getAssets(),
				"fonts/Tahoma.ttf");
		txt_HomeHeader.setTypeface(tyFace);
		btn_Expences.setTypeface(tyFace);
		btn_Incomes.setTypeface(tyFace);
		btn_Settings.setTypeface(tyFace);
		btn_Overview.setTypeface(tyFace);
		btn_History.setTypeface(tyFace);

	}
}

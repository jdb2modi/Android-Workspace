package com.zaptech.taskpasedjson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_NewsItemDetail extends ActionBarActivity implements
		OnClickListener {
	TextView txt_newsDescription;
	Button btn_back;
	DBHelper dbHelper;
	Model_NewsItem_Items model_newsItem_Item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_item_detail);
		init();
		Intent intent = getIntent();
		int strTemp = intent.getIntExtra("HeadLineId", 0);
		Toast.makeText(getApplicationContext(), "HeadLineId" + strTemp,
				Toast.LENGTH_LONG).show();
		model_newsItem_Item=dbHelper.getNewsDetails(strTemp);
		txt_newsDescription.setText(model_newsItem_Item.getModel_description().getTheString());
	}

	public void init() {
		txt_newsDescription = (TextView) findViewById(R.id.txt_newsDescription);
		btn_back = (Button) findViewById(R.id.btn_BackFromNewsItemDetails);
		btn_back.setOnClickListener(this);
		dbHelper = new DBHelper(Activity_NewsItemDetail.this);
		model_newsItem_Item = new Model_NewsItem_Items();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_BackFromNewsItemDetails:
			finish();
			Intent intent = new Intent(Activity_NewsItemDetail.this,
					Activity_NewsItems.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

}

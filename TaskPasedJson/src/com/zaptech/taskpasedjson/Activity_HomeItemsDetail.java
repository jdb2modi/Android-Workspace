package com.zaptech.taskpasedjson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zaptech.taskpasedjson.database.DBHelper;

public class Activity_HomeItemsDetail extends Activity {
	ImageView img_HomeItemDetail;
	TextView txt_title, txt_text, txt_textHtml, txt_header;
	Button btn_back;
	DBHelper dbHelper;
	String strTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_items_detail);
		Intent intent = getIntent();
		strTitle = intent.getStringExtra("TITLE");
		init();
		dbHelper.displayHomeItems();
		displayData();
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(Activity_HomeItemsDetail.this,
						Activity_HomeItems.class);
				startActivity(intent);
			}
		});
	}

	public void init() {
		img_HomeItemDetail = (ImageView) findViewById(R.id.img_HomeItemDetail);
		txt_title = (TextView) findViewById(R.id.txt_title);
		txt_text = (TextView) findViewById(R.id.txt_text);
		txt_textHtml = (TextView) findViewById(R.id.txt_textHtml);
		txt_header = (TextView) findViewById(R.id.txt_headerHomeItemDetail);
		btn_back = (Button) findViewById(R.id.btn_BackFromHomeItemDetail);

		dbHelper = new DBHelper(this);
	}

	public void displayData() {
		int length = dbHelper.arrayListHomeItems.size();
		for (int i = 0; i < length; i++) {
			if (dbHelper.arrayListHomeItems.get(i).getHomeItem_title()
					.equals(strTitle)) {
				txt_header.setText(dbHelper.arrayListHomeItems.get(i)
						.getHomeItem_title());
				txt_title.setText(dbHelper.arrayListHomeItems.get(i)
						.getHomeItem_title());
				txt_text.setText(dbHelper.arrayListHomeItems.get(i)
						.getHomeItem_text());
				txt_textHtml.setText(Html.fromHtml(
						dbHelper.arrayListHomeItems.get(i)
								.getHomeItem_textHTML()).toString());
			}
		}
	}
}

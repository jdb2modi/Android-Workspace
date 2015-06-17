package com.zaptech.taskpasedjson;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_HomeItemsDetail extends Activity {
	ImageView img_HomeItemDetail;
	TextView txt_title, txt_text, txt_textHtml;
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
	}

	public void init() {
		img_HomeItemDetail = (ImageView) findViewById(R.id.img_HomeItemDetail);
		txt_title = (TextView) findViewById(R.id.txt_title);
		txt_text = (TextView) findViewById(R.id.txt_text);
		txt_textHtml = (TextView) findViewById(R.id.txt_textHtml);

		dbHelper = new DBHelper(this);
	}

	public void displayData() {
		int length = dbHelper.arrayListHomeItems.size();
		for (int i = 0; i < length; i++) {
			if (dbHelper.arrayListHomeItems.get(i).getHomeItem_title()
					.equals(strTitle)) {
				txt_title.setText(dbHelper.arrayListHomeItems.get(i)
						.getHomeItem_title());
				txt_text.setText(dbHelper.arrayListHomeItems.get(i)
						.getHomeItem_text());
				txt_textHtml.setText(Html.fromHtml(dbHelper.arrayListHomeItems.get(i)
						.getHomeItem_textHTML()).toString());
			}
		}
	}
}

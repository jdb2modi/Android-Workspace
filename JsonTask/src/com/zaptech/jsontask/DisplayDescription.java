package com.zaptech.jsontask;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class DisplayDescription extends Activity {

	Bundle bundle;

	int id;

	MyDatabaseHelper myData;

	ArrayList<NewsDescriptionHTML> list_DescriptionHTML;

	NewsDescriptionHTML newsDescriptionHTML;

	TextView txt_DescriptionHTML;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_description);
		init();
		id = bundle.getInt("id");
		displayDescription();
	}

	public void displayDescription() {
		list_DescriptionHTML = myData.displayDescriptionHTML(id);
		int length = list_DescriptionHTML.size();
		for (int i = 0; i < length; i++) {
			txt_DescriptionHTML.setText(Html.fromHtml(list_DescriptionHTML
					.get(i).getTheString().toString()));
		}
	}

	public void init() {
		bundle = getIntent().getExtras();

		myData = new MyDatabaseHelper(DisplayDescription.this);

		list_DescriptionHTML = new ArrayList<NewsDescriptionHTML>();

		newsDescriptionHTML = new NewsDescriptionHTML();

		txt_DescriptionHTML = (TextView) findViewById(R.id.textViewDescriptionHTML);
	}
}

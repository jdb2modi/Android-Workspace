package com.zaptech.jsontask;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayFirstTitle extends Activity {

	String Str_Title, Str_Id;

	Bundle bundle;

	TextView txt_Data, txt_Text, txt_TextHTML;

	ListView list_HTML;

	MyDatabaseHelper myData;

	HomeItems modelHomeItems;

	LayoutInflater inflater;

	ArrayList<HomeItems> list_HomeTextHTML;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_first_title);

		init();

		bundle = getIntent().getExtras();
		Str_Title = bundle.getString("title");
		Str_Id = bundle.getString("id");

		displayDetail();

	}

	public void init() {
		txt_Data = (TextView) findViewById(R.id.textViewData);

		txt_Text = (TextView) findViewById(R.id.textViewText);

		txt_TextHTML = (TextView) findViewById(R.id.textViewTextHTML);

		myData = new MyDatabaseHelper(DisplayFirstTitle.this);

		modelHomeItems = new HomeItems();
	}

	public void displayDetail() {
		list_HomeTextHTML = myData.displayHomeItem(Str_Id);
		int length = list_HomeTextHTML.size();
		for (int i = 0; i < length; i++) {
			txt_Data.setText(list_HomeTextHTML.get(i).getHomeItem_title()
					.toString());
			txt_Text.setText(list_HomeTextHTML.get(i).getHomeItem_text()
					.toString());
			txt_TextHTML.setText(Html.fromHtml(list_HomeTextHTML.get(i)
					.getHomeItem_textHTML().toString()));
		}
	}
}

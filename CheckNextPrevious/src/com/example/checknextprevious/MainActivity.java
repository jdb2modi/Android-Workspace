package com.example.checknextprevious;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	ListView mList;
	Button btn1, btn2;
	ArrayList<String> arrayList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		btn1.setVisibility(View.INVISIBLE);
		btn2.setVisibility(View.INVISIBLE);
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("3");
		arrayList.add("4");
		arrayList.add("5");
		arrayList.add("6");
		arrayList.add("7");
		arrayList.add("8");
		arrayList.add("9");
		arrayList.add("10");
		arrayList.add("11");
		arrayList.add("12");

		ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_dropdown_item_1line,
				arrayList);
		mList.setAdapter(arrayAdpt);
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				btn1.setVisibility(View.INVISIBLE);
				btn2.setVisibility(View.INVISIBLE);
				if (position > 0) {
					btn1.setVisibility(View.VISIBLE);
					btn2.setVisibility(View.INVISIBLE);
				}
				if (position < arrayList.size()) {
					btn2.setVisibility(View.VISIBLE);

				}
				if (position == arrayList.size() - 1) {
					btn2.setVisibility(View.INVISIBLE);
				}

			}
		});
	}

	public void init() {
		mList = (ListView) findViewById(R.id.listcheck);
		btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(this);
		btn2 = (Button) findViewById(R.id.btn2);
		btn2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:

			break;
		case R.id.btn2:

			break;
		default:
			break;
		}

	}

}

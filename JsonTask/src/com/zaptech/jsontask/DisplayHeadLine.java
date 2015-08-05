package com.zaptech.jsontask;

import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayHeadLine extends Activity implements OnItemClickListener {

	ListView list_Data;

	int id;

	Bundle bundle;

	ArrayList<NewsHeadLine> list_HeadLine;

	LayoutInflater inflater;

	MyDatabaseHelper myData;

	NewsHeadLine newsHeadLine;

	customAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_head_line);
		init();
		id = bundle.getInt("id");
		System.err.println("Id------------------------>" + id);
		list_HeadLine = myData.displayHeadLine(id);
		adapter = new customAdapter(DisplayHeadLine.this, list_HeadLine);
		list_Data.setAdapter(adapter);
		list_Data.setOnItemClickListener(this);
	}

	public void init() {
		list_Data = (ListView) findViewById(R.id.listViewTitle);

		bundle = getIntent().getExtras();

		list_HeadLine = new ArrayList<NewsHeadLine>();

		myData = new MyDatabaseHelper(DisplayHeadLine.this);

		try {
			myData.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}

		newsHeadLine = new NewsHeadLine();
	}

	class customAdapter extends BaseAdapter {

		Context context;

		ArrayList<NewsHeadLine> array_List;

		public customAdapter(Context context, ArrayList<NewsHeadLine> array_List) {
			this.context = context;
			this.array_List = array_List;
		}

		@Override
		public int getCount() {
			return array_List.size();
		}

		@Override
		public Object getItem(int position) {
			return array_List.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (inflater == null) {
				inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.list_headline, null);
			}
			TextView txt_HeadLine = (TextView) convertView
					.findViewById(R.id.textViewHeadLine);
			TextView txt_HeadLineId = (TextView) convertView
					.findViewById(R.id.textViewHeadLineId);
			txt_HeadLine.setText(array_List.get(position).getTheString());
			txt_HeadLineId.setText(String.valueOf(array_List.get(position)
					.getItemId()));
			return convertView;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		newsHeadLine = (NewsHeadLine) adapter.getItem(position);
		switch (position) {
		case 0:
			Intent first = new Intent(DisplayHeadLine.this,
					DisplayDescription.class);
			first.putExtra("id", newsHeadLine.getItemId());
			startActivity(first);
			break;

		case 1:
			Intent second = new Intent(DisplayHeadLine.this,
					DisplayDescription.class);
			second.putExtra("id", newsHeadLine.getItemId());
			startActivity(second);
			break;

		case 2:
			Intent third = new Intent(DisplayHeadLine.this,
					DisplayDescription.class);
			startActivity(third);
			break;

		default:
			break;
		}

	}

}

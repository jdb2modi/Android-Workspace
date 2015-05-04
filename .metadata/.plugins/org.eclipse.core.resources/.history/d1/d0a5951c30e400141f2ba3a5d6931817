package com.zaptech.dataoperationpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyCustomAdapterTwo extends BaseAdapter {

	Context mcontextNew;
	ViewAllActivity va;

	public MyCustomAdapterTwo(Context context) {
		this.mcontextNew = context;
	}

	@Override
	public int getCount() {
		va = new ViewAllActivity();
		return va.dataList.size();
	}

	@Override
	public Object getItem(int position) {
		va = new ViewAllActivity();
		return va.dataList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		va = new ViewAllActivity();
		va.getData();
		if (ViewAllActivity.inflater == null)
			ViewAllActivity.inflater = (LayoutInflater) mcontextNew
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null)
			convertView = ViewAllActivity.inflater.inflate(
					R.layout.list_item_raw, null);

		TextView name = (TextView) convertView.findViewById(R.id.tvName);
		TextView age = (TextView) convertView.findViewById(R.id.tvAge);

		name.setText(va.dataList.get(position).getStrName());
		age.setText(String.valueOf(va.dataList.get(position).getAge()));

		return convertView;

	}

}

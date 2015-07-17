package com.zaptech.gridviewdemo;

import com.zaptech.gridviewdemo.R.color;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Custom_Grid extends BaseAdapter {
	private Context mContext;
	private String web[];
	private int images[];

	public Custom_Grid(Context mContext, String[] web, int[] images) {
		this.mContext = mContext;
		this.web = web;
		this.images = images;
	}

	@Override
	public int getCount() {

		return web.length;
	}

	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.grid_single, null);
		}
		TextView txtWeb;
		ImageView imgWeb;
		txtWeb = (TextView) convertView.findViewById(R.id.grid_text);
		imgWeb = (ImageView) convertView.findViewById(R.id.grid_image);
		txtWeb.setText(web[position]);
		imgWeb.setImageResource(images[position]);
		
		//convertView.setBackgroundColor(Color.BLUE);
		return convertView;
	}

}

package com.example.quizapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quizapplication.model.ImageLoader;
import com.example.quizapplication.model.Model;

public class RoundResult extends Activity {
	ListView listview;
	TextView tv_answer;
	ImageView img_user,img_opponent;
	LayoutInflater inflater;
	ArrayList<Model> record;
	Model model;
	
	String userName,oppPhoto,userPhoto;
	 SharedPreferences preferences;
		public ImageLoader imageLoader;
		String Url = "http://122.170.97.189:81/iphonequiz/user_photos/";

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.roundresult);
		listview = (ListView) findViewById(R.id.roundresult_listVw);
		record = new ArrayList<Model>();
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(RoundResult.this);
		
		preferences = PreferenceManager
				.getDefaultSharedPreferences(RoundResult.this);
		userName = preferences.getString("u_name_pref", "");
		oppPhoto = preferences.getString("opp_Pic_pref", "");
		userPhoto = preferences.getString("u_photo_Pref", "");
		
		listview.setAdapter(new SmplListAdaper());

		model = new Model();
		model.setAnswer("Apple");

		record.add(model);

		model = new Model();
		model.setAnswer("Orange");

		record.add(model);

		model = new Model();
		model.setAnswer("Grapes");

		record.add(model);
		model = new Model();
		model.setAnswer("Guava");

		record.add(model);
	}

	public class SmplListAdaper extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return record.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub

			view = inflater.inflate(R.layout.roundresult_list_row, null);

			tv_answer = (TextView) view
					.findViewById(R.id.result_listrow_tv_answer);
			// imagV_image = (ImageView) view
			// .findViewById(R.id.result_listrow_image);
			img_user = (ImageView) view.findViewById(R.id.result_listrow_imageLeft);
			img_opponent = (ImageView) view.findViewById(R.id.result_listrow_imageRight);
			

			tv_answer.setText(record.get(position).getAnswer());
			/*
			imageLoader.DisplayImage(oppPhoto,ImgUserPhoto);
			imageLoader.DisplayImage(Url + oppPhoto, ImgOppPhoto);*/
			// imagV_image.setText(record.get(position).getImage());
			imageLoader.DisplayImage(Url + userPhoto, img_opponent);
			imageLoader.DisplayImage(Url + oppPhoto, img_user);
			return view;
		}

	}
}

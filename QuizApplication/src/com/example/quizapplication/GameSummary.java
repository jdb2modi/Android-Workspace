package com.example.quizapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quizapplication.model.Model;

public class GameSummary extends Activity {

	ListView listview;
	TextView tvName;
	LayoutInflater inflater;
	ArrayList<Model> gameSumm_player;
	Model model;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gamesummary);
		listview = (ListView) findViewById(R.id.gameSummary_listVw);
		gameSumm_player = new ArrayList<Model>();
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		listview.setAdapter(new SmplListAdaper());

		model = new Model();
		model.setName("Dhara");
		gameSumm_player.add(model);

		model = new Model();
		model.setName("rutu");
		gameSumm_player.add(model);
		model = new Model();
		model.setName("Shweta");
		gameSumm_player.add(model);
		model = new Model();
		model.setName("neha");
		gameSumm_player.add(model);
	}

	public class SmplListAdaper extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return gameSumm_player.size();
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

			view = inflater.inflate(R.layout.game_summary_list_row, null);

			TextView tvName = (TextView) view
					.findViewById(R.id.gameSummary_listrow_textView);

			tvName.setText(gameSumm_player.get(position).getName());
			return view;
		}

	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.gameSummary_btnshop:
			Intent i_shop = new Intent(GameSummary.this, Shop.class);
			startActivity(i_shop);
			break;
		case R.id.gameSummary_btnNext:
			Intent i_next = new Intent(GameSummary.this, RoundResult.class);
			startActivity(i_next);
			break;

		}
	}
}

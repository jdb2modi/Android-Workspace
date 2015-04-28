package com.zaptech.listviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView gameView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initi();
	}
	public void initi(){
		gameView=(ListView)findViewById(R.id.games);
		ArrayAdapter<String> adpt=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.game));
		gameView.setAdapter(adpt);
		gameView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Toast.makeText(getApplicationContext(),""+String.valueOf(gameView.getSelectedItem()),Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Please Select an Item",Toast.LENGTH_LONG).show();
			}
		});
	}
}

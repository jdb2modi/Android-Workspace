package com.zaptech.gridviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class Activity_Home extends Activity {
	GridView grid;
	String strWeb[] = { "winner1", "winner2", "winner3", "winner4", "winner5",
			"winner6" };
	int images[] = { R.drawable.s1, R.drawable.s2, R.drawable.s3,
			R.drawable.s4, R.drawable.s5, R.drawable.s6 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		Custom_Grid adapter = new Custom_Grid(Activity_Home.this, strWeb,
				images);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(Activity_Home.this,
						"You Clicked at " + strWeb[+position],
						Toast.LENGTH_SHORT).show();
				
				grid.setItemChecked(position, true);
			}
		});
	}

	public void init() {
		grid = (GridView) findViewById(R.id.grid);

	}
}

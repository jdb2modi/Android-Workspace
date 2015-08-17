package com.zaptech.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

		FruitModel fruitsData[] = { new FruitModel("Apple"),
				new FruitModel("Banana"), new FruitModel("Orange"),
				new FruitModel("Pineapple"), new FruitModel("Mango"),
				new FruitModel("Watermelon"), new FruitModel("Strawberry"),
				new FruitModel("Grapes"), new FruitModel("Jackfruit"),
				new FruitModel("Carrot"), new FruitModel("Fig") };

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		MyAdapter mAdapter = new MyAdapter(fruitsData);

		recyclerView.setAdapter(mAdapter);

		recyclerView.setItemAnimator(new DefaultItemAnimator());

	}
}
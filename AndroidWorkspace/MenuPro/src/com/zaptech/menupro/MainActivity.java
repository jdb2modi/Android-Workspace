package com.zaptech.menupro;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageView imgView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgView=(ImageView)findViewById(R.id.imgBackground);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.itemFruits:
				Toast.makeText(getApplicationContext(), "Fruits",Toast.LENGTH_SHORT).show();
				imgView.setBackgroundResource(R.drawable.fruits);
			break;
		case R.id.itemDrinks:
			Toast.makeText(getApplicationContext(), "Drinks",Toast.LENGTH_SHORT).show();
			imgView.setBackgroundResource(R.drawable.drinks);
		break;
		case R.id.itemNonVeg:
			Toast.makeText(getApplicationContext(), "NonVeg",Toast.LENGTH_SHORT).show();
			imgView.setBackgroundResource(R.drawable.nonveg);
		break;
		case R.id.itemSnakes:
			Toast.makeText(getApplicationContext(), "Snakes",Toast.LENGTH_SHORT).show();
			imgView.setBackgroundResource(R.drawable.snakes);
		break;
		case R.id.itemSweets:
			Toast.makeText(getApplicationContext(), "Sweets",Toast.LENGTH_SHORT).show();
			imgView.setBackgroundResource(R.drawable.sweets);
		break;
		case R.id.itemVeg:
			Toast.makeText(getApplicationContext(), "Vegetable",Toast.LENGTH_SHORT).show();
			imgView.setBackgroundResource(R.drawable.veg);
		break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}

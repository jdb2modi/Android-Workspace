package com.zaptech.knownow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener {
	Button btnFruits, btnVeg, btnDevice, btnPlace, btnBird, btnAnimal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	public void init() {
		btnFruits = (Button) findViewById(R.id.btnKnowFruits);
		btnAnimal = (Button) findViewById(R.id.btnKnowAnimals);
		btnBird = (Button) findViewById(R.id.btnKnowBirds);
		btnDevice = (Button) findViewById(R.id.btnKnowDevices);
		btnPlace = (Button) findViewById(R.id.btnKnowPlaces);
		btnVeg = (Button) findViewById(R.id.btnKnowVegetables);

		btnFruits.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnKnowFruits:
			Intent i = new Intent(HomeActivity.this, KnowFruits.class);
			startActivity(i);
			break;

		default:
			break;
		}

	}
}

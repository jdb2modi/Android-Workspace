package com.example.gpsnearbyplaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	
	RelativeLayout raltiveHotel;
	RelativeLayout raltiveHospital;
	RelativeLayout raltiveATM;
	RelativeLayout raltiveAirport;
	RelativeLayout raltiveBusStation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		

		raltiveHotel = (RelativeLayout) findViewById(R.id.rel1);
		raltiveHospital = (RelativeLayout) findViewById(R.id.rel2);
		raltiveATM = (RelativeLayout) findViewById(R.id.rel3);
		raltiveAirport = (RelativeLayout) findViewById(R.id.rel4);
		raltiveBusStation = (RelativeLayout) findViewById(R.id.rel5);
		
		
		raltiveHotel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						SearchHotelActivity.class);
				i.putExtra("type", "hotel");
				startActivity(i);
			}
		});
		
		raltiveHospital.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						SearchHotelActivity.class);
				i.putExtra("type", "hospital");
				startActivity(i);
			}
		});
		raltiveATM.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						SearchHotelActivity.class);
				i.putExtra("type", "ATM");
				startActivity(i);
			}
		});

		raltiveAirport.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						SearchHotelActivity.class);
				i.putExtra("type", "airport");
				startActivity(i);
			}
		});

		raltiveBusStation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						SearchHotelActivity.class);
				i.putExtra("type", "busstation");
				startActivity(i);
			}
		});


	}
}

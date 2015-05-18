package com.zaptech.sensorlist;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ScrollView scroll;
	TextView textScrollList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		getSensorList();
	}

	public void init() {
		scroll = (ScrollView) findViewById(R.id.scrollSensors);
		textScrollList = (TextView) findViewById(R.id.textSensorList);
	}

	public void getSensorList() {
		SensorManager sMgr = (SensorManager) this
				.getSystemService(SENSOR_SERVICE);
		List sList = sMgr.getSensorList(Sensor.TYPE_ALL);
		StringBuilder data = new StringBuilder();
		for (int i = 0; i < sList.size(); i++) {
			data.append(sList.get(i));
			textScrollList.setText(data);
		}
	}
}

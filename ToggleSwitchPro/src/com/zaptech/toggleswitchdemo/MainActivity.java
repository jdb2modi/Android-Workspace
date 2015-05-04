package com.zaptech.toggleswitchdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	 private ToggleButton toggleButton1, toggleButton2;
	  private Button btnDisplay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
		toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
		btnDisplay = (Button) findViewById(R.id.btnDisplay);
		
		
		btnDisplay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				StringBuffer result = new StringBuffer();
				   result.append("toggleButton1 : ").append(toggleButton1.getText());
				   result.append("\ntoggleButton2 : ").append(toggleButton2.getText());
		 
				   Toast.makeText(MainActivity.this, result.toString(),
					Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
	
}

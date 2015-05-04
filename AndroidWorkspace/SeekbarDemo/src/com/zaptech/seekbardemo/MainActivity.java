package com.zaptech.seekbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private SeekBar seekBar;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeVariables();

		textView.setText("Covered: " + seekBar.getProgress() + "/"
				+ seekBar.getMax());

		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progress = 0;

			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue,
					boolean fromUser) {
				progress = progresValue;
				Toast.makeText(getApplicationContext(),
						"Changing seekbar's progress", Toast.LENGTH_SHORT)
						.show();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Toast.makeText(getApplicationContext(),
						"Started tracking seekbar", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				textView.setText("Covered: " + progress + "/"
						+ seekBar.getMax());
				Toast.makeText(getApplicationContext(),
						"Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initializeVariables() {
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		textView = (TextView) findViewById(R.id.textView1);
	}
}

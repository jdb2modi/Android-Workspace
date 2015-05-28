package com.zaptech.tasklinearlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends Activity implements OnClickListener {
	SeekBar seekbar1;
	RatingBar ratingbar1;
	Spinner spinner1;
	EditText edittext1;
	TextView textView1;
	Button btnClick, btnNext, btnPrevious;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		init();
		testRatingbar();
		testSeekbar();
		testSpinner();
		testEditText();

	}

	public void init() {
		seekbar1 = (SeekBar) findViewById(R.id.seekbar1);
		ratingbar1 = (RatingBar) findViewById(R.id.ratingbar1);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		textView1 = (TextView) findViewById(R.id.tvEditTextValue);
		btnClick = (Button) findViewById(R.id.buttonClick);
		btnClick.setOnClickListener(this);
		btnNext = (Button) findViewById(R.id.buttonNextOnThird);
		btnNext.setOnClickListener(this);
		btnPrevious = (Button) findViewById(R.id.buttonPreviousOnThird);
		btnPrevious.setOnClickListener(this);

	}

	public void testRatingbar() {
		ratingbar1
				.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

					@Override
					public void onRatingChanged(RatingBar ratingBar,
							float rating, boolean fromUser) {
						Toast.makeText(ThirdActivity.this,
								"RATING : " + rating, Toast.LENGTH_SHORT)
								.show();

					}
				});
	}

	public void testSeekbar() {
		seekbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				Toast.makeText(ThirdActivity.this, "Progress : " + progress,
						Toast.LENGTH_SHORT).show();

			}
		});
	}

	public void testSpinner() {

		ArrayAdapter<String> adpt = new ArrayAdapter<String>(
				ThirdActivity.this, android.R.layout.simple_spinner_item,
				getResources().getStringArray(R.array.arrayColor));
		spinner1.setAdapter(adpt);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if (spinner1.getSelectedItem().equals("Red")) {
					spinner1.setBackgroundColor(getResources().getColor(
							R.color.Red));
				} else if (spinner1.getSelectedItem().equals("Green")) {
					spinner1.setBackgroundColor(getResources().getColor(
							R.color.Green));
				} else if (spinner1.getSelectedItem().equals("Blue")) {
					spinner1.setBackgroundColor(getResources().getColor(
							R.color.Blue));
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	public void testEditText() {
		edittext1.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				Toast.makeText(ThirdActivity.this,
						"Text : " + edittext1.getText().toString(),
						Toast.LENGTH_SHORT).show();
				textView1.setText(edittext1.getText().toString());

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonClick:
			textView1.setText(edittext1.getText().toString());
			break;
		case R.id.buttonNextOnThird:
			this.finish();
			intent = new Intent(ThirdActivity.this, FourthActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonPreviousOnThird:
			this.finish();
			intent = new Intent(ThirdActivity.this, SecondActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}

package com.zaptech.taskcontrols;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,
		android.widget.RadioGroup.OnCheckedChangeListener,
		OnItemSelectedListener, OnSeekBarChangeListener,
		OnRatingBarChangeListener {
	Button btn_Button1, start, stop, restart, set, clear;
	CheckBox chk_CheckBox1;
	RadioGroup rg;
	RadioButton rb_Male, rb_Female;
	int selected;
	Spinner spinner_1;
	ArrayAdapter<String> adpt_City;
	SeekBar seekBar1;
	RatingBar ratingBar1;
	Switch switch1;
	EditText ed;
	ToggleButton tgBtn1;
	AutoCompleteTextView autoTxt1;
	MultiAutoCompleteTextView multiAutoTxt1;
	Chronometer chronometer1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		chk_CheckBox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (chk_CheckBox1.isChecked()) {
					Toast.makeText(MainActivity.this, "Selected",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "Not Selected",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		// //Chronometer
		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				chronometer1.start();
			}
		});

		stop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				chronometer1.stop();
			}
		});

		restart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// chronometer1.setBase(SystemClock.elapsedRealtime());
			}
		});

		set.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				chronometer1.setFormat("Formated Time - %s");
			}
		});

		clear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				chronometer1.setFormat(null);
			}
		});
	}

	public void init() {
		btn_Button1 = (Button) findViewById(R.id.btn_Button1);
		btn_Button1.setOnClickListener(this);

		chk_CheckBox1 = (CheckBox) findViewById(R.id.chk_CheckBox1);

		rg = (RadioGroup) findViewById(R.id.radioGroup_RadioGroup1);
		rg.setOnCheckedChangeListener(this);
		rb_Male = (RadioButton) findViewById(R.id.radioBtn_Male);
		rb_Female = (RadioButton) findViewById(R.id.radioBtn_Female);

		spinner_1 = (Spinner) findViewById(R.id.spinner_Spinner1);
		spinner_1.setOnItemSelectedListener(this);
		adpt_City = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, getResources()
						.getStringArray(R.array.arr_City));
		spinner_1.setAdapter(adpt_City);

		seekBar1 = (SeekBar) findViewById(R.id.seekBar_Seekbar1);
		seekBar1.setOnSeekBarChangeListener(this);

		ratingBar1 = (RatingBar) findViewById(R.id.rtBar_RatingBar1);
		ratingBar1.setOnRatingBarChangeListener(this);

		switch1 = (Switch) findViewById(R.id.switch_Switch1);
		// switch1.setOnCheckedChangeListener(this);
		switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Toast.makeText(MainActivity.this, isChecked ? "On" : "Off",
						Toast.LENGTH_SHORT).show();

			}
		});
		tgBtn1 = (ToggleButton) findViewById(R.id.tgBtn1);
		tgBtn1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Toast.makeText(MainActivity.this, isChecked ? "On" : "Off",
						Toast.LENGTH_SHORT).show();

			}
		});
		autoTxt1 = (AutoCompleteTextView) findViewById(R.id.autoTxt_AutocomepleteTextview1);
		autoTxt1.setThreshold(1);
		autoTxt1.setAdapter(adpt_City);
		multiAutoTxt1 = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoTxt_Multi1);
		multiAutoTxt1.setThreshold(1);
		multiAutoTxt1.setAdapter(adpt_City);
		multiAutoTxt1
				.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		ed = (EditText) findViewById(R.id.edt_EditText1);

		start = (Button) findViewById(R.id.btn_Start);
		stop = (Button) findViewById(R.id.btn_Stop);
		restart = (Button) findViewById(R.id.btn_Restart);
		set = (Button) findViewById(R.id.btn_Set);
		clear = (Button) findViewById(R.id.btn_Clear);
		chronometer1 = (Chronometer) findViewById(R.id.chronometer1);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_Button1:

			break;

		default:
			break;
		}

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.radioBtn_Male:
			Toast.makeText(MainActivity.this, "Male", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.radioBtn_Female:
			Toast.makeText(MainActivity.this, "Female", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.switch_Switch1:
			Toast.makeText(MainActivity.this, String.valueOf(checkedId),
					Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

	}

	// For Spinner
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(MainActivity.this,
				spinner_1.getSelectedItem().toString(), Toast.LENGTH_SHORT)
				.show();
	}

	// For Spinner
	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	// For Seekbar
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

	}

	// For Seekbar
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	// For Seekbar
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		Toast.makeText(MainActivity.this,
				String.valueOf(seekBar1.getProgress()), Toast.LENGTH_SHORT)
				.show();
	}

	// For Ratingbar
	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		Toast.makeText(MainActivity.this, String.valueOf(rating),
				Toast.LENGTH_SHORT).show();
	}
}

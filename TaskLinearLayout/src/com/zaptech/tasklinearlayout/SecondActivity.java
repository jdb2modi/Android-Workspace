package com.zaptech.tasklinearlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SecondActivity extends Activity implements OnClickListener {
	RadioButton radioBtn1, radioBtn2;
	CheckBox checkbox1, checkbox2;
	ImageButton imageBtn1, imageBtn2;
	RadioGroup rg;
	Button btnPrevious, btnNext;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		init();
		testCheckBox();
	}

	public void init() {
		radioBtn1 = (RadioButton) findViewById(R.id.radiobuttonMale);
		radioBtn2 = (RadioButton) findViewById(R.id.radiobuttonFemale);
		checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
		checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		imageBtn1 = (ImageButton) findViewById(R.id.imagebutton1);
		imageBtn2 = (ImageButton) findViewById(R.id.imagebutton2);
		imageBtn1.setOnClickListener(this);
		imageBtn2.setOnClickListener(this);
		btnPrevious = (Button) findViewById(R.id.buttonPreviousOnSecond);
		btnPrevious.setOnClickListener(this);
		btnNext = (Button) findViewById(R.id.buttonNextOnSecond);
		btnNext.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imagebutton1:
			Toast.makeText(SecondActivity.this, "ImageButton1 Clicked",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.imagebutton2:
			Toast.makeText(SecondActivity.this, "ImageButton2 Clicked",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.buttonNextOnSecond:
			this.finish();
			intent = new Intent(SecondActivity.this, ThirdActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonPreviousOnSecond:
			this.finish();
			intent = new Intent(SecondActivity.this, HomeActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	public void testCheckBox() {
		checkbox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (checkbox1.isChecked()) {
					Toast.makeText(SecondActivity.this, "Male",
							Toast.LENGTH_SHORT).show();
				} else if (checkbox1.isChecked() && checkbox2.isChecked()) {
					Toast.makeText(SecondActivity.this, "Male + Female",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		checkbox2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (checkbox2.isChecked()) {
					Toast.makeText(SecondActivity.this, "Female",
							Toast.LENGTH_SHORT).show();
				} else if (checkbox1.isChecked() && checkbox2.isChecked()) {
					Toast.makeText(SecondActivity.this, "Male + Feamle",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	/*
	 * public void testRadioButton() {
	 * 
	 * final String value = ((RadioButton) findViewById(rg
	 * .getCheckedRadioButtonId())).getText().toString();
	 * 
	 * rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	 * 
	 * public void onCheckedChanged(RadioGroup group, int checkedId) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void onCheckedChanged(CompoundButton buttonView, boolean
	 * isChecked) { // TODO Auto-generated method stub
	 * 
	 * } }); }
	 */
}

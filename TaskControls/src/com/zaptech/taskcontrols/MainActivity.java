package com.zaptech.taskcontrols;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener,
		android.widget.RadioGroup.OnCheckedChangeListener,
		OnItemSelectedListener, OnSeekBarChangeListener,
		OnRatingBarChangeListener {
	Button btn_Button1, btn_Start, btn_Stop, btn_Restart, btn_Set, btn_Clear;
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
	Chronometer mChronometer;
	// For Date-Picker
	private DatePicker datePicker;
	private Calendar calendar;
	private TextView txt_dateView;
	private int int_year, int_month, int_day;

	// For Time-Picker
	private TimePicker timePicker1;
	private TextView time;
	private String format = "";

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

		// For Switch
		switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Toast.makeText(MainActivity.this, isChecked ? "On" : "Off",
						Toast.LENGTH_SHORT).show();

			}
		});
		// For Toggle button
		tgBtn1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Toast.makeText(MainActivity.this, isChecked ? "On" : "Off",
						Toast.LENGTH_SHORT).show();

			}
		});

		// For Date-Picker
		int_year = calendar.get(Calendar.YEAR);
		int_month = calendar.get(Calendar.MONTH);
		int_day = calendar.get(Calendar.DAY_OF_MONTH);
		showDate(int_year, int_month + 1, int_day);

		// For Time-Picker
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		showTime(hour, min);

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

		tgBtn1 = (ToggleButton) findViewById(R.id.tgBtn1);

		autoTxt1 = (AutoCompleteTextView) findViewById(R.id.autoTxt_AutocomepleteTextview1);
		autoTxt1.setThreshold(1);
		autoTxt1.setAdapter(adpt_City);
		multiAutoTxt1 = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoTxt_Multi1);
		multiAutoTxt1.setThreshold(1);
		multiAutoTxt1.setAdapter(adpt_City);
		multiAutoTxt1
				.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		ed = (EditText) findViewById(R.id.edt_EditText1);

		btn_Start = (Button) findViewById(R.id.btn_Start);
		btn_Start.setOnClickListener(this);
		btn_Stop = (Button) findViewById(R.id.btn_Stop);
		btn_Stop.setOnClickListener(this);
		btn_Restart = (Button) findViewById(R.id.btn_Restart);
		btn_Restart.setOnClickListener(this);
		btn_Set = (Button) findViewById(R.id.btn_Set);
		btn_Set.setOnClickListener(this);
		btn_Clear = (Button) findViewById(R.id.btn_Clear);
		btn_Clear.setOnClickListener(this);
		mChronometer = (Chronometer) findViewById(R.id.chronometer1);

		calendar = Calendar.getInstance();
		// For Date-Picker
		txt_dateView = (TextView) findViewById(R.id.textView3);
		// For Time-Picker
		timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
		time = (TextView) findViewById(R.id.textView1);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_Start:
			mChronometer.start();
			break;
		case R.id.btn_Stop:
			mChronometer.stop();
			break;
		case R.id.btn_Restart:
			mChronometer.setText("00:00");
			mChronometer.start();
			break;
		case R.id.btn_Set:
			mChronometer.setFormat("Formated Time - %s");
			break;
		case R.id.btn_Clear:
			mChronometer.setFormat(null);
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

	// For Date-Picker
	public void setDate(View view) {
		showDialog(999);
		Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if (id == 999) {
			return new DatePickerDialog(this, myDateListener, int_year,
					int_month, int_day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			// arg1 = year
			// arg2 = month
			// arg3 = day
			showDate(arg1, arg2 + 1, arg3);
		}
	};

	private void showDate(int year, int month, int day) {
		txt_dateView.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));
	}

	// For Time-Picker
	public void setTime(View view) {
		int hour = timePicker1.getCurrentHour();
		int min = timePicker1.getCurrentMinute();
		showTime(hour, min);
	}

	public void showTime(int hour, int min) {
		if (hour == 0) {
			hour += 12;
			format = "AM";
		} else if (hour == 12) {
			format = "PM";
		} else if (hour > 12) {
			hour -= 12;
			format = "PM";
		} else {
			format = "AM";
		}
		if (min < 10) {
			time.setText(new StringBuilder().append(hour).append(" : 0")
					.append(min).append(" ").append(format));
		} else if (hour < 10) {
			time.setText(new StringBuilder().append(" 0").append(hour)
					.append(" : ").append(min).append(" ").append(format));
		} else if (min < 10 && hour < 10) {
			time.setText(new StringBuilder().append(" 0").append(hour)
					.append(" : 0").append(min).append(" ").append(format));
		} else {
			time.setText(new StringBuilder().append(hour).append(" : ")
					.append(min).append(" ").append(format));
		}
	}

}

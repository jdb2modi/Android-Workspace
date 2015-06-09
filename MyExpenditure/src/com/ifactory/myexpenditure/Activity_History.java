package com.ifactory.myexpenditure;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_History extends Activity {
	private Button btn_date;
	private DatePicker datePicker;
	private Calendar calendar;
	private int int_year, int_month, int_day;
	private int int_year2, int_month2, int_day2;

	TextView txt_startDate, txt_endDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		init();
		// FOR DATE-PICKER
		int_year = calendar.get(Calendar.YEAR);
		int_month = calendar.get(Calendar.MONTH);
		int_day = calendar.get(Calendar.DAY_OF_MONTH);
		int_year2 = calendar.get(Calendar.YEAR);
		int_month2 = calendar.get(Calendar.MONTH);
		int_day2 = calendar.get(Calendar.DAY_OF_MONTH);

		showDate(int_year, int_month + 1, int_day);
		showDate2(int_year2, int_month2 + 1, int_day2);

	}

	public void init() {
		txt_endDate = (TextView) findViewById(R.id.txt_endingDate);
		txt_startDate = (TextView) findViewById(R.id.txt_startingDate);
		calendar = Calendar.getInstance();
	}

	// /////FOR DATE-PICKER
	public void setDate(View view) {
		showDialog(999);
		Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
				.show();
	}

	public void setDate2(View view) {
		showDialog(990);
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
		if (id == 990) {
			return new DatePickerDialog(this, myDateListener2, int_year2,
					int_month2, int_day2);
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
	private DatePickerDialog.OnDateSetListener myDateListener2 = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			// arg1 = year
			// arg2 = month
			// arg3 = dayshowDate(arg1, arg2 + 1, arg3);
			showDate2(arg1, arg2 + 1, arg3);
		}
	};

	private void showDate(int year, int month, int day) {
		txt_startDate.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));

	}

	private void showDate2(int year, int month, int day) {

		txt_endDate.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));
	}
}

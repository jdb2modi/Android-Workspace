package com.ifactory.myexpenditure;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_AddExpence extends Activity implements OnClickListener,
		OnItemSelectedListener {
	private Button btn_date;
	private DatePicker datePicker;
	private Calendar calendar;
	private TextView txt_date;
	private int int_year, int_month, int_day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__add_expence);
		init();
		// FOR DATE-PICKER
		int_year = calendar.get(Calendar.YEAR);
		int_month = calendar.get(Calendar.MONTH);
		int_day = calendar.get(Calendar.DAY_OF_MONTH);
		showDate(int_year, int_month + 1, int_day);

	}

	public void init() {
		/*
		 * btn_date = (Button) findViewById(R.id.btn_setDate);
		 * btn_date.setOnClickListener(this);
		 */
		// FOR DATE-PICKER
		txt_date = (TextView) findViewById(R.id.txt_date);
		calendar = Calendar.getInstance();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		default:
			break;
		}

	}

	// /////FOR DATE-PICKER
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
		txt_date.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));
	}

	// ///////////FOR SPINNER
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}

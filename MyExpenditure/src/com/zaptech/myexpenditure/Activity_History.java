package com.zaptech.myexpenditure;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_History extends Activity implements OnClickListener {
	private Button btn_showAllHistory, btn_showSpecificHistory;
	private DatePicker datePicker;
	private Calendar calendar;
	private int int_year, int_month, int_day;
	private int int_year2, int_month2, int_day2;

	TextView txt_startDate, txt_endDate;
	Intent intent;

	Button btn_exit, btn_back;
	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		init();

		showDate(int_year, int_month + 1, int_day);
		showDate2(int_year2, int_month2 + 1, int_day2);
		setTypeface();
	}

	public void init() {
		txt_endDate = (TextView) findViewById(R.id.txt_endingDate);
		txt_startDate = (TextView) findViewById(R.id.txt_startingDate);
		// FOR DATE-PICKER
		calendar = Calendar.getInstance();
		int_year = calendar.get(Calendar.YEAR);
		int_month = calendar.get(Calendar.MONTH);
		int_day = calendar.get(Calendar.DAY_OF_MONTH);
		int_year2 = calendar.get(Calendar.YEAR);
		int_month2 = calendar.get(Calendar.MONTH);
		int_day2 = calendar.get(Calendar.DAY_OF_MONTH);

		btn_showAllHistory = (Button) findViewById(R.id.btn_showAllHistory);
		btn_showAllHistory.setOnClickListener(this);
		btn_showSpecificHistory = (Button) findViewById(R.id.btn_showSpecificHistory);
		btn_showSpecificHistory.setOnClickListener(this);
		btn_exit = (Button) findViewById(R.id.btn_exitFromHistory);
		btn_exit.setOnClickListener(this);
		btn_back = (Button) findViewById(R.id.btn_backFromHistory);
		btn_back.setOnClickListener(this);
		sp = getSharedPreferences(MyPREFERENCES, MODE_APPEND);
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

		if (day < 10) {

			if (month < 10) {
				txt_startDate.setText(new StringBuilder().append("0" + day)
						.append("/").append("0" + month).append("/")
						.append(year));
			} else {
				txt_startDate.setText(new StringBuilder().append("0" + day)
						.append("/").append(month).append("/").append(year));
			}

		} else {

			if (month < 10) {
				txt_startDate.setText(new StringBuilder().append(day)
						.append("/").append("0" + month).append("/")
						.append(year));

			} else {
				txt_startDate.setText(new StringBuilder().append(day)
						.append("/").append(month).append("/").append(year));
			}
		}

	}

	private void showDate2(int year, int month, int day) {

		if (day < 10) {

			if (month < 10) {
				txt_endDate.setText(new StringBuilder().append("0" + day)
						.append("/").append("0" + month).append("/")
						.append(year));
			} else {
				txt_endDate.setText(new StringBuilder().append("0" + day)
						.append("/").append(month).append("/").append(year));
			}

		} else {

			if (month < 10) {
				txt_endDate.setText(new StringBuilder().append(day).append("/")
						.append("0" + month).append("/").append(year));

			} else {
				txt_endDate.setText(new StringBuilder().append(day).append("/")
						.append(month).append("/").append(year));
			}

		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_showAllHistory:
			finish();
			intent = new Intent(Activity_History.this,
					Activity_ViewHistory.class);
			intent.putExtra("HISTORY", "ALL");
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);
			break;
		case R.id.btn_showSpecificHistory:
			finish();
			intent = new Intent(Activity_History.this,
					Activity_ViewHistory.class);
			intent.putExtra("HISTORY", "SPECIFIC");
			intent.putExtra("SDate", txt_startDate.getText().toString());
			intent.putExtra("EDate", txt_endDate.getText().toString());
			overridePendingTransition(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);
			startActivity(intent);
			break;
		case R.id.btn_exitFromHistory:
			Editor edit = sp.edit();
			edit.clear();
			edit.commit();
			Toast.makeText(getApplicationContext(), "Exiting...",
					Toast.LENGTH_SHORT).show();
			AlertDialog.Builder alert = new AlertDialog.Builder(
					Activity_History.this);
			alert.setTitle("Exit Confirmation");
			alert.setMessage("Are you want to Close the Application ?");
			alert.setCancelable(false);
			alert.setPositiveButton("EXIT",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();

						}
					});
			alert.setNegativeButton("CANCEL",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			alert.show();
			break;
		case R.id.btn_backFromHistory:
			finish();
			intent = new Intent(Activity_History.this, Activity_Home.class);
			overridePendingTransition(R.anim.in_from_left_activity,
					R.anim.out_to_right_activity);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getAssets(),
				"fonts/Tahoma.ttf");

		btn_showAllHistory.setTypeface(tyFace);
		btn_showSpecificHistory.setTypeface(tyFace);

		txt_startDate.setTypeface(tyFace);
		txt_endDate.setTypeface(tyFace);

	}
}

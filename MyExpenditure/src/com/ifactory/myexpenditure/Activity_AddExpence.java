package com.ifactory.myexpenditure;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_AddExpence extends Activity implements OnClickListener {
	// FOR DATE-PICKER...
	private Button btn_date, btn_addExpence, btn_exit, btn_back;
	private Calendar calendar;
	private TextView txt_ExpenseDate;// Used for Date of Expence...
	private int int_year, int_month, int_day;

	// FOR ADD EXPENCE...
	Spinner spin_ExpenceCategory, spin_ExpenceCurrency, spin_ExpenceMode;
	EditText ed_ExpenceDescription, ed_ExpenceAmount;
	DBHelper dbHelper;
	Intent intent;
	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__add_expence);
		// DATABASE object..
		dbHelper = new DBHelper(Activity_AddExpence.this);
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
		txt_ExpenseDate = (TextView) findViewById(R.id.txt_date);
		calendar = Calendar.getInstance();

		// FOR ADD EXPENCE...
		spin_ExpenceCategory = (Spinner) findViewById(R.id.spin_expenceOnAddExpence);
		spin_ExpenceCurrency = (Spinner) findViewById(R.id.spin_currency);
		spin_ExpenceMode = (Spinner) findViewById(R.id.spin_paymentMode);
		ed_ExpenceDescription = (EditText) findViewById(R.id.ed_Description);
		ed_ExpenceAmount = (EditText) findViewById(R.id.ed_Amount);
		btn_addExpence = (Button) findViewById(R.id.btn_saveOnAddExpence);
		btn_addExpence.setOnClickListener(this);
		btn_exit = (Button) findViewById(R.id.btn_exitFromAddExpence);
		btn_exit.setOnClickListener(this);
		btn_back = (Button) findViewById(R.id.btn_backFromAddExpence);
		btn_back.setOnClickListener(this);
		sp = getSharedPreferences(MyPREFERENCES, MODE_APPEND);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_saveOnAddExpence:
			// Inserting Expence Data..
			dbHelper.insertExpence(String.valueOf(spin_ExpenceCategory
					.getSelectedItem().toString()), String
					.valueOf(txt_ExpenseDate.getText().toString()), String
					.valueOf(spin_ExpenceMode.getSelectedItem().toString()),
					"NULL", "NULL", Integer.parseInt(ed_ExpenceAmount.getText()
							.toString()), String.valueOf(ed_ExpenceDescription
							.getText().toString()));
			ed_ExpenceDescription.requestFocus();
			clearInputs();
			Toast.makeText(Activity_AddExpence.this,
					"Expence Successfully Added", 500).show();
			break;
		case R.id.btn_exitFromAddExpence:
			Editor edit = sp.edit();
			edit.clear();
			edit.commit();
			Toast.makeText(getApplicationContext(), "Exiting...",
					Toast.LENGTH_SHORT).show();
			AlertDialog.Builder alert = new AlertDialog.Builder(
					Activity_AddExpence.this);
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
		case R.id.btn_backFromAddExpence:
			finish();
			intent = new Intent(Activity_AddExpence.this, Activity_Home.class);
			startActivity(intent);
			break;
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
		txt_ExpenseDate.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));
	}

	public void clearInputs() {
		spin_ExpenceCategory.setSelected(false);
		spin_ExpenceCurrency.setSelected(false);
		spin_ExpenceMode.setSelected(false);
		ed_ExpenceDescription.setText("");
		ed_ExpenceAmount.setText("");
	}

}

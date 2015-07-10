package com.zaptech.myexpenditure;

import java.util.ArrayList;
import java.util.Calendar;

import com.ifactory.myexpenditure.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_AddExpence extends Activity implements OnClickListener {
	// FOR DATE-PICKER...
	private Button mBtn_date;
	private Button mBtn_addExpence;
	private Button mBtn_exit;
	private Button mBtn_back;
	private Calendar mCalendar;
	private TextView mTxt_ExpenseDate;// Used for Date of Expence...
	private int mInt_year, mInt_month, mInt_day;
	LayoutInflater inflater;
	String strTemp;

	private ArrayList<String> expenceList;
	// FOR ADD EXPENCE...
	Spinner spin_ExpenceCategory, spin_ExpenceCurrency, spin_ExpenceMode;
	EditText ed_ExpenceDescription, ed_ExpenceAmount;
	DBHelper dbHelper;
	Intent intent;
	SharedPreferences sp;
	int imageExpences[] = { R.drawable.travel, R.drawable.food,
			R.drawable.glossary, R.drawable.cloths, R.drawable.medical,
			R.drawable.fuel, R.drawable.entertainment, R.drawable.telephone,
			R.drawable.electric, R.drawable.gas, R.drawable.emi,
			R.drawable.shopping, R.drawable.others };
	String arrayExpence[] = { "Travel", "Food", "Glossary", "Cloths",
			"Medical", "Fuel", "Entertainment", "Telephone Bill",
			"Electric Bill", "Gas Bill", "EMI", "Shopping", "Others" };
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__add_expence);
		// DATABASE object..
		dbHelper = new DBHelper(Activity_AddExpence.this);
		init();
		initExpenceList();
		// FOR DATE-PICKER
		mInt_year = mCalendar.get(Calendar.YEAR);
		mInt_month = mCalendar.get(Calendar.MONTH);
		mInt_day = mCalendar.get(Calendar.DAY_OF_MONTH);
		showDate(mInt_year, mInt_month + 1, mInt_day);

		/*
		 * spin_ExpenceCategory.setOnItemClickListener(new OnItemClickListener()
		 * {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) { // TODO Auto-generated method stub
		 * 
		 * } });
		 */
		/*
		 * spin_ExpenceCategory .setOnItemSelectedListener(new
		 * OnItemSelectedListener() {
		 * 
		 * @Override public void onItemSelected(AdapterView<?> parent, View
		 * view, int position, long id) { // TODO Auto-generated method stub
		 * 
		 * 
		 * String string= expenceList.get(position).toString();
		 * spin_ExpenceCategory.setSelection();
		 * 
		 * }
		 * 
		 * @Override public void onNothingSelected(AdapterView<?> parent) { //
		 * TODO Auto-generated method stub
		 * 
		 * } });
		 */

	}

	public void init() {

		// FOR DATE-PICKER
		mTxt_ExpenseDate = (TextView) findViewById(R.id.txt_date);
		mCalendar = Calendar.getInstance();

		// FOR ADD EXPENCE...
		spin_ExpenceCategory = (Spinner) findViewById(R.id.spin_expenceOnAddExpence);
		spin_ExpenceCurrency = (Spinner) findViewById(R.id.spin_currency);
		spin_ExpenceMode = (Spinner) findViewById(R.id.spin_paymentMode);
		ed_ExpenceDescription = (EditText) findViewById(R.id.ed_Description);
		ed_ExpenceAmount = (EditText) findViewById(R.id.ed_Amount);
		mBtn_addExpence = (Button) findViewById(R.id.btn_saveOnAddExpence);
		mBtn_addExpence.setOnClickListener(this);
		mBtn_exit = (Button) findViewById(R.id.btn_exitFromAddExpence);
		mBtn_exit.setOnClickListener(this);
		mBtn_back = (Button) findViewById(R.id.btn_backFromAddExpence);
		mBtn_back.setOnClickListener(this);
		sp = getSharedPreferences(MyPREFERENCES, MODE_APPEND);
		expenceList = new ArrayList<String>();
		spin_ExpenceCategory.setAdapter(new myExpenceAdapter(this,
				arrayExpence, R.layout.custom_expences));
		spin_ExpenceCategory
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						strTemp = arrayExpence[position];

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_saveOnAddExpence:
			addExpence();
			break;

		case R.id.btn_exitFromAddExpence:
			exit();
			break;

		case R.id.btn_backFromAddExpence:
			back();
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
			return new DatePickerDialog(this, myDateListener, mInt_year,
					mInt_month, mInt_day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

			showDate(arg1, arg2 + 1, arg3);
		}
	};

	private void showDate(int year, int month, int day) {
		
		if (day < 10) {

			if (month < 10) {
				mTxt_ExpenseDate.setText(new StringBuilder().append("0" + day)
						.append("/").append("0" + month).append("/")
						.append(year));
			} else {
				mTxt_ExpenseDate.setText(new StringBuilder().append("0" + day)
						.append("/").append(month).append("/").append(year));
			}

		} else {

			if (month < 10) {
				mTxt_ExpenseDate.setText(new StringBuilder().append(day)
						.append("/").append("0" + month).append("/")
						.append(year));

			} else {
				mTxt_ExpenseDate.setText(new StringBuilder().append(day)
						.append("/").append(month).append("/").append(year));
			}
		}

	}

	public void clearInputs() {
		spin_ExpenceCategory.setSelected(false);
		spin_ExpenceCurrency.setSelected(false);
		spin_ExpenceMode.setSelected(false);
		ed_ExpenceDescription.setText("");
		ed_ExpenceAmount.setText("");
	}

	private void exit() {
		Toast.makeText(getApplicationContext(), "Exiting...",
				Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alert = new AlertDialog.Builder(
				Activity_AddExpence.this);
		alert.setTitle("Exit Confirmation");
		alert.setMessage("Are you want to Close the Application ?");
		alert.setCancelable(false);
		alert.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {

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
	}

	private void back() {
		finish();
		intent = new Intent(Activity_AddExpence.this, Activity_Home.class);
		startActivity(intent);
	}

	private void addExpence() {
		// Inserting Expence Data..

		dbHelper.insertExpence(String.valueOf(strTemp),
				String.valueOf(mTxt_ExpenseDate.getText().toString()),
				String.valueOf(spin_ExpenceMode.getSelectedItem().toString()),
				"NULL", "NULL",
				Integer.parseInt(ed_ExpenceAmount.getText().toString()),
				String.valueOf(ed_ExpenceDescription.getText().toString()));
		ed_ExpenceDescription.requestFocus();
		clearInputs();
		Toast.makeText(Activity_AddExpence.this, "Expence Successfully Added",
				500).show();
	}

	public void initExpenceList() {
		for (int i = 0; i < arrayExpence.length; i++) {
			expenceList.add(arrayExpence[i]);

		}
	}

	class myExpenceAdapter extends ArrayAdapter<String> {
		Context context;

		public myExpenceAdapter(Activity_AddExpence activity_AddExpence,
				String[] arrayExpence, int customExpences) {
			// TODO Auto-generated constructor stub

			super(activity_AddExpence, customExpences, arrayExpence);
			this.context = activity_AddExpence;
		}

		@Override
		public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
			return getCustomView(position, cnvtView, prnt);
		}

		@Override
		public View getView(int pos, View cnvtView, ViewGroup prnt) {
			return getCustomView(pos, cnvtView, prnt);
		}

		private View getCustomView(int pos, View cnvtView, ViewGroup prnt) {
			// TODO Auto-generated method stub

			LayoutInflater inflater = getLayoutInflater();
			View mySpinner = inflater.inflate(R.layout.custom_expences, prnt,
					false);
			if (mySpinner == null) {
				mySpinner = inflater.inflate(R.layout.custom_expences, null);
			}
			TextView tvExpenceTitle = (TextView) mySpinner
					.findViewById(R.id.txt_customExpence);
			ImageView imgExpence = (ImageView) mySpinner
					.findViewById(R.id.img_customExpence);
			tvExpenceTitle.setText(arrayExpence[pos]);
			imgExpence.setBackgroundResource(imageExpences[pos]);
			return mySpinner;
		}

		/*
		 * @Override public View getView(int position, View convertView,
		 * ViewGroup parent) { if (inflater == null) { inflater =
		 * (LayoutInflater) context .getSystemService(LAYOUT_INFLATER_SERVICE);
		 * } if (convertView == null) { convertView =
		 * inflater.inflate(R.layout.custom_expences, null); } TextView
		 * tvExpenceTitle = (TextView) convertView
		 * .findViewById(R.id.txt_customExpence); ImageView imgExpence =
		 * (ImageView) convertView .findViewById(R.id.img_customExpence);
		 * tvExpenceTitle.setText(arrayExpence[position]);
		 * imgExpence.setBackgroundResource(imageExpences[position]); return
		 * convertView; }
		 */
	}

}

package com.zaptech.myexpenditure2.fragment;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.zaptech.myexpenditure2.R;

public class FragmentAddExpence extends Fragment implements OnClickListener {
	int setDate = 0;
	private Button mbtnDateOfExpence;
	private Button mbtnSave;
	private Button mbtnCancel;
	private Calendar mcalendar;
	private int mint_year, mint_month, mint_day;

	private LayoutInflater minflater;
	private String mstrTemp;
	private ArrayList<String> mexpenceList;
	private TextView mTxt_ExpenseDate;// Used for Date of Expence...
	private int mimageExpences[] = { R.drawable.travel, R.drawable.food,
			R.drawable.glossary, R.drawable.cloths, R.drawable.medical,
			R.drawable.fuel, R.drawable.entertainment, R.drawable.telephone,
			R.drawable.expence, R.drawable.gas, R.drawable.emi,
			R.drawable.shopping, R.drawable.others };
	private String marrayExpence[] = { "Expence Title", "Travel", "Food",
			"Glossary", "Cloths", "Medical", "Fuel", "Entertainment",
			"Telephone Bill", "Electric Bill", "Gas Bill", "EMI", "Shopping",
			"Others" };
	// FOR ADD EXPENCE...
	private Spinner mspinExpenceCategory;
	private Spinner mspinExpenceCurrency;
	private Spinner mspinExpenceMode;
	private EditText medExpenceDescription, medExpenceAmount;
	com.zaptech.myexpenditure2.database.DBHelper dbHelper;
	Intent intent;
	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_addexpence, container,
				false);

		init(rootView);
		setCurrentDate();
		return rootView;
	}

	public void init(View rootView) {
		mexpenceList = new ArrayList<String>();
		// FOR ADD EXPENCE...
		mspinExpenceCategory = (Spinner) rootView
				.findViewById(R.id.spin_expenceOnAddExpence);

		System.err.println("+++++++++++++++++++++++++++++++++++++++");

		mspinExpenceCurrency = (Spinner) rootView
				.findViewById(R.id.spin_currency);
		mspinExpenceCurrency.setSelection(50);
		mspinExpenceMode = (Spinner) rootView
				.findViewById(R.id.spin_paymentMode);
		mspinExpenceMode.setSelection(1);
		medExpenceDescription = (EditText) rootView
				.findViewById(R.id.ed_Description);
		medExpenceAmount = (EditText) rootView.findViewById(R.id.ed_Amount);

		mbtnDateOfExpence = (Button) rootView.findViewById(R.id.btn_setDate);
		mbtnDateOfExpence.setOnClickListener(this);
		mbtnSave = (Button) rootView.findViewById(R.id.btn_saveOnAddExpence);
		mbtnSave.setOnClickListener(this);
		mbtnCancel = (Button) rootView
				.findViewById(R.id.btn_cancelOnAddExpence);
		mbtnCancel.setOnClickListener(this);
		mTxt_ExpenseDate = (TextView) rootView.findViewById(R.id.txt_date);
		// DATABASE OBJECT...
		dbHelper = new com.zaptech.myexpenditure2.database.DBHelper(
				getActivity().getApplicationContext());
	}

	public void clearInputs() {
		// spin_ExpenceCategory.setSelected(false);
		mspinExpenceCategory.setSelection(1);
		mspinExpenceCurrency.setSelected(false);
		mspinExpenceMode.setSelected(false);
		medExpenceDescription.setText("");
		medExpenceAmount.setText("");
	}

	private void exit() {
		Toast.makeText(getActivity(), "Exiting...", Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity()
				.getApplicationContext());
		alert.setTitle("Exit Confirmation");
		alert.setMessage("Are you want to Close the Application ?");
		alert.setCancelable(false);
		alert.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				getActivity().finish();

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

	private void addExpence() {
		// Inserting Expence Data..

		dbHelper.insertExpence(String.valueOf(mstrTemp),
				String.valueOf(mTxt_ExpenseDate.getText().toString()),
				String.valueOf(mspinExpenceMode.getSelectedItem().toString()),
				"NULL", "NULL",
				Integer.parseInt(medExpenceAmount.getText().toString()),
				String.valueOf(medExpenceDescription.getText().toString()));
		medExpenceDescription.requestFocus();
		clearInputs();
		Toast.makeText(getActivity().getApplicationContext(),
				"Expence Successfully Added", 500).show();
		mTxt_ExpenseDate.setText("");
	}

	class myExpenceAdapter extends ArrayAdapter<String> {
		Context context;

		public myExpenceAdapter(FragmentAddExpence fragmentAddExpence,
				String[] arrayExpence, int customExpences) {

			super(getActivity().getApplicationContext(), customExpences,
					arrayExpence);
			this.context = getActivity().getApplicationContext();
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

			LayoutInflater inflater = getActivity().getLayoutInflater();
			View mySpinner = inflater.inflate(R.layout.custom_expences, prnt,
					false);
			if (mySpinner == null) {
				mySpinner = inflater.inflate(R.layout.custom_expences, null);
			}
			TextView tvExpenceTitle = (TextView) mySpinner
					.findViewById(R.id.txt_customExpence);
			ImageView imgExpence = (ImageView) mySpinner
					.findViewById(R.id.img_customExpence);
			tvExpenceTitle.setText(marrayExpence[pos]);
			imgExpence.setBackgroundResource(mimageExpences[pos]);
			return mySpinner;
		}

	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Tahoma.ttf");

		mbtnSave.setTypeface(tyFace);

		mTxt_ExpenseDate.setTypeface(tyFace);
		medExpenceDescription.setTypeface(tyFace);
		medExpenceAmount.setTypeface(tyFace);

	}

	private void mValidate() {
		String strExpence = medExpenceAmount.getText().toString().trim();
		if (strExpence.length() == 0 || Integer.parseInt(strExpence) <= 0) {
			Toast.makeText(getActivity().getApplicationContext(),
					"Expence cannot be null or less than zero",
					Toast.LENGTH_SHORT).show();
			medExpenceAmount.setFocusable(true);

		} else {
			addExpence();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_setDate:
			setDate = 1;
			showDatePicker();
			break;
		case R.id.btn_saveOnAddExpence:
			mValidate();
			break;
		case R.id.btn_cancelOnAddExpence:
			clearInputs();
			break;
		default:
			break;
		}

	}

	private void showDatePicker() {
		FragmentDatePicker date = new FragmentDatePicker();
		/**
		 * Set Up Current Date Into dialog
		 */
		Calendar calender = Calendar.getInstance();
		Bundle args = new Bundle();
		args.putInt("year", calender.get(Calendar.YEAR));
		args.putInt("month", calender.get(Calendar.MONTH));
		args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		date.setArguments(args);
		/**
		 * Set Call back to capture selected date
		 */
		date.setCallBack(ondate);
		date.show(getActivity().getFragmentManager(), "datePicker");
	}

	OnDateSetListener ondate = new OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			monthOfYear += 1;
			if (dayOfMonth < 10) {

				if (monthOfYear < 10) {
					mTxt_ExpenseDate
							.setText(new StringBuilder()
									.append("0" + dayOfMonth).append("/")
									.append("0" + monthOfYear).append("/")
									.append(year));
				} else {
					mTxt_ExpenseDate.setText(new StringBuilder()
							.append("0" + dayOfMonth).append("/")
							.append(monthOfYear).append("/").append(year));
				}

			} else {

				if (monthOfYear < 10) {
					mTxt_ExpenseDate
							.setText(new StringBuilder().append(dayOfMonth)
									.append("/").append("0" + monthOfYear)
									.append("/").append(year));

				} else {
					mTxt_ExpenseDate.setText(new StringBuilder()
							.append(dayOfMonth).append("/").append(monthOfYear)
							.append("/").append(year));
				}
			}

		}
	};

	public void selectExpenceCategory() {
		mspinExpenceCategory
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						mstrTemp = marrayExpence[position + 1];

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});
	}

	public void setCurrentDate() {
		final Calendar c = Calendar.getInstance();
		mint_year = c.get(Calendar.YEAR);
		mint_month = c.get(Calendar.MONTH);
		mint_day = c.get(Calendar.DAY_OF_MONTH);
		mTxt_ExpenseDate.setText(new StringBuilder()

		.append(mint_day).append("/").append(mint_month + 1).append("/")
				.append(mint_year));
		selectExpenceCategory();
	}
}

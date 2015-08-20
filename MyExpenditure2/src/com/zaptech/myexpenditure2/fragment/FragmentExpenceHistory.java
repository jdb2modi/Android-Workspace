package com.zaptech.myexpenditure2.fragment;

import java.util.Calendar;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.zaptech.myexpenditure2.R;

public class FragmentExpenceHistory extends Fragment implements OnClickListener {
	private Button mbtnStartDate;
	private Button mbtnEndDate;
	private Button mbtnShowSpecificHistory;
	private Button mbtnShowAllHistory;
	private TextView mtxtStartingDate;
	private TextView mtxtEndignDate;
	private String strDate = "";
	private String strStartDate, strEndDate;
	SharedPreferences sp;
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_expencehistory,
				container, false);
		init(rootView);

		return rootView;
	}

	public void init(View rootView) {
		mbtnStartDate = (Button) rootView.findViewById(R.id.btn_startingDate);
		mbtnStartDate.setOnClickListener(this);
		mbtnEndDate = (Button) rootView.findViewById(R.id.btn_endingDate);
		mbtnEndDate.setOnClickListener(this);
		mbtnShowSpecificHistory = (Button) rootView
				.findViewById(R.id.btn_showSpecificHistory);
		mbtnShowSpecificHistory.setOnClickListener(this);
		mbtnShowAllHistory = (Button) rootView
				.findViewById(R.id.btn_showAllHistory);
		mbtnShowAllHistory.setOnClickListener(this);

		mtxtEndignDate = (TextView) rootView.findViewById(R.id.txt_endingDate);
		mtxtStartingDate = (TextView) rootView
				.findViewById(R.id.txt_startingDate);
		sp = getActivity().getSharedPreferences(MyPREFERENCES,
				getActivity().MODE_APPEND);
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
					if (strDate.equals("start")) {
						mtxtStartingDate.setText(new StringBuilder()
								.append("0" + dayOfMonth).append("/")
								.append("0" + monthOfYear).append("/")
								.append(year));
					} else {
						mtxtEndignDate.setText(new StringBuilder()
								.append("0" + dayOfMonth).append("/")
								.append("0" + monthOfYear).append("/")
								.append(year));
					}

				} else {
					if (strDate.equals("start")) {
						mtxtStartingDate.setText(new StringBuilder()
								.append("0" + dayOfMonth).append("/")
								.append(monthOfYear).append("/").append(year));
					} else {
						mtxtEndignDate.setText(new StringBuilder()
								.append("0" + dayOfMonth).append("/")
								.append(monthOfYear).append("/").append(year));
					}

				}

			} else {

				if (monthOfYear < 10) {
					if (strDate.equals("start")) {
						mtxtStartingDate.setText(new StringBuilder()
								.append(dayOfMonth).append("/")
								.append("0" + monthOfYear).append("/")
								.append(year));
					} else {
						mtxtEndignDate.setText(new StringBuilder()
								.append(dayOfMonth).append("/")
								.append("0" + monthOfYear).append("/")
								.append(year));
					}

				} else {
					if (strDate.equals("start")) {
						mtxtStartingDate.setText(new StringBuilder()
								.append(dayOfMonth).append("/")
								.append(monthOfYear).append("/").append(year));
					} else {
						mtxtEndignDate.setText(new StringBuilder()
								.append(dayOfMonth).append("/")
								.append(monthOfYear).append("/").append(year));
					}

				}
			}

		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_startingDate:
			strDate = "start";
			showDatePicker();

			break;
		case R.id.btn_endingDate:
			strDate = "end";
			showDatePicker();

			break;
		case R.id.btn_showSpecificHistory:
			strStartDate = mtxtStartingDate.getText().toString().trim();
			strEndDate = mtxtEndignDate.getText().toString().trim();
			Fragment fShowSpecificHistory = new FragmentViewExpenceHistory();
			FragmentTransaction ft = getFragmentManager().beginTransaction();

			ft.setCustomAnimations(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);

			ft.replace(R.id.main, fShowSpecificHistory);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.addToBackStack(null);
			ft.commit();
			Bundle bundle = new Bundle();
			bundle.putString("HISTORY", "SPECIFIC");
			bundle.putString("startdate", strStartDate);
			bundle.putString("enddate", strEndDate);
			System.err
					.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>StartDateHome"
							+ strStartDate);
			System.err
					.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>EndDateDateHome"
							+ strEndDate);
			fShowSpecificHistory.setArguments(bundle);

			break;
		case R.id.btn_showAllHistory:
			Fragment fShowAllHistory = new FragmentViewExpenceHistory();
			FragmentTransaction ft2 = getFragmentManager().beginTransaction();

			ft2.setCustomAnimations(R.anim.in_from_right_activity,
					R.anim.out_to_left_activity);

			ft2.replace(R.id.main, fShowAllHistory);
			ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft2.addToBackStack(null);
			ft2.commit();
			Bundle bundle2 = new Bundle();
			bundle2.putString("HISTORY", "ALL");
			fShowAllHistory.setArguments(bundle2);

			break;
		default:
			break;
		}
	}
}

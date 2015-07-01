package com.zaptech.datesorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.os.Bundle;

public class Activity_Home extends Activity {

	Model_Dates model_Dates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// get current date time with Date()
		/*
		 * Date date = new Date(2015, 8, 25); Date date1 = new Date(2015, 3,
		 * 25); Date date2 = new Date(2015, 5, 25); Date date3 = new Date(2015,
		 * 4, 25); Date date4 = new Date(2015, 11, 25);
		 * 
		 * ArrayList<Date> data = new ArrayList<Date>();
		 */
		// //////////////DOING THROUGH POJO CLASS///////////////
		ArrayList<Model_Dates> arrayListDates = new ArrayList<Model_Dates>();
		model_Dates = new Model_Dates("2015-05-23");
		arrayListDates.add(model_Dates);
		model_Dates = new Model_Dates("2015-09-25");
		arrayListDates.add(model_Dates);
		model_Dates = new Model_Dates("2015-04-15");
		arrayListDates.add(model_Dates);
		model_Dates = new Model_Dates("2015-05-23");
		arrayListDates.add(model_Dates);
		model_Dates = new Model_Dates("2015-05-20");
		arrayListDates.add(model_Dates);
		model_Dates = new Model_Dates("2015-05-19");
		arrayListDates.add(model_Dates);
		model_Dates = new Model_Dates("2018-05-10");

		System.err.println("SIZE>>>>>>>>>>>>>>>>>>>>>>>>>>."
				+ arrayListDates.size());
		Collections.sort(arrayListDates, new FishNameComparator());

		// /////////////POJO COMPLETES////////////////////

		for (int i = 0; i < arrayListDates.size(); i++) {

			System.err.println("+++++++++++++++++++++++"
					+ arrayListDates.get(i).getDate());
		}
		/*
		 * data.add(date); data.add(date1); data.add(date2); data.add(date3);
		 * data.add(date4);
		 * 
		 * Collections.sort(data); for (int i = 0; i < data.size(); i++) {
		 * Toast.makeText( getApplicationContext(), "" + data.get(i).getDate() +
		 * "/" + data.get(i).getMonth() + "/" + data.get(i).getYear(),
		 * 2000).show(); } Collections.sort(data, Collections.reverseOrder());
		 * for (int i = 0; i < data.size(); i++) { Toast.makeText(
		 * getApplicationContext(), "" + data.get(i).getDate() + "/" +
		 * data.get(i).getMonth() + "/" + data.get(i).getYear(), 2000).show(); }
		 */
	}

	public class FishNameComparator implements Comparator<Model_Dates> {
		public int compare(Model_Dates left, Model_Dates right) {
			return left.getDate().toString()
					.compareTo(right.getDate().toString());
		}
	}

}

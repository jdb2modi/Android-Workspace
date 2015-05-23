package com.zaptech.moneymanager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Activity_FragmentSummary extends Fragment {
	TextView tvBalance, tvExpence, tvIncome, tvTotalBalanceHed,
			tvTotalIncomeHed, tvTotalExpenceHed, tvLogo;
	DBHelper dbHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_summary, container, false);

	}

	
}

package com.zaptech.myexpenditure2.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zaptech.myexpenditure2.R;
import com.zaptech.myexpenditure2.database.DBHelper;

public class FragmentUpdateExpenceDetail extends Fragment implements
		OnClickListener {
	private EditText medExpenceCategory;
	private EditText medExpenceMode;
	private EditText medExpenceAmount;
	private EditText medExpenceDate;
	private Button mbtnUpdateExpence;
	private String strData;
	private DBHelper dbHelper;
	private String strExpenceCategory;
	private String strExpenceMode;
	private String strExpenceAmount;
	private String strExpenceDate;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_updateexpencedetail,
				container, false);
		init(rootView);
		Bundle bundle = this.getArguments();
		strData = bundle.getString("ExpenceTitleToUpdate");
		Toast.makeText(getActivity(), strData, Toast.LENGTH_SHORT).show();
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>strDate" + strData);
		setValues();
		return rootView;
	}

	public void init(View rootView) {
		medExpenceCategory = (EditText) rootView
				.findViewById(R.id.ed_updateExpenceCategory);
		medExpenceMode = (EditText) rootView
				.findViewById(R.id.ed_updateExpenceMode);
		medExpenceAmount = (EditText) rootView
				.findViewById(R.id.ed_updateExpenceAmount);
		medExpenceDate = (EditText) rootView
				.findViewById(R.id.ed_updateExpenceDate);
		mbtnUpdateExpence = (Button) rootView
				.findViewById(R.id.btnUpdateExpenceNow);
		mbtnUpdateExpence.setOnClickListener(this);
		dbHelper = new DBHelper(getActivity());
	}

	private void setValues() {
		ArrayList<String> arrayListExpenceDetails = new ArrayList<String>();
		arrayListExpenceDetails = dbHelper.getExpenceDetailsToUpdate(strData);
		if (arrayListExpenceDetails.size() <= 0) {
			Toast.makeText(getActivity(), "No Record Found..!",
					Toast.LENGTH_SHORT).show();

		} else {
			strExpenceCategory = arrayListExpenceDetails.get(0).toString();
			strExpenceMode = arrayListExpenceDetails.get(1).toString();
			strExpenceAmount = arrayListExpenceDetails.get(2).toString();
			strExpenceDate = arrayListExpenceDetails.get(3).toString();
			medExpenceCategory.setText(strExpenceCategory);
			medExpenceMode.setText(strExpenceMode);
			medExpenceAmount.setText(strExpenceAmount);
			medExpenceDate.setText(strExpenceDate);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnUpdateExpenceNow:
			dbHelper.updateExpenceDetails(strExpenceCategory, strExpenceDate,
					strExpenceMode, strExpenceAmount);
			Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

}

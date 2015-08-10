package com.zaptech.tabfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class FirstActivity extends Fragment {

	EditText edt_setData;
	Button btn_sendData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.first_layout, container,
				false);

		edt_setData = (EditText) rootView.findViewById(R.id.edt_SendText);
		btn_sendData = (Button) rootView.findViewById(R.id.btn_sendData);

		return rootView;
	}
}

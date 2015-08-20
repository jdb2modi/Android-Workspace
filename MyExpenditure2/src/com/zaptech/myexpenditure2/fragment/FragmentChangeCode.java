package com.zaptech.myexpenditure2.fragment;

import android.graphics.Typeface;
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

public class FragmentChangeCode extends Fragment implements OnClickListener {
	private EditText mEdCurrentCode;
	private EditText mEdNewCode;
	private EditText mEdConfirmCode;
	private DBHelper dbHelper;

	private Button mBtnChangeAuthentication;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_changecode, container,
				false);
		init(rootView);
		//setTypeface();
		return rootView;
	}

	public void init(View rootView) {
		dbHelper = new DBHelper(getActivity());
		mEdCurrentCode = (EditText) rootView.findViewById(R.id.ed_currentCode);
		mEdNewCode = (EditText) rootView.findViewById(R.id.ed_newCode);
		mEdConfirmCode = (EditText) rootView.findViewById(R.id.ed_confirmCode);

		mBtnChangeAuthentication = (Button) rootView
				.findViewById(R.id.btn_changeAuthentication);
		mBtnChangeAuthentication.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_changeAuthentication:
			if (mEdConfirmCode.getText().toString().trim().length() <= 0
					|| mEdCurrentCode.getText().toString().trim().length() <= 0
					|| mEdNewCode.getText().toString().trim().length() <= 0) {
				Toast.makeText(getActivity(),
						"Authetication code never be null", Toast.LENGTH_SHORT)
						.show();
			} else {
				changeCode();
			}
			break;

		default:
			break;
		}
	}

	public void changeCode() {
		String strCurrentPass = mEdCurrentCode.getText().toString();
		String strTemp = dbHelper.getPassword().toString();
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>"+strTemp);
		if (strCurrentPass.equals(strTemp)) {
			String strNewPass = mEdNewCode.getText().toString();
			String strConPass = mEdConfirmCode.getText().toString();
			if (strNewPass.equals(strConPass)) {
				dbHelper.updatePassword(strNewPass);
				Toast.makeText(getActivity(),
						"Security Code changed successfully..!",
						Toast.LENGTH_SHORT).show();
				mEdCurrentCode.setText("");
				mEdNewCode.setText("");
				mEdConfirmCode.setText("");
				mEdCurrentCode.setFocusable(true);

			} else {
				Toast.makeText(getActivity(), "Password does not matched..!",
						Toast.LENGTH_SHORT).show();
				mEdConfirmCode.setText("");
				mEdNewCode.setText("");

				mEdConfirmCode.setFocusable(true);
			}
		} else {
			Toast.makeText(getActivity(), "You entered wrong password..!!!",
					Toast.LENGTH_SHORT).show();
			mEdConfirmCode.setText("");
			mEdNewCode.setText("");
			mEdCurrentCode.setText("");
			mEdCurrentCode.setFocusable(true);
		}
	}

	public void setTypeface() {
		Typeface tyFace = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Tahoma.ttf");

		mEdCurrentCode.setTypeface(tyFace);
		mEdNewCode.setTypeface(tyFace);
		mEdConfirmCode.setTypeface(tyFace);
		mBtnChangeAuthentication.setTypeface(tyFace);

	}
}

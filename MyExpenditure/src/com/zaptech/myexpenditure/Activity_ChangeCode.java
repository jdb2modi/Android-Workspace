package com.zaptech.myexpenditure;

import com.ifactory.myexpenditure.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_ChangeCode extends Activity implements OnClickListener {
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String PASSWORD = "password";
	public static final String CODE = "code";
	private SharedPreferences mSp;
	private DBHelper mDbHelper;
	private EditText mEdCurrent;
	private EditText mEdNew;
	private EditText mEdConfirm;
	private Button mBtnChangeCode;
	private Button mBtnExit;
	private Button mBtnBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code);
		init();
		savePreferences();
	}

	public void init() {
		mSp = getSharedPreferences(MyPREFERENCES, MODE_APPEND);
		mDbHelper = new DBHelper(Activity_ChangeCode.this);
		mEdCurrent = (EditText) findViewById(R.id.ed_currentCode);
		mEdNew = (EditText) findViewById(R.id.ed_newCode);
		mEdConfirm = (EditText) findViewById(R.id.ed_confirmCode);
		mBtnChangeCode = (Button) findViewById(R.id.btn_changeAuthentication);
		mBtnChangeCode.setOnClickListener(this);
		mBtnExit=(Button) findViewById(R.id.btn_exitFromChangeCode);
		mBtnExit.setOnClickListener(this);
		mBtnBack=(Button)findViewById(R.id.btn_backFromChangeCode);
		mBtnBack.setOnClickListener(this);
		mDbHelper.insertPassword();
	}

	public void savePreferences() {

		Editor editor = mSp.edit();
		editor.putString(CODE, "1");
		editor.commit();

	}

	public void changeCode() {
		String strCurrentPass = mEdCurrent.getText().toString();
		String strTemp = mDbHelper.getPassword().toString();
		if (strCurrentPass.equals(strTemp)) {
			String strNewPass = mEdNew.getText().toString();
			String strConPass = mEdConfirm.getText().toString();
			if (strNewPass.equals(strConPass)) {
				mDbHelper.updatePassword(strNewPass);
				Toast.makeText(Activity_ChangeCode.this,
						"Security Code changed successfully..!",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(Activity_ChangeCode.this,
						"Password does not matched..!", Toast.LENGTH_SHORT)
						.show();
				mEdConfirm.setText("");
				mEdNew.setText("");

				mEdConfirm.setFocusable(true);
			}
		} else {
			Toast.makeText(Activity_ChangeCode.this,
					"You entered wrong password..!!!", Toast.LENGTH_SHORT)
					.show();
			mEdConfirm.setText("");
			mEdNew.setText("");
			mEdCurrent.setText("");
			mEdCurrent.setFocusable(true);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_changeAuthentication:
			changeCode();
			break;
		case R.id.btn_exitFromChangeCode:
			exit();
			break;
		case R.id.btn_backFromChangeCode:
			back();
			break;
		default:
			break;
		}

	}

	private void exit() {
		Toast.makeText(getApplicationContext(), "Exiting...",
				Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alert = new AlertDialog.Builder(
				Activity_ChangeCode.this);
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
		Intent mIntent;
		mIntent = new Intent(Activity_ChangeCode.this, Activity_Settings.class);
		startActivity(mIntent);
	}
}

package com.ifactory.myexpenditure;

import android.app.Activity;
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
	SharedPreferences sp;
	DBHelper dbHelper;
	EditText edCurrent, edNew, edConfirm;
	Button btnChangeCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code);
		init();
		savePreferences();
	}

	public void init() {
		sp = getSharedPreferences(MyPREFERENCES, MODE_APPEND);
		dbHelper = new DBHelper(Activity_ChangeCode.this);
		edCurrent = (EditText) findViewById(R.id.ed_currentCode);
		edNew = (EditText) findViewById(R.id.ed_newCode);
		edConfirm = (EditText) findViewById(R.id.ed_confirmCode);
		btnChangeCode = (Button) findViewById(R.id.btn_changeAuthentication);
		btnChangeCode.setOnClickListener(this);
		dbHelper.insertPassword();
	}

	public void savePreferences() {

		Editor editor = sp.edit();
		editor.putString(CODE, "1");
		editor.commit();

	}

	public void changeCode() {
		String strCurrentPass = edCurrent.getText().toString();
		String strTemp = dbHelper.checkPassword().toString();
		if (strCurrentPass.equals(strTemp)) {
			String strNewPass = edNew.getText().toString();
			String strConPass = edConfirm.getText().toString();
			if (strNewPass.equals(strConPass)) {
				dbHelper.updatePassword(strNewPass);
				Toast.makeText(Activity_ChangeCode.this,
						"Security Code changed successfully..!",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(Activity_ChangeCode.this,
						"Password does not matched..!", Toast.LENGTH_SHORT)
						.show();
				edConfirm.setText("");
				edNew.setText("");

				edConfirm.setFocusable(true);
			}
		} else {
			Toast.makeText(Activity_ChangeCode.this,
					"You entered wrong password..!!!", Toast.LENGTH_SHORT)
					.show();
			edConfirm.setText("");
			edNew.setText("");
			edCurrent.setText("");
			edCurrent.setFocusable(true);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_changeAuthentication:
			changeCode();
			break;

		default:
			break;
		}

	}
}

package com.example.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends Activity{
	String strNewPassword,strRetypePasword;
	EditText ed_newpassword,ed_retypepassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.changepassword);
	}
	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.change_pswd_btn_back:
			Intent i_back = new Intent(ChangePassword.this,
					ProfileSetting.class);
			startActivity(i_back);
		break;
		case R.id.change_pswd_btnsave:
			ed_newpassword=(EditText)findViewById(R.id.change_pswd_ed_newpswd);
			ed_retypepassword=(EditText)findViewById(R.id.change_pswd_ed_retypepswd);
			strNewPassword=ed_newpassword.getText().toString();
			strRetypePasword=ed_retypepassword.getText().toString();
			
			if(strNewPassword.equals("") )
			{
				Toast.makeText(ChangePassword.this,"Please Enter New Password", Toast.LENGTH_SHORT).show();
				//ed_newpassword.setText("");
			}
			else if(strRetypePasword.equals(""))
			{
				Toast.makeText(ChangePassword.this,"Please Retype Password", Toast.LENGTH_SHORT).show();
			}
			
			
		/* if(strRetypePasword.equalsIgnoreCase(strNewPassword))
			{
				
				Toast.makeText(ChangePassword.this,"Not Same Password", Toast.LENGTH_SHORT).show();
				ed_retypepassword.setText("");
			}	*/
		 else 
		 {
			Intent i_save = new Intent(ChangePassword.this,
					ProfileSetting.class);
			startActivity(i_save);
		 }
		break;

		}
	}
}

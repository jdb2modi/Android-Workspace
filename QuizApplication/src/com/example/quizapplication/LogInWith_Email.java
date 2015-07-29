package com.example.quizapplication;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizapplication.common.Common;

public class LogInWith_Email extends Activity {

	// Typeface tf;

	Button btn_go, btn_loginFB;
	MediaPlayer mp;
	
	EditText edEmail,edUserName;
	String strEmail="",strUserName="",str,result;
	
	String strEmail_pref,strEmail_resp;
	JSONObject jsonObject;
	SharedPreferences preferences;
	ProgressDialog pd;
	Common common;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_with_email);

		// tf = Typeface.createFromAsset(getAssets(), "Chalkduster.ttf");
	
		mp= MediaPlayer.create(LogInWith_Email.this, R.raw.button);
		common = new Common(this);
		pd = new ProgressDialog(LogInWith_Email.this);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		btn_go = (Button) findViewById(R.id.login_with_email_btnGo);
		
		edEmail=(EditText)findViewById(R.id.login_with_email_ed_emailadd);
		edUserName=(EditText)findViewById(R.id.login_with_email_ed_userNM);
		
		edEmail.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					if(edEmail.getText().toString()!=null && !edEmail.getText().toString().trim().equals("")){
						if(isEmailValid(edEmail.getText().toString()))
						{
							if (common.checkInternetConnection(LogInWith_Email.this))
							{
								new CheckLoginBy_Email().execute();
							}
							else
							{
								Toast.makeText(getApplicationContext(), "Please check your Internet Connection first", Toast.LENGTH_SHORT).show(); 
							}
							
						}
					}
				}
				
			}
		});

	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.login_with_email_btnGo:

			mp.start();
			strEmail = edEmail.getText().toString();
			
			if (common.checkInternetConnection(LogInWith_Email.this))
			{
			if(isEmailValid(strEmail))// && !edUserName.getText().toString().equals(""))
			{
				new CheckLoginBy_Email().execute();
			}
			else if(!edUserName.getText().toString().equals("") && isEmailValid(strEmail))
			{
				new CheckLoginBy_Email().execute();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Please Enter valid Email Address or user Name",
						Toast.LENGTH_SHORT).show();
			}
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Please check your Internet Connection first", Toast.LENGTH_SHORT).show(); 
			}
			
			break;

		case R.id.login_with_email_btn_back:

			mp.start();
			Intent i_back = new Intent(LogInWith_Email.this, WelcomeScreen.class);
			startActivity(i_back);
			break;

		default:

			break;

		}

	}
	
	 public class CheckLoginBy_Email extends AsyncTask<Void, Void, Void> {

	
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pd.setCancelable(false);
			pd.setTitle(getString(R.string.app_name));
			pd.setMessage("Loading.....");
			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			try {

				strEmail=edEmail.getText().toString();
				strUserName=edUserName.getText().toString();
				
				JSONObject jObj = new JSONObject();

				jObj.put("u_email",strEmail );
				jObj.put("u_name", strUserName);
				jObj.put("ModMethod", "checkLoginByEmail");

				nameValuePairs.add(new BasicNameValuePair("data",
						jObj.toString()));
				result = common.call(nameValuePairs,
						"http://122.170.97.189:81/iphonequiz/webservice/userClass.php");

			} catch (Exception e) {
				if (pd != null && pd.isShowing())
					pd.dismiss();
				result = "";
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result1) {
			super.onPostExecute(result1);
			if (pd != null && pd.isShowing())
				pd.dismiss();
			Log.e("response from webservice", result);

			try {
				if (result != null) {
					/** JSON objects to GET THE RESULT form the websevice */

					jsonObject = new JSONObject(result);
					str = "" + jsonObject.getInt("code");

					if (str != null && str.equals("1")) {
						
					
						/*JSONObject jsonobject1 = jsonObject
								.getJSONObject("msg");
						strEmail_resp = jsonobject1.getString("u_email");*/
						
						SharedPreferences.Editor editor = preferences.edit();
						editor.putString("Email", strEmail);
						editor.commit();
						

						Intent i_go = new Intent(LogInWith_Email.this,
								 WelcomeLogin.class);
								 startActivity(i_go);

					} else if (str != null && str.equals("2")) {
						Toast.makeText(LogInWith_Email.this,
								"Email Not registered, please register it first",
								Toast.LENGTH_SHORT).show();
						
						String input = strEmail;
					
						int index = input.indexOf("@");
						
						if (index > 0)
						   //input = input.Substring(0, index);
							input = input.substring(0,index);
						
						
						edUserName.setText(input);
						edEmail.setEnabled(false);
						
					}
					else if (str != null && str.equals("3")) {
						/*Toast.makeText(LogInWith_Email.this,
								"Email is registered, please check your email",
								Toast.LENGTH_SHORT).show();
						
						SharedPreferences.Editor editor = preferences.edit();
						editor.putString("Email", strEmail);
						editor.commit();*/
						
						
						
						SharedPreferences.Editor editor = preferences.edit();
						editor.putString("Email", strEmail);
						editor.commit();
						
						Toast.makeText(LogInWith_Email.this,
								"Email is registered, please check your email",
								Toast.LENGTH_SHORT).show();
						
						Intent i_go = new Intent(LogInWith_Email.this,
								 WelcomeLogin.class);
								 startActivity(i_go);
						
					}
					else
					{
						Toast.makeText(LogInWith_Email.this,
								"Invalid Parameter supplied",
								Toast.LENGTH_SHORT).show();
						
					}

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	}
	
	
	boolean isEmailValid(CharSequence email) {
		   return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
		}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mp.stop();
		super.onDestroy();
	}
}
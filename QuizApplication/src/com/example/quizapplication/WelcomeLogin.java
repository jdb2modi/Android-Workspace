package com.example.quizapplication;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapplication.common.Common;

public class WelcomeLogin extends Activity {

	Dialog myDialog;
	TextView tv_forgotpswd;
	Typeface tf;
	Button btn_go;

	EditText ed_FGT_email, ed_email, ed_password;
	String str_Fpswd_email, validation, str_email, strpassword, result, str;
	String u_id, fb_id, u_name, u_email, u_password, u_photo, u_diamond,
			u_score, u_wins, u_tokenid, is_occupied, u_session, status,
			u_lastlogin, cdate, push_notification, opp_guess, chat_enable,
			t_ends;

	MediaPlayer mp;

	SharedPreferences preferences;
	ProgressDialog pd;
	Common common;
	JSONObject jsonObject;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome_login);
		tf = Typeface.createFromAsset(getAssets(), "Chalkduster.ttf");
		mp = MediaPlayer.create(WelcomeLogin.this, R.raw.button);
		common = new Common(this);
		pd = new ProgressDialog(WelcomeLogin.this);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		str_email = preferences.getString("Email", "");
		ed_email = (EditText) findViewById(R.id.welcome_login_ed_emailadd);
		ed_password = (EditText) findViewById(R.id.welcome_login_ed_password);
		ed_email.setText(str_email);
		ed_email.setEnabled(false);
		tv_forgotpswd = (TextView) findViewById(R.id.welcome_login_TVw_forgotPswd);
		tv_forgotpswd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				mp.start();
				myDialog = new Dialog(WelcomeLogin.this);
				myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				myDialog.setContentView(R.layout.forgotpassword_dialog);
				myDialog.setCanceledOnTouchOutside(false);
				myDialog.getWindow().setGravity(Gravity.TOP);

				myDialog.getWindow()
						.setBackgroundDrawable(new ColorDrawable(0));

				myDialog.setCancelable(true);
				myDialog.show();

				Button btn_close = (Button) myDialog
						.findViewById(R.id.forgot_pswd_btn_close);

				Button btn_go = (Button) myDialog
						.findViewById(R.id.forgot_pswd_btn_go);

				btn_close.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						mp.start();
						myDialog.dismiss();
					}
				});

				btn_go.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						mp.start();
						ed_FGT_email = (EditText) myDialog
								.findViewById(R.id.forgot_pswd_txtV_email);
						str_Fpswd_email = ed_FGT_email.getText().toString();
						// validation = "[a-zA-Z._-]+@[a-z]+.[a-z]+";

						// validation =
						// "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

						if (common.checkInternetConnection(WelcomeLogin.this))
						{
						if (isEmailValid(str_Fpswd_email)) {
							// Toast.makeText(getApplicationContext(),
							// "Correct Address", Toast.LENGTH_SHORT).show();
							new ForgotPasswordEmail().execute();

						}
						
						else {
							Toast.makeText(getApplicationContext(),
									"Wrong email address", Toast.LENGTH_SHORT)
									.show();
						}
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Please check your Internet Connection first", Toast.LENGTH_SHORT).show();
						}

					}
				});

				myDialog.show();
			}

		});

	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.welcome_login_btnGo:

			mp.start();

			if (ed_password.getText().toString().trim().equals("")) {
				Toast.makeText(getApplicationContext(),
						"Please enter password", Toast.LENGTH_SHORT).show();
			} else {
				/*
				 * Toast.makeText(getApplicationContext(),
				 * "Invalid email address", Toast.LENGTH_SHORT).show();
				 */
				if (common.checkInternetConnection(WelcomeLogin.this))
				{
				new GetLoginBy_Email().execute();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please check your Internet Connection first", Toast.LENGTH_SHORT).show();
				}


			}

			break;

		case R.id.welcome_login_btn_back:

			mp.start();
			Intent i_back = new Intent(WelcomeLogin.this, LogInWith_Email.class);
			startActivity(i_back);
			break;

		default:

			break;

		}

	}

	public class GetLoginBy_Email extends AsyncTask<Void, Void, Void> {

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

				strpassword = ed_password.getText().toString();

				str_email = ed_email.getText().toString();

				JSONObject jObj = new JSONObject();

				jObj.put("u_email", str_email);
				jObj.put("u_password", strpassword);
				jObj.put("ModMethod", "getLoginByEmail");

				nameValuePairs.add(new BasicNameValuePair("data", jObj
						.toString()));
				result = common
						.call(nameValuePairs,
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

					JSONObject jsonObject = new JSONObject(result);
					str = "" + jsonObject.getInt("code");

					if (str != null && str.equals("0")) {

						Toast.makeText(WelcomeLogin.this, "Password Mismatch",
								Toast.LENGTH_SHORT).show();

						/*
						 * JSONObject jsonobject1 = jsonObject
						 * .getJSONObject("msg"); strEmail_resp =
						 * jsonobject1.getString("u_email");
						 */

					} else if (str != null && str.equals("1")) {

						JSONArray jsonArray = jsonObject.getJSONArray("output");

						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jsonobject1 = jsonArray.getJSONObject(i);

							u_id = jsonobject1.getString("u_id");
							fb_id = jsonobject1.getString("fb_id");
							u_name = jsonobject1.getString("u_name");
							u_email = jsonobject1.getString("u_email");
							u_password = jsonobject1.getString("u_password");
							u_photo = jsonobject1.getString("u_photo");
							u_diamond = jsonobject1.getString("u_diamond");
							u_score = jsonobject1.getString("u_score");
							u_wins = jsonobject1.getString("u_wins");
							u_tokenid = jsonobject1.getString("u_tokenid");
							is_occupied = jsonobject1.getString("is_occupied");
							u_session = jsonobject1.getString("u_session");
							status = jsonobject1.getString("status");
							u_lastlogin = jsonobject1.getString("u_lastlogin");
							cdate = jsonobject1.getString("cdate");
							push_notification = jsonobject1
									.getString("push_notification");
							opp_guess = jsonobject1.getString("opp_guess");
							chat_enable = jsonobject1.getString("chat_enable");
							t_ends = jsonobject1.getString("t_ends");
							
							
							Log.i("U name is:- ", u_name);
							Log.i("Photo is:- ", u_photo);

							//Putting all the values in shared prefrences
							
							SharedPreferences.Editor editor = preferences
									.edit();
							editor.putString("u_id_Pref", u_id);
							editor.putString("fb_id_Pref", fb_id);
							editor.putString("u_name_Pref", u_name);
							editor.putString("u_email_Pref", u_email);
							editor.putString("u_photo_Pref", u_photo);
							editor.putString("u_password_Pref", u_password);
							editor.putString("u_diamond_Pref", u_diamond);
							editor.putString("u_score_Pref", u_score);
							editor.putString("u_wins_Pref", u_wins);
							editor.putString("u_tokenid_Pref", u_tokenid);
							editor.putString("is_occupied_Pref", is_occupied);
							editor.putString("u_session_Pref", u_session);
							editor.putString("status_Pref", status);
							editor.putString("cdate_Pref", cdate);
							editor.putString("push_notification_Pref", push_notification);
							editor.putString("opp_guess_Pref", opp_guess);
							editor.putString("chat_enable_Pref", chat_enable);
							editor.putString("t_ends_Pref", t_ends);
							editor.commit();

							Intent i_home = new Intent(WelcomeLogin.this,
									HomeScreen.class);
							startActivity(i_home);
						}

					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	}

	public class ForgotPasswordEmail extends AsyncTask<Void, Void, Void> {

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

				JSONObject jObj = new JSONObject();

				jObj.put("u_email", ed_FGT_email.getText().toString());

				jObj.put("ModMethod", "forgotPassword");

				nameValuePairs.add(new BasicNameValuePair("data", jObj
						.toString()));
				result = common
						.call(nameValuePairs,
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

					if (str != null && str.equals("0")) {

						Toast.makeText(WelcomeLogin.this,
								"Invalid Parameters Supplied",
								Toast.LENGTH_SHORT).show();

						/*
						 * JSONObject jsonobject1 = jsonObject
						 * .getJSONObject("msg"); strEmail_resp =
						 * jsonobject1.getString("u_email");
						 */

					} else if (str != null && str.equals("1")) {

						Toast.makeText(getApplicationContext(),
								"Password Reset, Check your mail",
								Toast.LENGTH_SHORT).show();
						myDialog.dismiss();

						/*
						 * siteId = jsonobject1.getString("site_id");
						 * site_Lat_to_set = jsonobject1.getString("site_lat");
						 * site_Long_to_set =
						 * jsonobject1.getString("site_long");
						 */

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

	protected void onDestroy() {
		// TODO Auto-generated method stub
		mp.stop();
		super.onDestroy();
	}

}

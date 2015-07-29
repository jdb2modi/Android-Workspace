package com.example.quizapplication;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.quizapplication.common.Common;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FindOpponent_Email extends Activity {

	MediaPlayer mp;
	String email, str, result;
	String u_name_Pref, opp_id_Pref, user_photo_Pref,u_diamond_Pref,u_id,opp_photo_Pref;
	EditText ed_email;

	SharedPreferences preferences;
	ProgressDialog pd;
	private Dialog myDialog;
	Common common;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_opponent_email);

		mp = MediaPlayer.create(FindOpponent_Email.this, R.raw.button);
		pd = new ProgressDialog(FindOpponent_Email.this);
		common = new Common(this);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		u_id = preferences.getString("u_id_Pref", "");
		
		ed_email = (EditText) findViewById(R.id.find_opponent_email_ed_emailadd);

	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.find_opponent_email_btn_go:

			mp.start();

			email = ed_email.getText().toString();

			if (email.trim().equals("")) {
				Toast.makeText(getApplicationContext(),
						"Please enter Emil Address", Toast.LENGTH_SHORT).show();
			} else if (isEmailValid(email)) {
				if (common.checkInternetConnection(FindOpponent_Email.this))
				{

				new FindOpponentByEmail().execute();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please check your Internet Connection first", Toast.LENGTH_SHORT).show(); 
				}
			} else {
				Toast.makeText(getApplicationContext(),
						"Invalid email address", Toast.LENGTH_SHORT).show();
			}

			break;

		case R.id.find_opponent_email_btn_back:

			mp.start();

			Intent i_back = new Intent(FindOpponent_Email.this,
					CreateGame.class);
			startActivity(i_back);
			break;

		default:

			break;

		}

	}

	public class FindOpponentByEmail extends AsyncTask<Void, Void, Void> {

		ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

		protected void onPreExecute() {
			super.onPreExecute();

			pd.setTitle(getString(R.string.app_name));
			pd.setCancelable(false);
			pd.setMessage("Loading.....");
			pd.show();

		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {

				JSONObject jObj = new JSONObject();
				jObj.put("ModMethod", "getFriendByEmail");
				jObj.put("u_id", u_id);
				jObj.put("u_email", ed_email.getText().toString().trim());

				nameValuePair.add(new BasicNameValuePair("data", jObj
						.toString()));

				result = common
						.call(nameValuePair,
								"http://122.170.97.189:81/iphonequiz/webservice/userClass.php");

				Log.e("Response : ", result);
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("U R in Error part :",
						"Here Comes the error in background");
			}

			return null;
		}

		protected void onPostExecute(Void result1) {
			if (pd != null && pd.isShowing())
				pd.dismiss();

			try {

				if (result != null) {

					JSONObject jsonObject = new JSONObject(result);
					str = "" + jsonObject.getInt("code");

					if (str != null && str.equals("1")) {
						Log.e("Success : ", "Done");

						JSONArray jsonarr = jsonObject.getJSONArray("output");

						for (int i = 0; i < jsonarr.length(); i++) {
							JSONObject jsonarrObject = jsonarr.getJSONObject(i);

							Log.e("u_id", jsonarrObject.optString("u_id"));
							Log.e("fb_id", jsonarrObject.optString("fb_id"));
							Log.e("u_name", jsonarrObject.optString("u_name"));

							u_name_Pref = jsonarrObject.optString("u_name");
							opp_id_Pref = jsonarrObject.optString("u_id");
							opp_photo_Pref = jsonarrObject
									.optString("u_photo");
							u_diamond_Pref = jsonarrObject.optString("u_diamond");

							SharedPreferences.Editor editor = preferences
									.edit();
							editor.putString("u_name_pref", u_name_Pref);
							editor.putString("opp_id_pref", opp_id_Pref);
							editor.putString("opp_Pic_pref", opp_photo_Pref);
							editor.putString("u_diamond_pref", u_diamond_Pref);
							editor.commit();
							Intent i_go = new Intent(FindOpponent_Email.this,
									SpinGameCategory.class);
							startActivity(i_go);
						}
					}

					else {

						myDialog = new Dialog(FindOpponent_Email.this);
						myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						myDialog.setContentView(R.layout.alert_findusername_email);
						myDialog.getWindow().setGravity(Gravity.BOTTOM);
						myDialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(0));
						myDialog.setCancelable(false);
						myDialog.show();
						Button button = (Button) myDialog
								.findViewById(R.id.findUser_Email_dialog_okBtn);
						button.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								myDialog.dismiss();
							}
			
						});
					}

				}
			} catch (JSONException e) {
				e.printStackTrace();
				Log.e("U R in Error part :",
						"Here Comes the error in Post Execute ");
			}
			super.onPostExecute(result1);
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
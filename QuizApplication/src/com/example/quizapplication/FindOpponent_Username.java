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
import android.widget.Toast;

public class FindOpponent_Username extends Activity {

	private EditText edUserName;
	private String strUserName,strResponse;
	String u_name_Pref,opp_id_Pref,opp_photo_Pref,u_diamond_Pref,u_id;
	
	private ProgressDialog pd;
	Common common;
	private Dialog myDialog;
	SharedPreferences preferences;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.find_opponent_username);
		common = new Common(FindOpponent_Username.this);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		u_id = preferences.getString("u_id_Pref", "");
		
	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.find_opponent_username_btn_back:
			Intent i_back = new Intent(FindOpponent_Username.this,
					CreateGame.class);
			startActivity(i_back);
			break;
		case R.id.find_opponent_Uname_btn_go:
			edUserName = (EditText) findViewById(R.id.find_opponent_Uname_ed_emailadd);
			strUserName = edUserName.getText().toString();

			if (strUserName.equals("")) {
				Toast.makeText(FindOpponent_Username.this,
						"Please Enter UserName", Toast.LENGTH_SHORT).show();
			} else {
				
				if (common.checkInternetConnection(FindOpponent_Username.this))
				{
				
				new FindOppByUsername().execute();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please check your Internet Connection first", Toast.LENGTH_SHORT).show(); 
				}
				
			}
			break;
		}
	}
	
	
	public class FindOppByUsername extends AsyncTask<Void, Void, Void> {

		String result;
		ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(FindOpponent_Username.this);
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
				jObj.put("ModMethod", "getFriendByUserName");
				jObj.put("u_id", u_id);
				jObj.put("u_name", edUserName.getText().toString().trim());
				
				
				nameValuePair.add(new BasicNameValuePair("data", jObj
						.toString()));
				
				result = common.call(nameValuePair,"http://122.170.97.189:81/iphonequiz/webservice/userClass.php");


				Log.e("Response : ", result);
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("U R in Error part :", "Here Comes the error in background");
			}

			return null;
		}

		protected void onPostExecute(Void result1) {
			if (pd != null && pd.isShowing())
				pd.dismiss();
			

			try {

				if (result != null) {

					JSONObject jsonObject = new JSONObject(result);
					strResponse = "" + jsonObject.getInt("code");
					

					if (strResponse != null && strResponse.equals("1")) {
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
							
							Intent i_go = new Intent(FindOpponent_Username.this,
									SpinGameCategory.class);
							startActivity(i_go);
						}
					}
					
					else {
						
						
						myDialog = new Dialog(FindOpponent_Username.this);
						myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						myDialog.setContentView(R.layout.alert_find_username_dialog);
						myDialog.getWindow().setGravity(Gravity.BOTTOM);
						myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
						myDialog.setCancelable(false);
						myDialog.show();
						Button button = (Button) myDialog
								.findViewById(R.id.findUser_Name_dialog_okBtn);
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
				Log.e("U R in Error part :", "Here Comes the error in Post Execute ");
			}
			super.onPostExecute(result1);
		}
	}	
	
	
	
	public void parsingData(String str)
	{
		if(str.length()>0){
			
		}
	}
}

package com.example.quizapplication;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.quizapplication.common.Common;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CreateGame extends Activity {

	Typeface tf;
	MediaPlayer mp;
	ProgressDialog pd;
	String u_name_Pref, opp_id_Pref, user_photo_Pref, u_diamond_Pref,u_id,opp_photo_Pref;
	Common common;
	String result, str;
	private Dialog myDialog;
	SharedPreferences preferences;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creategame);

		mp = MediaPlayer.create(CreateGame.this, R.raw.button);
		common = new Common(this);
		pd = new ProgressDialog(CreateGame.this);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		u_id = preferences.getString("u_id_Pref", "");
		tf = Typeface.createFromAsset(getAssets(), "Chalkduster.ttf");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creategame, menu);
		return true;
	}

	public void OnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.createGame_btn_faceBook:

			mp.start();
			break;

		case R.id.createGame_btn_back:

			mp.start();
			finish();
			break;

		case R.id.createGame_btn_username:

			mp.start();
			Intent i_uname = new Intent(CreateGame.this,
					FindOpponent_Username.class);
			startActivity(i_uname);
			break;

		case R.id.createGame_btn_email:

			mp.start();
			Intent i_email = new Intent(CreateGame.this,
					FindOpponent_Email.class);
			startActivity(i_email);
			break;

		case R.id.createGame_btn_random:

			mp.start();
			/*
			 * Intent i_random = new
			 * Intent(CreateGame.this,SpinGameCategory.class);
			 * startActivity(i_random);
			 */
			if (common.checkInternetConnection(CreateGame.this))
			{
			new GetRandomUser().execute();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Please check your Internet Connection first", Toast.LENGTH_SHORT).show(); 
			}
			Log.i("test", "1");

			break;
		default:

			break;

		}

	}

	public class GetRandomUser extends AsyncTask<Void, Void, Void> {

		ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

		protected void onPreExecute() {
			super.onPreExecute();
			Log.i("test", "111");

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
				jObj.put("ModMethod", "getRandomUser");
				jObj.put("u_id", u_id);

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

							Intent i_go = new Intent(CreateGame.this,
									SpinGameCategory.class);
							startActivity(i_go);
						}
					}

					else {

						myDialog = new Dialog(CreateGame.this);
						myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						myDialog.setContentView(R.layout.alert_random_user);
						myDialog.getWindow().setGravity(Gravity.BOTTOM);
						myDialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(0));
						myDialog.setCancelable(false);
						myDialog.show();
						Button button = (Button) myDialog
								.findViewById(R.id.findUser_random_dialog_okBtn);
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mp.stop();
		super.onDestroy();
	}
}

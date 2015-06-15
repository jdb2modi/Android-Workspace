/*Bitmap bitmap = BitmapFactory.decodeStream(is);
ImageView imageView = (ImageView) findViewById(R.id.image_view);
imageView.setImageBitmap(bitmap);*/
//http://80.93.28.24/json/autoexpress.json
package com.zaptech.networkdemo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {
	ProgressDialog mProgress;
	TextView txt_Json, txt_Parsed;
	String latitute;
	String longitude;
	String batteryDistance;
	String time;
	int warning;
	int active;
	String charge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		new GetData().execute();
	}

	public void init() {
		mProgress = new ProgressDialog(HomeActivity.this);
		txt_Json = (TextView) findViewById(R.id.txt_json);
		txt_Parsed = (TextView) findViewById(R.id.txt_parsed);
	}

	class GetData extends AsyncTask<Void, Void, Void> {
		String str;

		@Override
		protected void onPreExecute() {
			mProgress.setTitle("Stream Loder");
			mProgress.setMessage("Please wait, Loading Stream...");
			mProgress.setCancelable(false);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			str = GET("https://dl.dropboxusercontent.com/u/7409975/findmybike.json");

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
			txt_Json.setText(str);
			JsonParsing(str);
			str = "Latitude : " + latitute + "\nLongitude : " + longitude
					+ "\nCahrge : " + charge + "\nDistance : "
					+ batteryDistance + "\nTime : " + time + "\nWarning : "
					+ warning + "\nActive : " + active;
			txt_Parsed.setText(str);
			super.onPostExecute(result);
		}
	}

	public String GET(String str) {
		InputStream inputstream;
		String strConverted = "";

		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(new HttpGet(str));
			inputstream = httpResponse.getEntity().getContent();
			strConverted = convertToString(inputstream);

		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return strConverted;
	}

	public String convertToString(InputStream inputstream) {
		String result = "";
		String line;
		BufferedReader bf = new BufferedReader(new InputStreamReader(
				inputstream));
		try {
			while ((line = bf.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return result;
	}

	public void JsonParsing(String str) {
		try {

			JSONObject jObj = new JSONObject(str);
			time = jObj.getString("datetime");
			JSONObject jLocation = new JSONObject(jObj.getString("location"));
			longitude = jLocation.getString("longitude");
			latitute = jLocation.getString("latitude");

			JSONObject jBattery = new JSONObject(jObj.getString("battery"));
			charge = jBattery.getString("charge");
			batteryDistance = jBattery.getString("distance");

			JSONObject jAlarm = new JSONObject(jObj.getString("alarm"));
			warning = jAlarm.getInt("warning");
			active = jAlarm.getInt("active");

		} catch (JSONException e) {

			e.printStackTrace();
		}
	}
}

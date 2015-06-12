package com.zaptech.networkdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity_Home extends Activity {

	ProgressDialog mProgress;
	String latitute;
	String longitude;
	String batteryDistance;
	String time;
	int warning;
	int active;
	String charge;
	TextView txt1, txt2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__home);
		init();
		new GetData().execute();
	}

	public void init() {

		txt1 = (TextView) findViewById(R.id.txt1);
		txt2 = (TextView) findViewById(R.id.txt2);
	}

	class GetData extends AsyncTask<Void, Void, Void> {
		String str = "";

		@Override
		protected void onPreExecute() {
			mProgress = new ProgressDialog(Activity_Home.this);
			mProgress.setTitle("Data Loder");
			mProgress.setMessage("Please wait, Loading Stream...");
			mProgress.setCancelable(false);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			str = GET("https://dl.dropboxusercontent.com/u/7409975/findmybike.json");
			Log.i("Response", ">>>>>>>>>>>>>>" + str);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
			txt1.setText(str);
			JsonParser(str);
			super.onPostExecute(result);
		}

	}

	public String GET(String url) {
		String result = "";
		InputStream inputStream = null;

		try {
			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse httpResponce = httpClient.execute(new HttpGet(url));

			inputStream = httpResponce.getEntity().getContent();
			if (inputStream != null) {
				result = convertInputStreamToString(inputStream);
			} else {
				result = "Not Found";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * public static String convertInputStreamToString(InputStream inputstream)
	 * { BufferedReader bf = new BufferedReader(new InputStreamReader(
	 * inputstream)); String result = ""; String line = ""; try { while ((line =
	 * bf.readLine()) != null) result += line; } catch (IOException e) {
	 * e.printStackTrace(); } return result;
	 * 
	 * }
	 */

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

	public void JsonParser(String str) {
		try {
			JSONObject obj = new JSONObject(str);
			time = obj.getString("datetime");

			JSONObject locationObj = new JSONObject(obj.getString("location"));
			latitute = locationObj.getString("latitude");
			longitude = locationObj.getString("longitude");

			JSONObject batteryObj = new JSONObject(obj.getString("battery"));
			charge = batteryObj.getString("charge");
			batteryDistance = batteryObj.getString("distance");
			JSONObject alarmObj = new JSONObject(obj.getString("alarm"));
			warning = alarmObj.getInt("warning");
			active = alarmObj.getInt("active");

			txt2.setText(time + latitute + longitude + charge + batteryDistance
					+ warning + active);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

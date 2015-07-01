package com.zaptech.taskstatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zaptech.taskstatus.Models.Model_MainResponce;

public class Activity_Home extends Activity implements OnClickListener {
	ProgressDialog mProgress;
	TextView txt_batteryPercent;
	TextView txt_batteryDistance;
	TextView txt_dateTime;
	ImageButton imgBtn_referesh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();// Initializing variables
		new ParsingData().execute();// Calling AsyncTask
	}

	// Function to Initialize variables..
	public void init() {

		mProgress = new ProgressDialog(Activity_Home.this);

		txt_batteryPercent = (TextView) findViewById(R.id.txt_batteryStatus);
		txt_batteryDistance = (TextView) findViewById(R.id.txt_batteryDistance);
		txt_dateTime = (TextView) findViewById(R.id.txt_dateTime);

		imgBtn_referesh = (ImageButton) findViewById(R.id.imgBtn_refresh);
		imgBtn_referesh.setOnClickListener(Activity_Home.this);

	}

	// An Async class
	class ParsingData extends AsyncTask<Void, Void, Void> {
		String str;

		@Override
		protected void onPreExecute() {
			mProgress.setTitle(getResources()
					.getString(R.string.strDialogTitle));
			mProgress.setMessage(getResources().getString(
					R.string.strDialogMessage));
			mProgress.setCancelable(false);
			mProgress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			str = GET(getResources().getString(R.string.strUrl));
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgress != null) {
				if (mProgress.isShowing()) {
					mProgress.dismiss();
				}
			}
			super.onPostExecute(result);

			// Call to Parse Data..
			parseJson(str);

		}

		// A function to Parse Json Stream..
		public String GET(String url) {
			InputStream inputStream = null;
			String result = "";
			try {

				HttpClient httpclient = new DefaultHttpClient();

				HttpResponse httpResponse = httpclient
						.execute(new HttpGet(url));

				inputStream = httpResponse.getEntity().getContent();

				if (inputStream != null)
					result = convertInputStreamToString(inputStream);
				else
					result = "Did not work!";

			} catch (Exception e) {
				Log.d("InputStream", e.getLocalizedMessage());
			}

			return result;
		}

	}

	// convert inputstream to String
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

	// Function to Parse data using Gson..
	public void parseJson(String str) {
		Gson gson = new Gson();
		Model_MainResponce model_MainResponce = gson.fromJson(str,
				Model_MainResponce.class);

		// Function call to set the Values to the Controls..
		setControls(model_MainResponce);

	}

	public void setControls(Model_MainResponce model_MainResponce) {

		txt_dateTime.setText(txt_dateTime.getText().toString() + " "
				+ model_MainResponce.getDatetime().toString());

		txt_batteryPercent.setText(txt_batteryPercent.getText().toString()
				+ " " + model_MainResponce.getBattery().getCharge().toString());

		// Converting Battery Distance into Meters...
		int int_batteryDistance = convertBatteryDistance(model_MainResponce);
		txt_batteryDistance.setText(txt_batteryDistance.getText().toString()
				+ " " + int_batteryDistance);
		String strDate = dateConvert(model_MainResponce.getDatetime());
		Toast.makeText(getApplicationContext(), strDate, 2000).show();
	}

	// Function to Convert Battery Distance into meters...
	public int convertBatteryDistance(Model_MainResponce model_MainResponce) {
		int int_batteryDistance = Integer.parseInt(model_MainResponce
				.getBattery().getDistance().toString());
		int_batteryDistance /= 1000;
		return int_batteryDistance;
	}

	// Function to Convert date...
	public String dateConvert(String s) {

		SimpleDateFormat newformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			if (s.contains("T")) {
				String datestring = s.split("T")[0];
				SimpleDateFormat oldformat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String reformattedStr = newformat.format(oldformat
						.parse(datestring));
				System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>DDD"
						+ reformattedStr);
				return reformattedStr;
			} else {
				if (Integer.parseInt(s.split("-")[0]) > 13) {
					SimpleDateFormat oldformat = new SimpleDateFormat(
							"yyyy-MM-dd");
					String reformattedStr = newformat
							.format(oldformat.parse(s));
					System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>DDD"
							+ reformattedStr);
					return reformattedStr;

				} else {
					SimpleDateFormat oldformat = new SimpleDateFormat(
							"MM-dd-yyyy");
					String reformattedStr = newformat
							.format(oldformat.parse(s));
					return reformattedStr;
				}

			}
		} catch (Exception e) {
			return null;
		}
	}

	// Function to Restart Activity...
	protected void onRestart() {

		super.onRestart();
		Intent i = new Intent(Activity_Home.this, Activity_Home.class);
		startActivity(i);
		finish();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBtn_refresh:
			onRestart();
			break;

		default:
			break;
		}

	}

}

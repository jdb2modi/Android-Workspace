package com.zaptech.taskgson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zaptech.TaskGson2.Model_MainResponce;

public class Activity_Home extends Activity {
	ProgressDialog mProgress;
	ListView listdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		new Parsing().execute();
	}

	public void init() {
		mProgress = new ProgressDialog(Activity_Home.this);
		listdata = (ListView) findViewById(R.id.listData);
	}

	class Parsing extends AsyncTask<Void, Void, Void> {
		String str;

		@Override
		protected void onPreExecute() {
			mProgress.setTitle("Loading");
			mProgress.setMessage("please wait, loading...");
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
			if (mProgress != null) {
				if (mProgress.isShowing()) {
					mProgress.dismiss();
				}
			}
			super.onPostExecute(result);
			Gson gson = new Gson();
			Model_MainResponce model_MainResponce = gson.fromJson(str,
					Model_MainResponce.class);
			Log.d("DATE TIME : >>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<",
					"" + model_MainResponce.getDatetime());
			Log.d("Latitude::", "---------->"
					+ model_MainResponce.getLocation().getLatitude());

			Log.d("Active: >>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<",
					"" + model_MainResponce.getAlarm().getActive());
			Log.d("Cahrge : >>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<",
					"" + model_MainResponce.getBattery().getCharge());

		}
	}

	public String GET(String url) {
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
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

}

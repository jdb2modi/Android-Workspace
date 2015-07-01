package com.zaptech.taskgsonparsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zaptech.TaskGsonParsing.Models.Model_MainResource;
import com.zaptech.TaskGsonParsing.Models.Model_countryList;

public class Activity_Home extends Activity {
	List<Model_countryList> arraycountry;
	ListView list1;
	LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		new CallTask().execute();
		init();
	}

	public void init() {
		arraycountry = new ArrayList<Model_countryList>();
		list1 = (ListView) findViewById(R.id.list_1);
	}

	class CallTask extends AsyncTask<Void, Void, Void> {
		ProgressDialog mPro;
		String strResponse;

		@Override
		protected Void doInBackground(Void... params) {
			strResponse = GET("http://demo.mysamplecode.com/Servlets_JSP/CountryJSONData");
			return null;
		}

		@Override
		protected void onPreExecute() {
			mPro = new ProgressDialog(Activity_Home.this);
			mPro.setMessage("Please wait, Loading...");
			mPro.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {

			if (mPro != null) {
				if (mPro.isShowing()) {
					mPro.dismiss();
				}
			}
			Gson gson = new Gson();
			Model_MainResource modelMain = gson.fromJson(strResponse,
					Model_MainResource.class);

			Log.d("SUCCESS : ",
					"===>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
							+ modelMain.getSuccess());
			Log.d("List :>DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDd>",
					"" + modelMain.getCountryList().size());
			arraycountry = modelMain.getCountryList();
			list1.setAdapter(new CustomAdpt(Activity_Home.this));
			super.onPostExecute(result);
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

	class CustomAdpt extends BaseAdapter {
		Context context;

		public CustomAdpt(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {

			return arraycountry.size();
		}

		@Override
		public Object getItem(int position) {

			return arraycountry.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (inflater == null) {
				inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.customlayout, null);

			}
			TextView txt1 = (TextView) convertView
					.findViewById(R.id.txt_TextView1);
			TextView txt2 = (TextView) convertView
					.findViewById(R.id.txt_TextView2);
			TextView txt3 = (TextView) convertView
					.findViewById(R.id.txt_TextView3);
			TextView txt4 = (TextView) convertView
					.findViewById(R.id.txt_TextView4);

			txt1.setText("CODE : " + arraycountry.get(position).getCode());
			txt2.setText("Continent : "
					+ arraycountry.get(position).getContinent());
			txt3.setText("Name : " + arraycountry.get(position).getName());
			txt4.setText("Class : " + arraycountry.get(position).getClass());
			return convertView;
		}

	}
}

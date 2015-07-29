package com.example.quizapplication.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

public class Common extends Activity {
	Context context;

	public Common(Context context) {
		this.context = context;
	}

	// Method to check Internet connection is available or not
	public boolean checkInternetConnection(Context context) {

		ConnectivityManager conMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conMgr.getActiveNetworkInfo() != null
				&& conMgr.getActiveNetworkInfo().isAvailable()
				&& conMgr.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	public String call(List<NameValuePair> postParameters, String url) {
		String strresponse = "";
		BufferedReader bufferedReader = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
					postParameters);
			// request.setHeader("application/x-www-form-urlencoded");
			request.setHeader("Content-type",
					"application/x-www-form-urlencoded");
			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);

			bufferedReader = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LineSeparator = System.getProperty("line.separator");
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line + LineSeparator);
			}
			bufferedReader.close();

			// result.setText(stringBuffer.toString());
			Log.e("response ", stringBuffer.toString());
			strresponse = stringBuffer.toString();
		} catch (ClientProtocolException e) {
			strresponse = "";
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			strresponse = "";
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return strresponse;
	}

	public String convertinputStreamToString(InputStream ists)
			throws IOException {
		if (ists != null) {
			StringBuilder sb = new StringBuilder();
			String line;
			try {
				BufferedReader r1 = new BufferedReader(new InputStreamReader(
						ists, "UTF-8"));
				while ((line = r1.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				ists.close();
			}

			return sb.toString();
		} else {
			return "";
		}
	}

}
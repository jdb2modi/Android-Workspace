//Initialize button, onButtonclick taking innerthread and written code for getting data from webservice..
//After getting string value from webservice, creating methods to set Messge , 
//Putting String value into bundle and bundle to Message,created handler ans handler.sendMessage(msgObj);


package com.zaptech.handlerdemo;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Home extends Activity implements OnClickListener {
	Button btnGet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	public void init() {
		btnGet = (Button) findViewById(R.id.GetServerData);
		btnGet.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.GetServerData:
			Toast.makeText(getBaseContext(),
					"Please wait, connecting to server.", Toast.LENGTH_SHORT)
					.show();
			innerThread();

			break;

		default:
			break;
		}

	}

	public void innerThread() {
		// Create Inner Thread Class
		new Thread(new Runnable() {
			private final HttpClient Client = new DefaultHttpClient();
			private String URL = "http://androidexample.com/media/webservice/getPage.php";

			@Override
			public void run() {
				String SetServerString = "";
				HttpGet httpget = new HttpGet(URL);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				try {
					SetServerString = Client.execute(httpget, responseHandler);
					threadMsg(SetServerString);
				} catch (ClientProtocolException e) {
					Log.i("Animation", "Thread  exception " + e);
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}).start();
	}

	private void threadMsg(String msg) {

		if (!msg.equals(null) && !msg.equals("")) {
			Message msgObj = handler.obtainMessage();
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + msgObj);// msgObj
																				// has
																				// what=0
																				// and
																				// when=-3h0m7s314ms
			Bundle b = new Bundle();
			b.putString("message", msg);
			msgObj.setData(b);
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + msgObj);
			handler.sendMessage(msgObj);
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			String aResponse = msg.getData().getString("message");
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>aRESPONE"
					+ aResponse);// Data Android Example
			if ((null != aResponse)) {

				Toast.makeText(getBaseContext(),
						"Server Response: " + aResponse, Toast.LENGTH_SHORT)
						.show();
			} else {

				Toast.makeText(getBaseContext(),
						"Not Got Response From Server.", Toast.LENGTH_SHORT)
						.show();
			}

		}
	};
	
}

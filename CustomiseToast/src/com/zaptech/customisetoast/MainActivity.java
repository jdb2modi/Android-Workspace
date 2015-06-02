package com.zaptech.customisetoast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Toast toastCenter, toastTop, toastBottom;
	Button btnCenter, btnTop, btnBottom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	public void init() {
		btnCenter = (Button) findViewById(R.id.buttonCenter);
		btnCenter.setOnClickListener(this);
		btnTop = (Button) findViewById(R.id.buttonTop);
		btnTop.setOnClickListener(this);
		btnBottom = (Button) findViewById(R.id.buttonBottom);
		btnBottom.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonCenter:
			toastCenter = Toast.makeText(this, "JaiminModi_CENTER : ",
					Toast.LENGTH_LONG);
			toastCenter.setGravity(Gravity.CENTER, 0, 0);
			// toastCenter.setMargin(50, 50);
			toastCenter.show();
			break;
		case R.id.buttonTop:
			toastTop = Toast
					.makeText(this, "JaiminModi_TOP", Toast.LENGTH_LONG);
			toastTop.setGravity(Gravity.TOP, 0, 0);
			toastTop.show();
			break;
		case R.id.buttonBottom:
			toastBottom = Toast.makeText(this, "JaiminModi_BOTTOM",
					Toast.LENGTH_LONG);
			toastBottom.setGravity(Gravity.BOTTOM, 0, 0);
			toastBottom.show();
			break;

		default:
			break;
		}
	}
}

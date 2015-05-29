package com.zaptech.taskframelayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FifthActivity extends Activity implements OnClickListener {
	Button btnExit, btnOne, btnTwo, btnThree, btnFour;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth);
		btnExit = (Button) findViewById(R.id.buttonExit);
		btnOne = (Button) findViewById(R.id.buttonOne);
		btnTwo = (Button) findViewById(R.id.buttonTwo);
		btnThree = (Button) findViewById(R.id.buttonThree);
		btnFour = (Button) findViewById(R.id.buttonFour);
		btnExit.setOnClickListener(this);
		btnOne.setOnClickListener(this);
		btnTwo.setOnClickListener(this);
		btnThree.setOnClickListener(this);
		btnFour.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnExit:
			finish();
			break;
		case R.id.buttonOne:
			finish();
			intent = new Intent(getApplicationContext(), HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonTwo:
			finish();
			intent = new Intent(getApplicationContext(), SecondActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonThree:
			finish();
			intent = new Intent(getApplicationContext(), ThirdActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonFour:
			finish();
			intent = new Intent(getApplicationContext(), FourthActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonExit:
			finish();
			break;
		default:
			break;
		}

	}
}

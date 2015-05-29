package com.zaptech.taskframelayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends Activity {
	Button button1, buttonHome;
	EditText ed1;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		button1 = (Button) findViewById(R.id.button1);
		buttonHome = (Button) findViewById(R.id.btnHomeFromThird);
		ed1 = (EditText) findViewById(R.id.edit1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (ed1.getText().toString().equals("6")) {
					Toast.makeText(ThirdActivity.this, "Correct Answer",
							Toast.LENGTH_SHORT).show();
					finish();
					intent = new Intent(ThirdActivity.this,
							FourthActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(ThirdActivity.this, "Wrong Answer",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		buttonHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(),
						HomeActivity.class);
				startActivity(intent);

			}
		});
	}
}

package com.zaptech.taskframelayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FourthActivity extends Activity {
	Button bt1, buttonHome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);
		bt1 = (Button) findViewById(R.id.btnPrint);
		buttonHome = (Button) findViewById(R.id.btnHomeFromFourth);
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(FourthActivity.this, "Picture can't be Print",
						Toast.LENGTH_SHORT).show();

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

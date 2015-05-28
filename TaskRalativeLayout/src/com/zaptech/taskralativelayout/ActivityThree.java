package com.zaptech.taskralativelayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityThree extends Activity implements OnClickListener {
	Button btn1, btn2, btn3, btn4;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);
		init();
	}

	public void init() {
		btn1 = (Button) findViewById(R.id.button1OnActivity3);
		btn2 = (Button) findViewById(R.id.button2OnActivity3);
		btn3 = (Button) findViewById(R.id.button3OnActivity3);
		btn4 = (Button) findViewById(R.id.button4OnActivity3);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1OnActivity3:
			this.finish();
			intent=new Intent(ActivityThree.this,HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.button2OnActivity3:
			this.finish();
			intent=new Intent(ActivityThree.this,ActivityTwo.class);
			startActivity(intent);
			break;
		case R.id.button3OnActivity3:
			this.finish();
			intent=new Intent(ActivityThree.this,ActivityThree.class);
			startActivity(intent);
			break;
		case R.id.button4OnActivity3:
			this.finish();
			intent=new Intent(ActivityThree.this,ActivityFour.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}

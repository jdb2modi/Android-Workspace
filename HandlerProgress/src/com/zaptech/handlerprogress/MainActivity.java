package com.zaptech.handlerprogress;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements OnClickListener {
	Button btn1;
	ProgressBar pBar;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void init() {
		btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(this);
		pBar = (ProgressBar) findViewById(R.id.progressBar);
	}

	 public void startProgress(View view) {
		    // do something long
		    Runnable runnable = new Runnable() {
		      @Override
		      public void run() {
		        for (int i = 0; i <= 10; i++) {
		          final int value = i;
		           doFakeWork();
		          pBar.post(new Runnable() {
		            @Override
		            public void run() {
		              
		              pBar.setProgress(value);
		            }
		          });
		        }
		      }
		    };
		    new Thread(runnable).start();
		  }

	// Simulating something timeconsuming
	  private void doFakeWork() {
	    try {
	      Thread.sleep(2000);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			startProgress(v);
			break;

		default:
			break;
		}

	}
}

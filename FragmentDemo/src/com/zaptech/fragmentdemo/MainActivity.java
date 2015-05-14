package com.zaptech.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends Activity {
	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		Fragment1 fr1 = new Fragment1();
		Fragment2 fr2 = new Fragment2();

		fragmentTransaction.add(fr1, "");
		fragmentTransaction.add(fr2, "");

	}
}

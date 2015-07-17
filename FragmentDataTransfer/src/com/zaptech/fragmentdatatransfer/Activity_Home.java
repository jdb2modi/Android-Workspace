package com.zaptech.fragmentdatatransfer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class Activity_Home extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);


		 FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         InputFragment input = new InputFragment();
         fragmentTransaction.add(R.id.mainContent,input, "HELLO");
         fragmentTransaction.commit();
       
	}

}
